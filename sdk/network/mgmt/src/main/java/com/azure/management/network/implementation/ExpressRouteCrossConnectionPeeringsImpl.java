/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.azure.management.network.implementation;

import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.management.network.ExpressRouteCrossConnection;
import com.azure.management.network.ExpressRouteCrossConnectionPeering;
import com.azure.management.network.ExpressRouteCrossConnectionPeerings;
import com.azure.management.network.ExpressRoutePeeringType;
import com.azure.management.network.models.ExpressRouteCrossConnectionPeeringInner;
import com.azure.management.network.models.ExpressRouteCrossConnectionPeeringsInner;
import com.azure.management.resources.fluentcore.arm.collection.implementation.IndependentChildrenImpl;
import com.azure.management.resources.fluentcore.arm.collection.implementation.ReadableWrappersImpl;
import reactor.core.publisher.Mono;

/**
 * Represents Express Route Cross Connection Peerings collection associated with Network Watcher.
 */
class ExpressRouteCrossConnectionPeeringsImpl extends IndependentChildrenImpl<
        ExpressRouteCrossConnectionPeering,
        ExpressRouteCrossConnectionPeeringImpl,
        ExpressRouteCrossConnectionPeeringInner,
        ExpressRouteCrossConnectionPeeringsInner,
        NetworkManager,
        ExpressRouteCrossConnection>
        implements ExpressRouteCrossConnectionPeerings {
    private final ExpressRouteCrossConnectionImpl parent;

    /**
     * Creates a new ExpressRouteCrossConnectionPeeringsImpl.
     *
     * @param parent the Express Route Circuit associated with ExpressRouteCrossConnectionPeering
     */
    ExpressRouteCrossConnectionPeeringsImpl(ExpressRouteCrossConnectionImpl parent) {
        super(parent.manager().inner().expressRouteCrossConnectionPeerings(), parent.manager());
        this.parent = parent;
    }

    @Override
    public final PagedIterable<ExpressRouteCrossConnectionPeering> list() {
        return wrapList(inner().list(parent.resourceGroupName(), parent.name()));
    }

    /**
     * @return an observable emits packet captures in this collection
     */
    @Override
    public PagedFlux<ExpressRouteCrossConnectionPeering> listAsync() {
        return wrapPageAsync(inner().listAsync(parent.resourceGroupName(), parent.name()));
    }

    @Override
    protected ExpressRouteCrossConnectionPeeringImpl wrapModel(String name) {
        return new ExpressRouteCrossConnectionPeeringImpl(parent, new ExpressRouteCrossConnectionPeeringInner(), ExpressRoutePeeringType.fromString(name));
    }

    protected ExpressRouteCrossConnectionPeeringImpl wrapModel(ExpressRouteCrossConnectionPeeringInner inner) {
        return (inner == null) ? null : new ExpressRouteCrossConnectionPeeringImpl(parent, inner, inner.peeringType());
    }

    @Override
    public ExpressRouteCrossConnectionPeeringImpl defineAzurePrivatePeering() {
        return new ExpressRouteCrossConnectionPeeringImpl(parent, new ExpressRouteCrossConnectionPeeringInner(), ExpressRoutePeeringType.AZURE_PRIVATE_PEERING);
    }

    @Override
    public ExpressRouteCrossConnectionPeeringImpl defineMicrosoftPeering() {
        return new ExpressRouteCrossConnectionPeeringImpl(parent, new ExpressRouteCrossConnectionPeeringInner(), ExpressRoutePeeringType.MICROSOFT_PEERING);
    }

    @Override
    public Mono<ExpressRouteCrossConnectionPeering> getByNameAsync(String name) {
        return inner().getAsync(parent.resourceGroupName(), parent.name(), name)
                .map(inner -> wrapModel(inner));
    }

    @Override
    public ExpressRouteCrossConnectionPeering getByName(String name) {
        return getByNameAsync(name).block();
    }

    @Override
    public void deleteByName(String name) {
        deleteByNameAsync(name).block();
    }

    @Override
    public Mono<Void> deleteByNameAsync(String name) {
        return deleteByParentAsync(parent.resourceGroupName(), parent.name(), name);
    }

    @Override
    public ExpressRouteCrossConnection parent() {
        return parent;
    }

    @Override
    public Mono<Void> deleteByParentAsync(String groupName, String parentName, String name) {
        return this.inner().deleteAsync(groupName, parentName, name)
                .doOnSuccess(result -> {
                    parent.refresh();
                }).then();
    }

    @Override
    public Mono<ExpressRouteCrossConnectionPeering> getByParentAsync(String resourceGroup, String parentName, String name) {
        return inner().getAsync(resourceGroup, parentName, name)
                .map(inner -> wrapModel(inner));
    }

    @Override
    public PagedIterable<ExpressRouteCrossConnectionPeering> listByParent(String resourceGroupName, String parentName) {
        return wrapList(this.inner().list(resourceGroupName, parentName));
    }
}