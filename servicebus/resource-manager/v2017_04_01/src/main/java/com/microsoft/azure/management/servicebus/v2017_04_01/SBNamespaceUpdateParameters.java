/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.servicebus.v2017_04_01;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * Description of a namespace resource.
 */
@JsonFlatten
public class SBNamespaceUpdateParameters extends ResourceNamespacePatch {
    /**
     * Properties of Sku.
     */
    @JsonProperty(value = "sku")
    private SBSku sku;

    /**
     * Provisioning state of the namespace.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private String provisioningState;

    /**
     * The time the namespace was created.
     */
    @JsonProperty(value = "properties.createdAt", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime createdAt;

    /**
     * The time the namespace was updated.
     */
    @JsonProperty(value = "properties.updatedAt", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime updatedAt;

    /**
     * Endpoint you can use to perform Service Bus operations.
     */
    @JsonProperty(value = "properties.serviceBusEndpoint", access = JsonProperty.Access.WRITE_ONLY)
    private String serviceBusEndpoint;

    /**
     * Identifier for Azure Insights metrics.
     */
    @JsonProperty(value = "properties.metricId", access = JsonProperty.Access.WRITE_ONLY)
    private String metricId;

    /**
     * Get properties of Sku.
     *
     * @return the sku value
     */
    public SBSku sku() {
        return this.sku;
    }

    /**
     * Set properties of Sku.
     *
     * @param sku the sku value to set
     * @return the SBNamespaceUpdateParameters object itself.
     */
    public SBNamespaceUpdateParameters withSku(SBSku sku) {
        this.sku = sku;
        return this;
    }

    /**
     * Get provisioning state of the namespace.
     *
     * @return the provisioningState value
     */
    public String provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get the time the namespace was created.
     *
     * @return the createdAt value
     */
    public DateTime createdAt() {
        return this.createdAt;
    }

    /**
     * Get the time the namespace was updated.
     *
     * @return the updatedAt value
     */
    public DateTime updatedAt() {
        return this.updatedAt;
    }

    /**
     * Get endpoint you can use to perform Service Bus operations.
     *
     * @return the serviceBusEndpoint value
     */
    public String serviceBusEndpoint() {
        return this.serviceBusEndpoint;
    }

    /**
     * Get identifier for Azure Insights metrics.
     *
     * @return the metricId value
     */
    public String metricId() {
        return this.metricId;
    }

}
