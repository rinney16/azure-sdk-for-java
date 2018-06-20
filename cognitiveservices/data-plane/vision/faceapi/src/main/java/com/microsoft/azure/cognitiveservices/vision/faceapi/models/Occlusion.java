/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.vision.faceapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Properties describing occlusions on a given face.
 */
public class Occlusion {
    /**
     * A boolean value indicating whether forehead is occluded.
     */
    @JsonProperty(value = "foreheadOccluded")
    private boolean foreheadOccluded;

    /**
     * A boolean value indicating whether eyes are occluded.
     */
    @JsonProperty(value = "eyeOccluded")
    private boolean eyeOccluded;

    /**
     * A boolean value indicating whether the mouth is occluded.
     */
    @JsonProperty(value = "mouthOccluded")
    private boolean mouthOccluded;

    /**
     * Get the foreheadOccluded value.
     *
     * @return the foreheadOccluded value
     */
    public boolean foreheadOccluded() {
        return this.foreheadOccluded;
    }

    /**
     * Set the foreheadOccluded value.
     *
     * @param foreheadOccluded the foreheadOccluded value to set
     * @return the Occlusion object itself.
     */
    public Occlusion withForeheadOccluded(boolean foreheadOccluded) {
        this.foreheadOccluded = foreheadOccluded;
        return this;
    }

    /**
     * Get the eyeOccluded value.
     *
     * @return the eyeOccluded value
     */
    public boolean eyeOccluded() {
        return this.eyeOccluded;
    }

    /**
     * Set the eyeOccluded value.
     *
     * @param eyeOccluded the eyeOccluded value to set
     * @return the Occlusion object itself.
     */
    public Occlusion withEyeOccluded(boolean eyeOccluded) {
        this.eyeOccluded = eyeOccluded;
        return this;
    }

    /**
     * Get the mouthOccluded value.
     *
     * @return the mouthOccluded value
     */
    public boolean mouthOccluded() {
        return this.mouthOccluded;
    }

    /**
     * Set the mouthOccluded value.
     *
     * @param mouthOccluded the mouthOccluded value to set
     * @return the Occlusion object itself.
     */
    public Occlusion withMouthOccluded(boolean mouthOccluded) {
        this.mouthOccluded = mouthOccluded;
        return this;
    }

}