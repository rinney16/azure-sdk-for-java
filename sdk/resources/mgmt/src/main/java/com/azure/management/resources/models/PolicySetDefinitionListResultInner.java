// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for
// license information.
// 
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.resources.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The PolicySetDefinitionListResult model.
 */
@Fluent
public final class PolicySetDefinitionListResultInner {
    /*
     * An array of policy set definitions.
     */
    @JsonProperty(value = "value")
    private List<PolicySetDefinitionInner> value;

    /*
     * The URL to use for getting the next set of results.
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the value property: An array of policy set definitions.
     * 
     * @return the value value.
     */
    public List<PolicySetDefinitionInner> value() {
        return this.value;
    }

    /**
     * Set the value property: An array of policy set definitions.
     * 
     * @param value the value value to set.
     * @return the PolicySetDefinitionListResultInner object itself.
     */
    public PolicySetDefinitionListResultInner withValue(List<PolicySetDefinitionInner> value) {
        this.value = value;
        return this;
    }

    /**
     * Get the nextLink property: The URL to use for getting the next set of
     * results.
     * 
     * @return the nextLink value.
     */
    public String nextLink() {
        return this.nextLink;
    }

    /**
     * Set the nextLink property: The URL to use for getting the next set of
     * results.
     * 
     * @param nextLink the nextLink value to set.
     * @return the PolicySetDefinitionListResultInner object itself.
     */
    public PolicySetDefinitionListResultInner withNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }
}
