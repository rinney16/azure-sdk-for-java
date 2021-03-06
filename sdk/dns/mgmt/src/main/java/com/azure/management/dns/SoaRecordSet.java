/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.azure.management.dns;

import com.azure.core.annotation.Fluent;

/**
 * An immutable client-side representation of a SOA (start of authority) record set in Azure DNS Zone.
 */
@Fluent
public interface SoaRecordSet extends DnsRecordSet {
    /**
     * @return the SOA record in this record set
     */
    SoaRecord record();
}
