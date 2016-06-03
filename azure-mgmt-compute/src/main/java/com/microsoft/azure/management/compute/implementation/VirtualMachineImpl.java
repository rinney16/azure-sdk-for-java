package com.microsoft.azure.management.compute.implementation;

import com.microsoft.azure.CloudException;
import com.microsoft.azure.management.compute.AvailabilitySet;
import com.microsoft.azure.management.compute.AvailabilitySets;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.azure.management.compute.implementation.api.VirtualMachineInner;
import com.microsoft.azure.management.compute.implementation.api.Plan;
import com.microsoft.azure.management.compute.implementation.api.HardwareProfile;
import com.microsoft.azure.management.compute.implementation.api.StorageProfile;
import com.microsoft.azure.management.compute.implementation.api.OSProfile;
import com.microsoft.azure.management.compute.implementation.api.DiagnosticsProfile;
import com.microsoft.azure.management.compute.implementation.api.VirtualMachineInstanceView;
import com.microsoft.azure.management.compute.implementation.api.VirtualMachineExtensionInner;
import com.microsoft.azure.management.compute.implementation.api.OperatingSystemTypes;
import com.microsoft.azure.management.compute.implementation.api.ImageReference;
import com.microsoft.azure.management.compute.implementation.api.WinRMListener;
import com.microsoft.azure.management.compute.implementation.api.CachingTypes;
import com.microsoft.azure.management.compute.implementation.api.DiskEncryptionSettings;
import com.microsoft.azure.management.compute.implementation.api.VirtualMachineSizeTypes;
import com.microsoft.azure.management.compute.implementation.api.VirtualHardDisk;
import com.microsoft.azure.management.compute.implementation.api.VirtualMachinesInner;
import com.microsoft.azure.management.compute.implementation.api.OSDisk;
import com.microsoft.azure.management.compute.implementation.api.DiskCreateOptionTypes;
import com.microsoft.azure.management.compute.implementation.api.DataDisk;
import com.microsoft.azure.management.compute.implementation.api.LinuxConfiguration;
import com.microsoft.azure.management.compute.implementation.api.WindowsConfiguration;
import com.microsoft.azure.management.compute.implementation.api.WinRMConfiguration;
import com.microsoft.azure.management.compute.implementation.api.SshConfiguration;
import com.microsoft.azure.management.compute.implementation.api.SshPublicKey;
import com.microsoft.azure.management.compute.implementation.api.NetworkInterfaceReference;
import com.microsoft.azure.management.network.NetworkInterface;
import com.microsoft.azure.management.network.PublicIpAddress;
import com.microsoft.azure.management.network.implementation.NetworkManager;
import com.microsoft.azure.management.resources.fluentcore.arm.ResourceUtils;
import com.microsoft.azure.management.resources.fluentcore.arm.models.implementation.GroupableResourceImpl;
import com.microsoft.azure.management.resources.fluentcore.utils.Utils;
import com.microsoft.azure.management.resources.implementation.ResourceManager;
import com.microsoft.azure.management.storage.StorageAccount;
import com.microsoft.azure.management.storage.implementation.StorageManager;
import com.microsoft.azure.management.storage.implementation.api.AccountType;
import com.microsoft.rest.ServiceResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type representing Azure virtual machine.
 */
class VirtualMachineImpl
        extends GroupableResourceImpl<VirtualMachine, VirtualMachineInner, VirtualMachineImpl>
        implements
            VirtualMachine,
            VirtualMachine.DefinitionBlank,
            VirtualMachine.DefinitionWithGroup,
            VirtualMachine.DefinitionWithPrimaryNetworkInterface,
            VirtualMachine.DefinitionWithOS,
            VirtualMachine.DefinitionWithMarketplaceImage,
            VirtualMachine.DefinitionWithOSType,
            VirtualMachine.DefinitionWithRootUserName,
            VirtualMachine.DefinitionWithAdminUserName,
            VirtualMachine.DefinitionLinuxCreatable,
            VirtualMachine.DefinitionWindowsCreatable,
            VirtualMachine.DefinitionCreatable,
            VirtualMachine.ConfigureDataDisk,
            VirtualMachine.ConfigureNewDataDiskWithStoreAt,
            VirtualMachine.ConfigureNewDataDisk,
            VirtualMachine.ConfigureExistingDataDisk {
    // Clients
    private final VirtualMachinesInner client;
    private final AvailabilitySets availabilitySets;
    private final StorageManager storageManager;
    private final NetworkManager networkManager;
    // the name of the virtual machine
    private final String vmName;
    // used to generate unique name for any dependency resources
    private final String randomId;
    // unique key of a creatable storage account to be used for virtual machine child resources that
    // requires storage [OS disk, data disk etc..]
    private String creatableStorageAccountKey;
    // unique key of a creatable availability set that this virtual machine to put
    private String creatableAvailabilitySetKey;
    // unique key of a creatable network interface that needs to be used as virtual machine's primary network interface
    private String creatableNetworkInterfaceKey;
    // reference to an existing storage account to be used for virtual machine child resources that
    // requires storage [OS disk, data disk etc..]
    private StorageAccount existingStorageAccountToAssociate;
    // reference to an existing availability set that this virtual machine to put
    private AvailabilitySet existingAvailabilitySetToAssociate;
    // reference to an existing network interface that needs to be used as virtual machine's primary network interface
    private NetworkInterface existingNetworkInterfaceToAssociate;

    VirtualMachineImpl(String name,
                       VirtualMachineInner innerModel,
                       VirtualMachinesInner client,
                       AvailabilitySets availabilitySets,
                       final ResourceManager resourceManager,
                       final StorageManager storageManager,
                       final NetworkManager networkManager) {
        super(name, innerModel, resourceManager.resourceGroups());
        this.client = client;
        this.availabilitySets = availabilitySets;
        this.storageManager = storageManager;
        this.networkManager = networkManager;
        this.vmName = name;
        this.randomId = Utils.randomId(this.vmName);
    }

    /**************************************************.
     * Verbs
     **************************************************/

    @Override
    public VirtualMachine refresh() throws Exception {
        ServiceResponse<VirtualMachineInner> response =
                this.client.get(this.resourceGroupName(), this.name());
        this.setInner(response.getBody());
        return this;
    }

    @Override
    public VirtualMachine create() throws Exception {
        super.creatablesCreate();
        return this;
    }

    /**************************************************.
     * Setters
     **************************************************/

    // Virtual machine network interface specific fluent methods
    //

    @Override
    public DefinitionWithOS withNewPrimaryNetworkInterface(NetworkInterface.DefinitionCreatable creatable) {
        this.creatableNetworkInterfaceKey = creatable.key();
        this.addCreatableDependency(creatable);
        return this;
    }

    @Override
    public DefinitionWithOS withNewPrimaryNetworkInterface(String name) {
        return withNewPrimaryNetworkInterface(prepareNetworkInterface(name));
    }

    public DefinitionWithOS withNewPrimaryNetworkInterface(String name, String publicDnsNameLabel) {
        NetworkInterface.DefinitionCreatable definitionCreatable = prepareNetworkInterface(name)
                .withNewPublicIpAddress(publicDnsNameLabel);
        return withNewPrimaryNetworkInterface(definitionCreatable);
    }

    @Override
    public DefinitionWithOS withExistingPrimaryNetworkInterface(NetworkInterface networkInterface) {
        this.existingNetworkInterfaceToAssociate = networkInterface;
        return this;
    }

    // Virtual machine image specific fluent methods
    //

    @Override
    public DefinitionWithMarketplaceImage withMarketplaceImage() {
        return this;
    }

    @Override
    public DefinitionWithOSType withStoredImage(String imageUrl) {
        VirtualHardDisk userImageVhd = new VirtualHardDisk();
        userImageVhd.setUri(imageUrl);
        this.inner().storageProfile().osDisk().setCreateOption(DiskCreateOptionTypes.FROM_IMAGE);
        this.inner().storageProfile().osDisk().setImage(userImageVhd);
        return this;
    }

    @Override
    public DefinitionCreatable withOSDisk(String osDiskUrl, OperatingSystemTypes osType) {
        VirtualHardDisk osDisk = new VirtualHardDisk();
        osDisk.setUri(osDiskUrl);
        this.inner().storageProfile().osDisk().setCreateOption(DiskCreateOptionTypes.ATTACH);
        this.inner().storageProfile().osDisk().setVhd(osDisk);
        this.inner().storageProfile().osDisk().setOsType(osType);
        return this;
    }

    @Override
    public DefinitionWithOSType version(ImageReference imageReference) {
        this.inner().storageProfile().osDisk().setCreateOption(DiskCreateOptionTypes.FROM_IMAGE);
        this.inner().storageProfile().setImageReference(imageReference);
        return this;
    }

    @Override
    public DefinitionWithOSType latest(String publisher, String offer, String sku) {
        ImageReference imageReference = new ImageReference();
        imageReference.setPublisher(publisher);
        imageReference.setOffer(offer);
        imageReference.setSku(sku);
        imageReference.setVersion("latest");
        return version(imageReference);
    }

    @Override
    public DefinitionWithOSType popular(KnownVirtualMachineImage knownImage) {
        return version(knownImage.imageReference());
    }

    // Virtual machine operating system type fluent methods
    //

    @Override
    public DefinitionWithRootUserName withLinuxOS() {
        OSDisk osDisk = this.inner().storageProfile().osDisk();
        if (isStoredImage(osDisk)) {
            // For platform image osType should be null, azure will pick it from the image metadata.
            osDisk.setOsType(OperatingSystemTypes.LINUX);
        }
        this.inner().osProfile().setLinuxConfiguration(new LinuxConfiguration());
        return this;
    }

    @Override
    public DefinitionWithAdminUserName withWindowsOS() {
        OSDisk osDisk = this.inner().storageProfile().osDisk();
        if (isStoredImage(osDisk)) {
            // For platform image osType should be null, azure will pick it from the image metadata.
            osDisk.setOsType(OperatingSystemTypes.WINDOWS);
        }
        this.inner().osProfile().setWindowsConfiguration(new WindowsConfiguration());
        // sets defaults for "Stored(User)Image" or "VM(Platform)Image"
        this.inner().osProfile().windowsConfiguration().setProvisionVMAgent(true);
        this.inner().osProfile().windowsConfiguration().setEnableAutomaticUpdates(true);
        return this;
    }

    // Virtual machine user name fluent methods
    //

    @Override
    public DefinitionLinuxCreatable withRootUserName(String rootUserName) {
        this.inner().osProfile().setAdminUsername(rootUserName);
        return this;
    }

    @Override
    public DefinitionWindowsCreatable withAdminUserName(String adminUserName) {
        this.inner().osProfile().setAdminUsername(adminUserName);
        return this;
    }

    // Virtual machine optional fluent methods
    //

    @Override
    public DefinitionLinuxCreatable withSsh(String publicKeyData) {
        OSProfile osProfile = this.inner().osProfile();
        if (osProfile.linuxConfiguration().ssh() == null) {
            SshConfiguration sshConfiguration = new SshConfiguration();
            sshConfiguration.setPublicKeys(new ArrayList<SshPublicKey>());
            osProfile.linuxConfiguration().setSsh(sshConfiguration);
        }
        SshPublicKey sshPublicKey = new SshPublicKey();
        sshPublicKey.setKeyData(publicKeyData);
        sshPublicKey.setPath("/home/" + osProfile.adminUsername() + "/.ssh/authorized_keys");
        osProfile.linuxConfiguration().ssh().publicKeys().add(sshPublicKey);
        return this;
    }

    @Override
    public DefinitionWindowsCreatable disableVMAgent() {
        this.inner().osProfile().windowsConfiguration().setProvisionVMAgent(false);
        return this;
    }

    @Override
    public DefinitionWindowsCreatable disableAutoUpdate() {
        this.inner().osProfile().windowsConfiguration().setEnableAutomaticUpdates(false);
        return this;
    }

    @Override
    public DefinitionWindowsCreatable withTimeZone(String timeZone) {
        this.inner().osProfile().windowsConfiguration().setTimeZone(timeZone);
        return this;
    }

    @Override
    public DefinitionWindowsCreatable withWinRM(WinRMListener listener) {
        if (this.inner().osProfile().windowsConfiguration().winRM() == null) {
            WinRMConfiguration winRMConfiguration = new WinRMConfiguration();
            this.inner().osProfile().windowsConfiguration().setWinRM(winRMConfiguration);
        }

        this.inner().osProfile()
                .windowsConfiguration()
                .winRM()
                .listeners()
                .add(listener);
        return this;
    }

    @Override
    public DefinitionCreatable withPassword(String password) {
        this.inner().osProfile().setAdminPassword(password);
        return this;
    }

    @Override
    public DefinitionCreatable withSize(String sizeName) {
        this.inner().hardwareProfile().setVmSize(sizeName);
        return this;
    }

    @Override
    public DefinitionCreatable withSize(VirtualMachineSizeTypes size) {
        this.inner().hardwareProfile().setVmSize(size.toString());
        return this;
    }

    @Override
    public DefinitionCreatable withOSDiskCaching(CachingTypes cachingType) {
        this.inner().storageProfile().osDisk().setCaching(cachingType);
        return this;
    }

    @Override
    public DefinitionCreatable withOSDiskVhdLocation(String containerName, String vhdName) {
        VirtualHardDisk osVhd = new VirtualHardDisk();
        osVhd.setUri(temporaryBlobUrl(containerName, vhdName));
        this.inner().storageProfile().osDisk().setVhd(osVhd);
        return this;
    }

    @Override
    public DefinitionCreatable withOSDiskEncryptionSettings(DiskEncryptionSettings settings) {
        this.inner().storageProfile().osDisk().setEncryptionSettings(settings);
        return this;
    }

    @Override
    public DefinitionCreatable withOSDiskSizeInGB(Integer size) {
        this.inner().storageProfile().osDisk().setDiskSizeGB(size);
        return this;
    }

    @Override
    public DefinitionCreatable withOSDiskName(String name) {
        this.inner().storageProfile().osDisk().setName(name);
        return this;
    }

    // Virtual machine optional data disk fluent methods
    //

    @Override
    public ConfigureDataDisk<DefinitionCreatable> withLun(Integer lun) {
        DataDisk dataDisk = currentDataDisk();
        dataDisk.setLun(lun);
        return this;
    }

    @Override
    public ConfigureDataDisk<DefinitionCreatable> withCaching(CachingTypes cachingType) {
        DataDisk dataDisk = currentDataDisk();
        dataDisk.setCaching(cachingType);
        return this;
    }

    @Override
    public DefinitionCreatable attach() {
        return this;
    }

    @Override
    public ConfigureDataDisk<DefinitionCreatable> storeAt(String storageAccountName, String containerName, String vhdName) {
        DataDisk dataDisk = currentDataDisk();
        dataDisk.setVhd(new VirtualHardDisk());
        // URL points to where the new data disk needs to be stored
        dataDisk.vhd().setUri(blobUrl(storageAccountName, containerName, vhdName));
        return this;
    }

    @Override
    public ConfigureNewDataDiskWithStoreAt<DefinitionCreatable> withSizeInGB(Integer sizeInGB) {
        DataDisk dataDisk = currentDataDisk();
        dataDisk.setDiskSizeGB(sizeInGB);
        return this;
    }

    @Override
    public ConfigureDataDisk<DefinitionCreatable> from(String storageAccountName, String containerName, String vhdName) {
        DataDisk dataDisk = currentDataDisk();
        dataDisk.setVhd(new VirtualHardDisk());
        //URL points to an existing data disk to be attached
        dataDisk.vhd().setUri(blobUrl(storageAccountName, containerName, vhdName));
        return this;
    }

    @Override
    public ConfigureNewDataDisk<DefinitionCreatable> defineNewDataDisk(String name) {
        DataDisk dataDisk = prepareNewDataDisk();
        dataDisk.setName(name);
        dataDisk.setCreateOption(DiskCreateOptionTypes.EMPTY);
        return this;
    }

    @Override
    public ConfigureExistingDataDisk<DefinitionCreatable> defineExistingDataDisk(String name) {
        DataDisk dataDisk = prepareNewDataDisk();
        dataDisk.setName(name);
        dataDisk.setCreateOption(DiskCreateOptionTypes.ATTACH);
        return this;
    }

    @Override
    public DefinitionCreatable withNewDataDisk(Integer sizeInGB) {
        DataDisk dataDisk = prepareNewDataDisk();
        dataDisk.setDiskSizeGB(sizeInGB);
        dataDisk.setCreateOption(DiskCreateOptionTypes.EMPTY);
        return this;
    }

    @Override
    public DefinitionCreatable withExistingDataDisk(String storageAccountName, String containerName, String vhdName) {
        DataDisk dataDisk = prepareNewDataDisk();
        VirtualHardDisk diskVhd = new VirtualHardDisk();
        diskVhd.setUri(blobUrl(storageAccountName, containerName, vhdName));
        dataDisk.setVhd(diskVhd);
        dataDisk.setCreateOption(DiskCreateOptionTypes.ATTACH);
        return this;
    }

    // Virtual machine optional storage account fluent methods
    //

    @Override
    public DefinitionCreatable withNewStorageAccount(StorageAccount.DefinitionCreatable creatable) {
        // This method's effect is NOT additive.
        if (this.creatableStorageAccountKey == null) {
            this.creatableStorageAccountKey = creatable.key();
            this.addCreatableDependency(creatable);
        }
        return this;
    }

    @Override
    public DefinitionCreatable withNewStorageAccount(String name) {
        StorageAccount.DefinitionWithGroup definitionWithGroup = this.storageManager
                .storageAccounts()
                .define(name)
                .withRegion(this.region());
        StorageAccount.DefinitionCreatable definitionAfterGroup;
        if (this.newGroup != null) {
            definitionAfterGroup = definitionWithGroup.withNewGroup(this.newGroup);
        } else {
            definitionAfterGroup = definitionWithGroup.withExistingGroup(this.resourceGroupName());
        }
        return withNewStorageAccount(definitionAfterGroup.withAccountType(AccountType.STANDARD_GRS));
    }

    @Override
    public DefinitionCreatable withExistingStorageAccount(StorageAccount storageAccount) {
        this.existingStorageAccountToAssociate = storageAccount;
        return this;
    }

    // Virtual machine optional availability set fluent methods
    //

    @Override
    public DefinitionCreatable withNewAvailabilitySet(AvailabilitySet.DefinitionCreatable creatable) {
        // This method's effect is NOT additive.
        if (this.creatableAvailabilitySetKey == null) {
            this.creatableAvailabilitySetKey = creatable.key();
            this.addCreatableDependency(creatable);
        }
        return this;
    }

    @Override
    public DefinitionCreatable withNewAvailabilitySet(String name) {
        return withNewAvailabilitySet(availabilitySets.define(name)
                .withRegion(region())
                .withExistingGroup(this.resourceGroupName())
        );
    }

    @Override
    public DefinitionCreatable withExistingAvailabilitySet(AvailabilitySet availabilitySet) {
        this.existingAvailabilitySetToAssociate = availabilitySet;
        return this;
    }

    /**************************************************.
     * Getters
     **************************************************/

    @Override
    public String computerName() {
        return inner().osProfile().computerName();
    }

    @Override
    public String size() {
        return inner().hardwareProfile().vmSize();
    }

    @Override
    public OperatingSystemTypes osType() {
        return inner().storageProfile().osDisk().osType();
    }

    @Override
    public String osDiskVhdUri() {
        return inner().storageProfile().osDisk().vhd().uri();
    }

    @Override
    public CachingTypes osDiskCachingType() {
        return inner().storageProfile().osDisk().caching();
    }

    @Override
    public Integer osDiskSize() {
        return inner().storageProfile().osDisk().diskSizeGB();
    }

    @Override
    public List<DataDisk> dataDisks() {
        return inner().storageProfile().dataDisks();
    }

    @Override
    public NetworkInterface primaryNetworkInterface() throws CloudException, IOException {
        String primaryNicId = primaryNetworkInterfaceId();
        return this.networkManager.networkInterfaces()
                .get(ResourceUtils.groupFromResourceId(primaryNicId), ResourceUtils.nameFromResourceId(primaryNicId));
    }

    @Override
    public PublicIpAddress primaryPublicIpAddress()  throws CloudException, IOException {
        return this.primaryNetworkInterface().publicIpAddress();
    }

    @Override
    public List<String> networkInterfaceIds() {
        List nicIds = new ArrayList();
        for (NetworkInterfaceReference nicRef : inner().networkProfile().networkInterfaces()) {
            nicIds.add(nicRef.id());
        }
        return nicIds;
    }

    @Override
    public String primaryNetworkInterfaceId() {
        for (NetworkInterfaceReference nicRef : inner().networkProfile().networkInterfaces()) {
            if (nicRef.primary() != null && nicRef.primary()) {
                return nicRef.id();
            }
        }
        return null;
    }

    @Override
    public String availabilitySetId() {
        if (inner().availabilitySet() != null) {
            return inner().availabilitySet().id();
        }
        return null;
    }

    @Override
    public String provisioningState() {
        return inner().provisioningState();
    }

    @Override
    public VirtualMachineInstanceView instanceView() {
        return inner().instanceView();
    }

    @Override
    public String licenseType() {
        return inner().licenseType();
    }

    @Override
    public List<VirtualMachineExtensionInner> resources() {
        return inner().resources();
    }

    @Override
    public Plan plan() {
        return inner().plan();
    }

    @Override
    public StorageProfile storageProfile() {
        return inner().storageProfile();
    }

    @Override
    public OSProfile osProfile() {
        return inner().osProfile();
    }

    @Override
    public DiagnosticsProfile diagnosticsProfile() {
        return inner().diagnosticsProfile();
    }


    /**************************************************.
     * CreatableImpl::createResource
     **************************************************/

    @Override
    protected void createResource() throws Exception {
        if (isInCreateMode()) {
            setOSDiskAndOSProfileDefaults();
            setHardwareProfileDefaults();
        }
        setDataDisksDefaults();
        handleStorageSettings();
        handleNetworkSettings();
        handleAvailabilitySettings();

        ServiceResponse<VirtualMachineInner> serviceResponse = this.client.createOrUpdate(this.resourceGroupName(), this.vmName, this.inner());
        this.setInner(serviceResponse.getBody());
    }

    /**************************************************.
     * Helper methods
     **************************************************/

    /**
     * @param prefix the prefix
     * @return a random value (derived from the resource and resource group name) with the given prefix
     */
    private String nameWithPrefix(String prefix) {
        return prefix + "-" + this.randomId + "-" + this.resourceGroupName();
    }

    private void setOSDiskAndOSProfileDefaults() {
        if (!isInCreateMode()) {
            return;
        }

        OSDisk osDisk = this.inner().storageProfile().osDisk();
        if (isOSDiskFromImage(osDisk)) {
            if (osDisk.vhd() == null) {
                // Sets the OS disk VHD for "UserImage" and "VM(Platform)Image"
                withOSDiskVhdLocation("vhds", this.vmName + "-os-disk-" + UUID.randomUUID().toString() + ".vhd");
            }
            OSProfile osProfile = this.inner().osProfile();
            if (osDisk.osType() == OperatingSystemTypes.LINUX) {
                if (osProfile.linuxConfiguration() == null) {
                    osProfile.setLinuxConfiguration(new LinuxConfiguration());
                }
                this.inner().osProfile().linuxConfiguration().setDisablePasswordAuthentication(osProfile.adminPassword() == null);
            }
        }

        if (osDisk.caching() == null) {
            withOSDiskCaching(CachingTypes.READ_WRITE);
        }

        if (osDisk.name() == null) {
            withOSDiskName(this.vmName + "-os-disk");
        }
    }

    private void setHardwareProfileDefaults() {
        if (!isInCreateMode()) {
            return;
        }

        HardwareProfile hardwareProfile = this.inner().hardwareProfile();
        if (hardwareProfile.vmSize() == null) {
            hardwareProfile.setVmSize(VirtualMachineSizeTypes.BASIC_A0);
        }
    }

    private void setDataDisksDefaults() {
        List<DataDisk> dataDisks = this.inner().storageProfile().dataDisks();
        if (dataDisks.size() == 0) {
            this.inner().storageProfile().setDataDisks(null);
            return;
        }

        List<Integer> usedLuns = new ArrayList<>();
        for (DataDisk dataDisk : dataDisks) {
            if (dataDisk.lun() != -1) {
                usedLuns.add(dataDisk.lun());
            }
        }

        for (DataDisk dataDisk : dataDisks) {
            if (dataDisk.lun() == -1) {
                Integer i = 0;
                while (usedLuns.contains(i)) {
                    i++;
                }
                dataDisk.setLun(i);
                usedLuns.add(i);
            }

            if (dataDisk.vhd() == null) {
                VirtualHardDisk diskVhd = new VirtualHardDisk();
                diskVhd.setUri(temporaryBlobUrl("vhds",
                        this.vmName + "-data-disk-" + dataDisk.lun() + "-" + UUID.randomUUID().toString() + ".vhd"));
                dataDisk.setVhd(diskVhd);
            }

            if (dataDisk.name() == null) {
                dataDisk.setName(this.vmName + "-data-disk-" + dataDisk.lun());
            }

            if (dataDisk.caching() == null) {
                dataDisk.setCaching(CachingTypes.READ_WRITE);
            }
        }
    }

    private void handleStorageSettings() throws Exception {
        StorageAccount storageAccount = null;
        if (this.creatableStorageAccountKey != null) {
            storageAccount = (StorageAccount) this.createdResource(this.creatableStorageAccountKey);
        } else if (this.existingStorageAccountToAssociate != null) {
            storageAccount = this.existingStorageAccountToAssociate;
        } else if (requiresImplicitStorageAccountCreation()) {
            storageAccount = this.storageManager.storageAccounts()
                    .define(nameWithPrefix("stg"))
                    .withRegion(this.region())
                    .withExistingGroup(this.resourceGroupName())
                    .create();
        }

        if (storageAccount != null) {
            replaceTemporaryBlobUrls(storageAccount);
        }
    }

    private void handleNetworkSettings() {
        if (isInCreateMode()) {
            NetworkInterface networkInterface = null;
            if (this.creatableNetworkInterfaceKey != null) {
                networkInterface = (NetworkInterface) this.createdResource(this.creatableNetworkInterfaceKey);
            } else if (this.existingNetworkInterfaceToAssociate != null) {
                networkInterface = this.existingNetworkInterfaceToAssociate;
            }

            if (networkInterface != null) {
                NetworkInterfaceReference nicReference = new NetworkInterfaceReference();
                nicReference.setPrimary(true);
                nicReference.setId(networkInterface.id());
                this.inner().networkProfile().networkInterfaces().add(nicReference);
            }
        }
    }

    private void handleAvailabilitySettings() {
        if (!isInCreateMode()) {
            return;
        }

        AvailabilitySet availabilitySet = null;
        if (this.creatableAvailabilitySetKey != null) {
            availabilitySet = (AvailabilitySet) this.createdResource(this.creatableAvailabilitySetKey);
        } else if (this.existingAvailabilitySetToAssociate != null) {
            availabilitySet = this.existingAvailabilitySetToAssociate;
        }

        if (availabilitySet != null) {
            this.inner().availabilitySet().setId(availabilitySet.id());
        }
    }

    private boolean requiresImplicitStorageAccountCreation() {
        if (this.creatableStorageAccountKey == null && this.existingStorageAccountToAssociate == null) {
            if (this.isInCreateMode()) {
                if (isOSDiskFromImage(this.inner().storageProfile().osDisk())) {
                    return true;
                }
            }
            for (DataDisk dataDisk : this.inner().storageProfile().dataDisks()) {
                if (dataDisk.createOption() != DiskCreateOptionTypes.ATTACH) {
                    if (isTemporaryBlobUrl(dataDisk.vhd().uri())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isStoredImage(OSDisk osDisk) {
        return osDisk.image() != null;
    }

    private boolean isOSDiskAttached(OSDisk osDisk) {
        return osDisk.createOption() == DiskCreateOptionTypes.ATTACH;
    }

    private boolean isOSDiskFromImage(OSDisk osDisk) {
        return !isOSDiskAttached(osDisk);
    }

    private String blobUrl(String storageAccountName, String containerName, String blobName) {
        return  "https://" + storageAccountName + ".blob.core.windows.net" + "/" + containerName + "/" + blobName;
    }

    private String temporaryBlobUrl(String containerName, String blobName) {
        return "{storage-base-url}" + "/" + containerName + "/" + blobName;
    }

    private boolean isTemporaryBlobUrl(String blobUrl) {
        return blobUrl.startsWith("{storage-base-url}");
    }

    private void replaceTemporaryBlobUrls(StorageAccount storageAccount) {
        if (this.isInCreateMode()) {
            if (isOSDiskFromImage(this.inner().storageProfile().osDisk())) {
                String uri = this.inner()
                        .storageProfile()
                        .osDisk().vhd().uri()
                        .replaceFirst("\\{storage-base-url}", storageAccount.endPoints().primary().blob());
                this.inner().storageProfile().osDisk().vhd().setUri(uri);
            }
        }
        for (DataDisk dataDisk : this.inner().storageProfile().dataDisks()) {
            if (dataDisk.createOption() != DiskCreateOptionTypes.ATTACH) {
                if (isTemporaryBlobUrl(dataDisk.vhd().uri())) {
                    String uri = dataDisk.vhd().uri()
                            .replaceFirst("\\{storage-base-url}", storageAccount.endPoints().primary().blob());
                    dataDisk.vhd().setUri(uri);
                }
            }
        }
    }

    private DataDisk prepareNewDataDisk() {
        DataDisk dataDisk = new DataDisk();
        dataDisk.setLun(-1);
        this.inner().storageProfile().dataDisks().add(dataDisk);
        return dataDisk;
    }

    private DataDisk currentDataDisk() {
        List<DataDisk> dataDisks = this.inner().storageProfile().dataDisks();
        return dataDisks.get(dataDisks.size() - 1);
    }

    private NetworkInterface.DefinitionWithPublicIpAddress prepareNetworkInterface(String name) {
        NetworkInterface.DefinitionWithGroup definitionWithGroup = this.networkManager
                .networkInterfaces()
                .define(name)
                .withRegion(this.region());
        NetworkInterface.DefinitionWithNetwork definitionWithNetwork;
        if (this.newGroup != null) {
            definitionWithNetwork = definitionWithGroup.withNewGroup(this.newGroup);
        } else {
            definitionWithNetwork = definitionWithGroup.withExistingGroup(this.resourceGroupName());
        }
        return definitionWithNetwork
                .withNewNetwork("vnet" + name)
                .withPrivateIpAddressDynamic();
    }
}
