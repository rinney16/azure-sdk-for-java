// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for
// license information.
// 
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.graphrbac.models;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.management.CloudException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Permissions.
 */
public final class PermissionsInner {
    /**
     * The proxy service used to perform REST calls.
     */
    private PermissionsService service;

    /**
     * The service client containing this operation class.
     */
    private AuthorizationManagementClientImpl client;

    /**
     * Initializes an instance of PermissionsInner.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public PermissionsInner(AuthorizationManagementClientImpl client) {
        this.service = RestProxy.create(PermissionsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AuthorizationManagementClientPermissions to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AuthorizationManagementClientPermissions")
    private interface PermissionsService {
        @Get("/subscriptions/{subscriptionId}/resourcegroups/{resourceGroupName}/providers/Microsoft.Authorization/permissions")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<PermissionGetResultInner>> listByResourceGroup(@HostParam("$host") String host, @PathParam("resourceGroupName") String resourceGroupName, @PathParam("subscriptionId") String subscriptionId, @QueryParam("api-version") String apiVersion);

        @Get("/subscriptions/{subscriptionId}/resourcegroups/{resourceGroupName}/providers/{resourceProviderNamespace}/{parentResourcePath}/{resourceType}/{resourceName}/providers/Microsoft.Authorization/permissions")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<PermissionGetResultInner>> listForResource(@HostParam("$host") String host, @PathParam("resourceGroupName") String resourceGroupName, @PathParam("resourceProviderNamespace") String resourceProviderNamespace, @PathParam(value = "parentResourcePath", encoded = true) String parentResourcePath, @PathParam(value = "resourceType", encoded = true) String resourceType, @PathParam("resourceName") String resourceName, @PathParam("subscriptionId") String subscriptionId, @QueryParam("api-version") String apiVersion);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<PermissionGetResultInner>> listForResourceGroupNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<PermissionGetResultInner>> listForResourceNext(@PathParam(value = "nextLink", encoded = true) String nextLink);
    }

    /**
     * Gets all permissions the caller has for a resource group.
     * 
     * @param resourceGroupName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<PermissionInner>> listByResourceGroupSinglePageAsync(String resourceGroupName) {
        return service.listByResourceGroup(this.client.getHost(), resourceGroupName, this.client.getSubscriptionId(), this.client.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Gets all permissions the caller has for a resource group.
     * 
     * @param resourceGroupName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<PermissionInner> listByResourceGroupAsync(String resourceGroupName) {
        return new PagedFlux<>(
            () -> listByResourceGroupSinglePageAsync(resourceGroupName),
            nextLink -> listForResourceGroupNextSinglePageAsync(nextLink));
    }

    /**
     * Gets all permissions the caller has for a resource group.
     * 
     * @param resourceGroupName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<PermissionInner> listByResourceGroup(String resourceGroupName) {
        return new PagedIterable<>(listByResourceGroupAsync(resourceGroupName));
    }

    /**
     * Gets all permissions the caller has for a resource.
     * 
     * @param resourceGroupName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceProviderNamespace MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parentResourcePath MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceType MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<PermissionInner>> listForResourceSinglePageAsync(String resourceGroupName, String resourceProviderNamespace, String parentResourcePath, String resourceType, String resourceName) {
        return service.listForResource(this.client.getHost(), resourceGroupName, resourceProviderNamespace, parentResourcePath, resourceType, resourceName, this.client.getSubscriptionId(), this.client.getApiVersion()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Gets all permissions the caller has for a resource.
     * 
     * @param resourceGroupName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceProviderNamespace MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parentResourcePath MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceType MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<PermissionInner> listForResourceAsync(String resourceGroupName, String resourceProviderNamespace, String parentResourcePath, String resourceType, String resourceName) {
        return new PagedFlux<>(
            () -> listForResourceSinglePageAsync(resourceGroupName, resourceProviderNamespace, parentResourcePath, resourceType, resourceName),
            nextLink -> listForResourceNextSinglePageAsync(nextLink));
    }

    /**
     * Gets all permissions the caller has for a resource.
     * 
     * @param resourceGroupName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceProviderNamespace MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param parentResourcePath MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceType MISSING·SCHEMA-DESCRIPTION-STRING.
     * @param resourceName MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<PermissionInner> listForResource(String resourceGroupName, String resourceProviderNamespace, String parentResourcePath, String resourceType, String resourceName) {
        return new PagedIterable<>(listForResourceAsync(resourceGroupName, resourceProviderNamespace, parentResourcePath, resourceType, resourceName));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<PermissionInner>> listForResourceGroupNextSinglePageAsync(String nextLink) {
        return service.listForResourceGroupNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<PermissionInner>> listForResourceNextSinglePageAsync(String nextLink) {
        return service.listForResourceNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }
}
