// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.monitor.models;

import com.azure.core.annotation.Fluent;
import com.azure.management.monitor.MetricNamespaceName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The MetricNamespace model.
 */
@Fluent
public final class MetricNamespaceInner {
    /*
     * The ID of the metricNamespace.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * The type of the namespace.
     */
    @JsonProperty(value = "type")
    private String type;

    /*
     * The name of the namespace.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * Properties which include the fully qualified namespace name.
     */
    @JsonProperty(value = "properties")
    private MetricNamespaceName properties;

    /**
     * Get the id property: The ID of the metricNamespace.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: The ID of the metricNamespace.
     * 
     * @param id the id value to set.
     * @return the MetricNamespaceInner object itself.
     */
    public MetricNamespaceInner withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: The type of the namespace.
     * 
     * @return the type value.
     */
    public String type() {
        return this.type;
    }

    /**
     * Set the type property: The type of the namespace.
     * 
     * @param type the type value to set.
     * @return the MetricNamespaceInner object itself.
     */
    public MetricNamespaceInner withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the name property: The name of the namespace.
     * 
     * @return the name value.
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name property: The name of the namespace.
     * 
     * @param name the name value to set.
     * @return the MetricNamespaceInner object itself.
     */
    public MetricNamespaceInner withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the properties property: Properties which include the fully
     * qualified namespace name.
     * 
     * @return the properties value.
     */
    public MetricNamespaceName properties() {
        return this.properties;
    }

    /**
     * Set the properties property: Properties which include the fully
     * qualified namespace name.
     * 
     * @param properties the properties value to set.
     * @return the MetricNamespaceInner object itself.
     */
    public MetricNamespaceInner withProperties(MetricNamespaceName properties) {
        this.properties = properties;
        return this;
    }
}
