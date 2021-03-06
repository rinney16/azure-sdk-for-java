/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.network.v2019_02_01;

import com.microsoft.azure.SubResource;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Effective network security group.
 */
public class EffectiveNetworkSecurityGroup {
    /**
     * The ID of network security group that is applied.
     */
    @JsonProperty(value = "networkSecurityGroup")
    private SubResource networkSecurityGroup;

    /**
     * Associated resources.
     */
    @JsonProperty(value = "association")
    private EffectiveNetworkSecurityGroupAssociation association;

    /**
     * A collection of effective security rules.
     */
    @JsonProperty(value = "effectiveSecurityRules")
    private List<EffectiveNetworkSecurityRule> effectiveSecurityRules;

    /**
     * Mapping of tags to list of IP Addresses included within the tag.
     */
    @JsonProperty(value = "tagMap")
    private Map<String, List<String>> tagMap;

    /**
     * Get the ID of network security group that is applied.
     *
     * @return the networkSecurityGroup value
     */
    public SubResource networkSecurityGroup() {
        return this.networkSecurityGroup;
    }

    /**
     * Set the ID of network security group that is applied.
     *
     * @param networkSecurityGroup the networkSecurityGroup value to set
     * @return the EffectiveNetworkSecurityGroup object itself.
     */
    public EffectiveNetworkSecurityGroup withNetworkSecurityGroup(SubResource networkSecurityGroup) {
        this.networkSecurityGroup = networkSecurityGroup;
        return this;
    }

    /**
     * Get associated resources.
     *
     * @return the association value
     */
    public EffectiveNetworkSecurityGroupAssociation association() {
        return this.association;
    }

    /**
     * Set associated resources.
     *
     * @param association the association value to set
     * @return the EffectiveNetworkSecurityGroup object itself.
     */
    public EffectiveNetworkSecurityGroup withAssociation(EffectiveNetworkSecurityGroupAssociation association) {
        this.association = association;
        return this;
    }

    /**
     * Get a collection of effective security rules.
     *
     * @return the effectiveSecurityRules value
     */
    public List<EffectiveNetworkSecurityRule> effectiveSecurityRules() {
        return this.effectiveSecurityRules;
    }

    /**
     * Set a collection of effective security rules.
     *
     * @param effectiveSecurityRules the effectiveSecurityRules value to set
     * @return the EffectiveNetworkSecurityGroup object itself.
     */
    public EffectiveNetworkSecurityGroup withEffectiveSecurityRules(List<EffectiveNetworkSecurityRule> effectiveSecurityRules) {
        this.effectiveSecurityRules = effectiveSecurityRules;
        return this;
    }

    /**
     * Get mapping of tags to list of IP Addresses included within the tag.
     *
     * @return the tagMap value
     */
    public Map<String, List<String>> tagMap() {
        return this.tagMap;
    }

    /**
     * Set mapping of tags to list of IP Addresses included within the tag.
     *
     * @param tagMap the tagMap value to set
     * @return the EffectiveNetworkSecurityGroup object itself.
     */
    public EffectiveNetworkSecurityGroup withTagMap(Map<String, List<String>> tagMap) {
        this.tagMap = tagMap;
        return this;
    }

}
