// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.storage.models;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Headers;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.management.CloudException;
import java.util.Map;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * FileShares.
 */
public final class FileSharesInner {
    /**
     * The proxy service used to perform REST calls.
     */
    private FileSharesService service;

    /**
     * The service client containing this operation class.
     */
    private StorageManagementClientImpl client;

    /**
     * Initializes an instance of FileSharesInner.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    FileSharesInner(StorageManagementClientImpl client) {
        this.service = RestProxy.create(FileSharesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * StorageManagementClientFileShares to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "StorageManagementClientFileShares")
    private interface FileSharesService {
        @Headers({ "Accept: application/json", "Content-Type: application/json" })
        @Get("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/fileServices/default/shares")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<FileShareItemsInner>> list(@HostParam("$host") String host, @PathParam("resourceGroupName") String resourceGroupName, @PathParam("accountName") String accountName, @QueryParam("api-version") String apiVersion, @PathParam("subscriptionId") String subscriptionId, @QueryParam("$maxpagesize") String maxpagesize, @QueryParam("$filter") String filter);

        @Headers({ "Accept: application/json", "Content-Type: application/json" })
        @Put("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/fileServices/default/shares/{shareName}")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<FileShareInner>> create(@HostParam("$host") String host, @PathParam("resourceGroupName") String resourceGroupName, @PathParam("accountName") String accountName, @PathParam("shareName") String shareName, @QueryParam("api-version") String apiVersion, @PathParam("subscriptionId") String subscriptionId, @BodyParam("application/json") FileShareInner fileShare);

        @Headers({ "Accept: application/json", "Content-Type: application/json" })
        @Patch("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/fileServices/default/shares/{shareName}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<FileShareInner>> update(@HostParam("$host") String host, @PathParam("resourceGroupName") String resourceGroupName, @PathParam("accountName") String accountName, @PathParam("shareName") String shareName, @QueryParam("api-version") String apiVersion, @PathParam("subscriptionId") String subscriptionId, @BodyParam("application/json") FileShareInner fileShare);

        @Headers({ "Accept: application/json", "Content-Type: application/json" })
        @Get("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/fileServices/default/shares/{shareName}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<FileShareInner>> get(@HostParam("$host") String host, @PathParam("resourceGroupName") String resourceGroupName, @PathParam("accountName") String accountName, @PathParam("shareName") String shareName, @QueryParam("api-version") String apiVersion, @PathParam("subscriptionId") String subscriptionId);

        @Headers({ "Accept: application/json;q=0.9", "Content-Type: application/json" })
        @Delete("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/fileServices/default/shares/{shareName}")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<Response<Void>> delete(@HostParam("$host") String host, @PathParam("resourceGroupName") String resourceGroupName, @PathParam("accountName") String accountName, @PathParam("shareName") String shareName, @QueryParam("api-version") String apiVersion, @PathParam("subscriptionId") String subscriptionId);

        @Headers({ "Accept: application/json", "Content-Type: application/json" })
        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<FileShareItemsInner>> listNext(@PathParam(value = "nextLink", encoded = true) String nextLink);
    }

    /**
     * Lists all shares.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param maxpagesize Optional. Specified maximum number of shares that can be included in the list.
     * @param filter Optional. When specified, only share names starting with the filter will be listed.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<FileShareItemInner>> listSinglePageAsync(String resourceGroupName, String accountName, String maxpagesize, String filter) {
        return service.list(this.client.getHost(), resourceGroupName, accountName, this.client.getApiVersion(), this.client.getSubscriptionId(), maxpagesize, filter)
            .map(res -> new PagedResponseBase<>(
                res.getRequest(),
                res.getStatusCode(),
                res.getHeaders(),
                res.getValue().getValue(),
                res.getValue().getNextLink(),
                null));
    }

    /**
     * Lists all shares.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param maxpagesize Optional. Specified maximum number of shares that can be included in the list.
     * @param filter Optional. When specified, only share names starting with the filter will be listed.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<FileShareItemInner> listAsync(String resourceGroupName, String accountName, String maxpagesize, String filter) {
        return new PagedFlux<>(
            () -> listSinglePageAsync(resourceGroupName, accountName, maxpagesize, filter),
            nextLink -> listNextSinglePageAsync(nextLink));
    }

    /**
     * Lists all shares.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<FileShareItemInner> listAsync(String resourceGroupName, String accountName) {
        final String maxpagesize = null;
        final String filter = null;
        return new PagedFlux<>(
            () -> listSinglePageAsync(resourceGroupName, accountName, maxpagesize, filter),
            nextLink -> listNextSinglePageAsync(nextLink));
    }

    /**
     * Lists all shares.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param maxpagesize Optional. Specified maximum number of shares that can be included in the list.
     * @param filter Optional. When specified, only share names starting with the filter will be listed.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<FileShareItemInner> list(String resourceGroupName, String accountName, String maxpagesize, String filter) {
        return new PagedIterable<>(listAsync(resourceGroupName, accountName, maxpagesize, filter));
    }

    /**
     * Lists all shares.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<FileShareItemInner> list(String resourceGroupName, String accountName) {
        final String maxpagesize = null;
        final String filter = null;
        return new PagedIterable<>(listAsync(resourceGroupName, accountName, maxpagesize, filter));
    }

    /**
     * Creates a new share under the specified account as described by request body. The share resource includes metadata and properties for that share. It does not include a list of the files contained by the share.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @param metadata A name-value pair to associate with the share as metadata.
     * @param shareQuota The maximum size of the share, in gigabytes. Must be greater than 0, and less than or equal to 5TB (5120). For Large File Shares, the maximum size is 102400.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<FileShareInner>> createWithResponseAsync(String resourceGroupName, String accountName, String shareName, Map<String, String> metadata, Integer shareQuota) {
        FileShareInner fileShare = new FileShareInner();
        fileShare.setMetadata(metadata);
        fileShare.setShareQuota(shareQuota);
        return service.create(this.client.getHost(), resourceGroupName, accountName, shareName, this.client.getApiVersion(), this.client.getSubscriptionId(), fileShare);
    }

    /**
     * Creates a new share under the specified account as described by request body. The share resource includes metadata and properties for that share. It does not include a list of the files contained by the share.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @param metadata A name-value pair to associate with the share as metadata.
     * @param shareQuota The maximum size of the share, in gigabytes. Must be greater than 0, and less than or equal to 5TB (5120). For Large File Shares, the maximum size is 102400.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<FileShareInner> createAsync(String resourceGroupName, String accountName, String shareName, Map<String, String> metadata, Integer shareQuota) {
        return createWithResponseAsync(resourceGroupName, accountName, shareName, metadata, shareQuota)
            .flatMap((SimpleResponse<FileShareInner> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Creates a new share under the specified account as described by request body. The share resource includes metadata and properties for that share. It does not include a list of the files contained by the share.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @param metadata A name-value pair to associate with the share as metadata.
     * @param shareQuota The maximum size of the share, in gigabytes. Must be greater than 0, and less than or equal to 5TB (5120). For Large File Shares, the maximum size is 102400.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public FileShareInner create(String resourceGroupName, String accountName, String shareName, Map<String, String> metadata, Integer shareQuota) {
        return createAsync(resourceGroupName, accountName, shareName, metadata, shareQuota).block();
    }

    /**
     * Updates share properties as specified in request body. Properties not mentioned in the request will not be changed. Update fails if the specified share does not already exist.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @param metadata A name-value pair to associate with the share as metadata.
     * @param shareQuota The maximum size of the share, in gigabytes. Must be greater than 0, and less than or equal to 5TB (5120). For Large File Shares, the maximum size is 102400.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<FileShareInner>> updateWithResponseAsync(String resourceGroupName, String accountName, String shareName, Map<String, String> metadata, Integer shareQuota) {
        FileShareInner fileShare = new FileShareInner();
        fileShare.setMetadata(metadata);
        fileShare.setShareQuota(shareQuota);
        return service.update(this.client.getHost(), resourceGroupName, accountName, shareName, this.client.getApiVersion(), this.client.getSubscriptionId(), fileShare);
    }

    /**
     * Updates share properties as specified in request body. Properties not mentioned in the request will not be changed. Update fails if the specified share does not already exist.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @param metadata A name-value pair to associate with the share as metadata.
     * @param shareQuota The maximum size of the share, in gigabytes. Must be greater than 0, and less than or equal to 5TB (5120). For Large File Shares, the maximum size is 102400.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<FileShareInner> updateAsync(String resourceGroupName, String accountName, String shareName, Map<String, String> metadata, Integer shareQuota) {
        return updateWithResponseAsync(resourceGroupName, accountName, shareName, metadata, shareQuota)
            .flatMap((SimpleResponse<FileShareInner> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Updates share properties as specified in request body. Properties not mentioned in the request will not be changed. Update fails if the specified share does not already exist.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @param metadata A name-value pair to associate with the share as metadata.
     * @param shareQuota The maximum size of the share, in gigabytes. Must be greater than 0, and less than or equal to 5TB (5120). For Large File Shares, the maximum size is 102400.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public FileShareInner update(String resourceGroupName, String accountName, String shareName, Map<String, String> metadata, Integer shareQuota) {
        return updateAsync(resourceGroupName, accountName, shareName, metadata, shareQuota).block();
    }

    /**
     * Gets properties of a specified share.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<FileShareInner>> getWithResponseAsync(String resourceGroupName, String accountName, String shareName) {
        return service.get(this.client.getHost(), resourceGroupName, accountName, shareName, this.client.getApiVersion(), this.client.getSubscriptionId());
    }

    /**
     * Gets properties of a specified share.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<FileShareInner> getAsync(String resourceGroupName, String accountName, String shareName) {
        return getWithResponseAsync(resourceGroupName, accountName, shareName)
            .flatMap((SimpleResponse<FileShareInner> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets properties of a specified share.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public FileShareInner get(String resourceGroupName, String accountName, String shareName) {
        return getAsync(resourceGroupName, accountName, shareName).block();
    }

    /**
     * Deletes specified share under its account.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteWithResponseAsync(String resourceGroupName, String accountName, String shareName) {
        return service.delete(this.client.getHost(), resourceGroupName, accountName, shareName, this.client.getApiVersion(), this.client.getSubscriptionId());
    }

    /**
     * Deletes specified share under its account.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> deleteAsync(String resourceGroupName, String accountName, String shareName) {
        return deleteWithResponseAsync(resourceGroupName, accountName, shareName)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Deletes specified share under its account.
     * 
     * @param resourceGroupName The name of the resource group within the user's subscription. The name is case insensitive.
     * @param accountName The name of the storage account within the specified resource group. Storage account names must be between 3 and 24 characters in length and use numbers and lower-case letters only.
     * @param shareName The name of the file share within the specified storage account. File share names must be between 3 and 63 characters in length and use numbers, lower-case letters and dash (-) only. Every dash (-) character must be immediately preceded and followed by a letter or number.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete(String resourceGroupName, String accountName, String shareName) {
        deleteAsync(resourceGroupName, accountName, shareName).block();
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
    public Mono<PagedResponse<FileShareItemInner>> listNextSinglePageAsync(String nextLink) {
        return service.listNext(nextLink)
            .map(res -> new PagedResponseBase<>(
                res.getRequest(),
                res.getStatusCode(),
                res.getHeaders(),
                res.getValue().getValue(),
                res.getValue().getNextLink(),
                null));
    }
}
