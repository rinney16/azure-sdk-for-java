// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for
// license information.
// 
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.compute;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeyVaultAndKeyReference model.
 */
@Fluent
public final class KeyVaultAndKeyReference {
    /*
     * The vault id is an Azure Resource Manager Resource id in the form
     * /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.KeyVault/vaults/{vaultName}
     */
    @JsonProperty(value = "sourceVault", required = true)
    private SourceVault sourceVault;

    /*
     * Url pointing to a key or secret in KeyVault
     */
    @JsonProperty(value = "keyUrl", required = true)
    private String keyUrl;

    /**
     * Get the sourceVault property: The vault id is an Azure Resource Manager
     * Resource id in the form
     * /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.KeyVault/vaults/{vaultName}.
     * 
     * @return the sourceVault value.
     */
    public SourceVault sourceVault() {
        return this.sourceVault;
    }

    /**
     * Set the sourceVault property: The vault id is an Azure Resource Manager
     * Resource id in the form
     * /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.KeyVault/vaults/{vaultName}.
     * 
     * @param sourceVault the sourceVault value to set.
     * @return the KeyVaultAndKeyReference object itself.
     */
    public KeyVaultAndKeyReference withSourceVault(SourceVault sourceVault) {
        this.sourceVault = sourceVault;
        return this;
    }

    /**
     * Get the keyUrl property: Url pointing to a key or secret in KeyVault.
     * 
     * @return the keyUrl value.
     */
    public String keyUrl() {
        return this.keyUrl;
    }

    /**
     * Set the keyUrl property: Url pointing to a key or secret in KeyVault.
     * 
     * @param keyUrl the keyUrl value to set.
     * @return the KeyVaultAndKeyReference object itself.
     */
    public KeyVaultAndKeyReference withKeyUrl(String keyUrl) {
        this.keyUrl = keyUrl;
        return this;
    }
}
