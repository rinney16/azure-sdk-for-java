/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.applicationinsights.query;

import com.microsoft.azure.applicationinsights.query.models.ErrorResponseException;
import com.microsoft.azure.applicationinsights.query.models.EventsResults;
import com.microsoft.azure.applicationinsights.query.models.EventType;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import java.io.IOException;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in Events.
 */
public interface Events {
    /**
     * Execute OData query.
     * Executes an OData query for events.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the EventsResults object if successful.
     */
    EventsResults getByType(String appId, EventType eventType);

    /**
     * Execute OData query.
     * Executes an OData query for events.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<EventsResults> getByTypeAsync(String appId, EventType eventType, final ServiceCallback<EventsResults> serviceCallback);

    /**
     * Execute OData query.
     * Executes an OData query for events.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EventsResults object
     */
    Observable<EventsResults> getByTypeAsync(String appId, EventType eventType);

    /**
     * Execute OData query.
     * Executes an OData query for events.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EventsResults object
     */
    Observable<ServiceResponse<EventsResults>> getByTypeWithServiceResponseAsync(String appId, EventType eventType);
    /**
     * Execute OData query.
     * Executes an OData query for events.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param timespan Optional. The timespan over which to retrieve events. This is an ISO8601 time period value.  This timespan is applied in addition to any that are specified in the Odata expression.
     * @param filter An expression used to filter the returned events
     * @param search A free-text search expression to match for whether a particular event should be returned
     * @param orderby A comma-separated list of properties with \"asc\" (the default) or \"desc\" to control the order of returned events
     * @param select Limits the properties to just those requested on each returned event
     * @param skip The number of items to skip over before returning events
     * @param top The number of events to return
     * @param format Format for the returned events
     * @param count Request a count of matching items included with the returned events
     * @param apply An expression used for aggregation over returned events
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the EventsResults object if successful.
     */
    EventsResults getByType(String appId, EventType eventType, String timespan, String filter, String search, String orderby, String select, Integer skip, Integer top, String format, Boolean count, String apply);

    /**
     * Execute OData query.
     * Executes an OData query for events.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param timespan Optional. The timespan over which to retrieve events. This is an ISO8601 time period value.  This timespan is applied in addition to any that are specified in the Odata expression.
     * @param filter An expression used to filter the returned events
     * @param search A free-text search expression to match for whether a particular event should be returned
     * @param orderby A comma-separated list of properties with \"asc\" (the default) or \"desc\" to control the order of returned events
     * @param select Limits the properties to just those requested on each returned event
     * @param skip The number of items to skip over before returning events
     * @param top The number of events to return
     * @param format Format for the returned events
     * @param count Request a count of matching items included with the returned events
     * @param apply An expression used for aggregation over returned events
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<EventsResults> getByTypeAsync(String appId, EventType eventType, String timespan, String filter, String search, String orderby, String select, Integer skip, Integer top, String format, Boolean count, String apply, final ServiceCallback<EventsResults> serviceCallback);

    /**
     * Execute OData query.
     * Executes an OData query for events.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param timespan Optional. The timespan over which to retrieve events. This is an ISO8601 time period value.  This timespan is applied in addition to any that are specified in the Odata expression.
     * @param filter An expression used to filter the returned events
     * @param search A free-text search expression to match for whether a particular event should be returned
     * @param orderby A comma-separated list of properties with \"asc\" (the default) or \"desc\" to control the order of returned events
     * @param select Limits the properties to just those requested on each returned event
     * @param skip The number of items to skip over before returning events
     * @param top The number of events to return
     * @param format Format for the returned events
     * @param count Request a count of matching items included with the returned events
     * @param apply An expression used for aggregation over returned events
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EventsResults object
     */
    Observable<EventsResults> getByTypeAsync(String appId, EventType eventType, String timespan, String filter, String search, String orderby, String select, Integer skip, Integer top, String format, Boolean count, String apply);

    /**
     * Execute OData query.
     * Executes an OData query for events.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param timespan Optional. The timespan over which to retrieve events. This is an ISO8601 time period value.  This timespan is applied in addition to any that are specified in the Odata expression.
     * @param filter An expression used to filter the returned events
     * @param search A free-text search expression to match for whether a particular event should be returned
     * @param orderby A comma-separated list of properties with \"asc\" (the default) or \"desc\" to control the order of returned events
     * @param select Limits the properties to just those requested on each returned event
     * @param skip The number of items to skip over before returning events
     * @param top The number of events to return
     * @param format Format for the returned events
     * @param count Request a count of matching items included with the returned events
     * @param apply An expression used for aggregation over returned events
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EventsResults object
     */
    Observable<ServiceResponse<EventsResults>> getByTypeWithServiceResponseAsync(String appId, EventType eventType, String timespan, String filter, String search, String orderby, String select, Integer skip, Integer top, String format, Boolean count, String apply);

    /**
     * Get an event.
     * Gets the data for a single event.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param eventId ID of event.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the EventsResults object if successful.
     */
    EventsResults get(String appId, EventType eventType, String eventId);

    /**
     * Get an event.
     * Gets the data for a single event.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param eventId ID of event.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<EventsResults> getAsync(String appId, EventType eventType, String eventId, final ServiceCallback<EventsResults> serviceCallback);

    /**
     * Get an event.
     * Gets the data for a single event.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param eventId ID of event.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EventsResults object
     */
    Observable<EventsResults> getAsync(String appId, EventType eventType, String eventId);

    /**
     * Get an event.
     * Gets the data for a single event.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param eventId ID of event.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EventsResults object
     */
    Observable<ServiceResponse<EventsResults>> getWithServiceResponseAsync(String appId, EventType eventType, String eventId);
    /**
     * Get an event.
     * Gets the data for a single event.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param eventId ID of event.
     * @param timespan Optional. The timespan over which to retrieve events. This is an ISO8601 time period value.  This timespan is applied in addition to any that are specified in the Odata expression.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the EventsResults object if successful.
     */
    EventsResults get(String appId, EventType eventType, String eventId, String timespan);

    /**
     * Get an event.
     * Gets the data for a single event.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param eventId ID of event.
     * @param timespan Optional. The timespan over which to retrieve events. This is an ISO8601 time period value.  This timespan is applied in addition to any that are specified in the Odata expression.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<EventsResults> getAsync(String appId, EventType eventType, String eventId, String timespan, final ServiceCallback<EventsResults> serviceCallback);

    /**
     * Get an event.
     * Gets the data for a single event.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param eventId ID of event.
     * @param timespan Optional. The timespan over which to retrieve events. This is an ISO8601 time period value.  This timespan is applied in addition to any that are specified in the Odata expression.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EventsResults object
     */
    Observable<EventsResults> getAsync(String appId, EventType eventType, String eventId, String timespan);

    /**
     * Get an event.
     * Gets the data for a single event.
     *
     * @param appId ID of the application. This is Application ID from the API Access settings blade in the Azure portal.
     * @param eventType The type of events to query; either a standard event type (`traces`, `customEvents`, `pageViews`, `requests`, `dependencies`, `exceptions`, `availabilityResults`) or `$all` to query across all event types. Possible values include: '$all', 'traces', 'customEvents', 'pageViews', 'browserTimings', 'requests', 'dependencies', 'exceptions', 'availabilityResults', 'performanceCounters', 'customMetrics'
     * @param eventId ID of event.
     * @param timespan Optional. The timespan over which to retrieve events. This is an ISO8601 time period value.  This timespan is applied in addition to any that are specified in the Odata expression.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the EventsResults object
     */
    Observable<ServiceResponse<EventsResults>> getWithServiceResponseAsync(String appId, EventType eventType, String eventId, String timespan);

}