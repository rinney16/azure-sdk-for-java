// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.sql.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.azure.core.management.ProxyResource;
import com.azure.management.sql.SyncDirection;
import com.azure.management.sql.SyncMemberDbType;
import com.azure.management.sql.SyncMemberState;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 * The SyncMember model.
 */
@JsonFlatten
@Fluent
public class SyncMemberInner extends ProxyResource {
    /*
     * Database type of the sync member.
     */
    @JsonProperty(value = "properties.databaseType")
    private SyncMemberDbType databaseType;

    /*
     * ARM resource id of the sync agent in the sync member.
     */
    @JsonProperty(value = "properties.syncAgentId")
    private String syncAgentId;

    /*
     * SQL Server database id of the sync member.
     */
    @JsonProperty(value = "properties.sqlServerDatabaseId")
    private UUID sqlServerDatabaseId;

    /*
     * Server name of the member database in the sync member
     */
    @JsonProperty(value = "properties.serverName")
    private String serverName;

    /*
     * Database name of the member database in the sync member.
     */
    @JsonProperty(value = "properties.databaseName")
    private String databaseName;

    /*
     * User name of the member database in the sync member.
     */
    @JsonProperty(value = "properties.userName")
    private String userName;

    /*
     * Password of the member database in the sync member.
     */
    @JsonProperty(value = "properties.password")
    private String password;

    /*
     * Sync direction of the sync member.
     */
    @JsonProperty(value = "properties.syncDirection")
    private SyncDirection syncDirection;

    /*
     * Sync state of the sync member.
     */
    @JsonProperty(value = "properties.syncState", access = JsonProperty.Access.WRITE_ONLY)
    private SyncMemberState syncState;

    /**
     * Get the databaseType property: Database type of the sync member.
     * 
     * @return the databaseType value.
     */
    public SyncMemberDbType databaseType() {
        return this.databaseType;
    }

    /**
     * Set the databaseType property: Database type of the sync member.
     * 
     * @param databaseType the databaseType value to set.
     * @return the SyncMemberInner object itself.
     */
    public SyncMemberInner withDatabaseType(SyncMemberDbType databaseType) {
        this.databaseType = databaseType;
        return this;
    }

    /**
     * Get the syncAgentId property: ARM resource id of the sync agent in the
     * sync member.
     * 
     * @return the syncAgentId value.
     */
    public String syncAgentId() {
        return this.syncAgentId;
    }

    /**
     * Set the syncAgentId property: ARM resource id of the sync agent in the
     * sync member.
     * 
     * @param syncAgentId the syncAgentId value to set.
     * @return the SyncMemberInner object itself.
     */
    public SyncMemberInner withSyncAgentId(String syncAgentId) {
        this.syncAgentId = syncAgentId;
        return this;
    }

    /**
     * Get the sqlServerDatabaseId property: SQL Server database id of the sync
     * member.
     * 
     * @return the sqlServerDatabaseId value.
     */
    public UUID sqlServerDatabaseId() {
        return this.sqlServerDatabaseId;
    }

    /**
     * Set the sqlServerDatabaseId property: SQL Server database id of the sync
     * member.
     * 
     * @param sqlServerDatabaseId the sqlServerDatabaseId value to set.
     * @return the SyncMemberInner object itself.
     */
    public SyncMemberInner withSqlServerDatabaseId(UUID sqlServerDatabaseId) {
        this.sqlServerDatabaseId = sqlServerDatabaseId;
        return this;
    }

    /**
     * Get the serverName property: Server name of the member database in the
     * sync member.
     * 
     * @return the serverName value.
     */
    public String serverName() {
        return this.serverName;
    }

    /**
     * Set the serverName property: Server name of the member database in the
     * sync member.
     * 
     * @param serverName the serverName value to set.
     * @return the SyncMemberInner object itself.
     */
    public SyncMemberInner withServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    /**
     * Get the databaseName property: Database name of the member database in
     * the sync member.
     * 
     * @return the databaseName value.
     */
    public String databaseName() {
        return this.databaseName;
    }

    /**
     * Set the databaseName property: Database name of the member database in
     * the sync member.
     * 
     * @param databaseName the databaseName value to set.
     * @return the SyncMemberInner object itself.
     */
    public SyncMemberInner withDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    /**
     * Get the userName property: User name of the member database in the sync
     * member.
     * 
     * @return the userName value.
     */
    public String userName() {
        return this.userName;
    }

    /**
     * Set the userName property: User name of the member database in the sync
     * member.
     * 
     * @param userName the userName value to set.
     * @return the SyncMemberInner object itself.
     */
    public SyncMemberInner withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Get the password property: Password of the member database in the sync
     * member.
     * 
     * @return the password value.
     */
    public String password() {
        return this.password;
    }

    /**
     * Set the password property: Password of the member database in the sync
     * member.
     * 
     * @param password the password value to set.
     * @return the SyncMemberInner object itself.
     */
    public SyncMemberInner withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Get the syncDirection property: Sync direction of the sync member.
     * 
     * @return the syncDirection value.
     */
    public SyncDirection syncDirection() {
        return this.syncDirection;
    }

    /**
     * Set the syncDirection property: Sync direction of the sync member.
     * 
     * @param syncDirection the syncDirection value to set.
     * @return the SyncMemberInner object itself.
     */
    public SyncMemberInner withSyncDirection(SyncDirection syncDirection) {
        this.syncDirection = syncDirection;
        return this;
    }

    /**
     * Get the syncState property: Sync state of the sync member.
     * 
     * @return the syncState value.
     */
    public SyncMemberState syncState() {
        return this.syncState;
    }
}
