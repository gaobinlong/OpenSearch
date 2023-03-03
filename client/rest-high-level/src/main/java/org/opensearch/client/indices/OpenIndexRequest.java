/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.client.indices;

import org.opensearch.action.admin.indices.open.OpenIndexResponse;
import org.opensearch.action.support.ActiveShardCount;
import org.opensearch.action.support.IndicesOptions;
import org.opensearch.client.TimedRequest;
import org.opensearch.client.Validatable;
import org.opensearch.client.ValidationException;
import org.opensearch.common.unit.TimeValue;

import java.util.Optional;

/**
 * A request to open an index.
 */
public class OpenIndexRequest extends TimedRequest implements Validatable {

    private String[] indices;
    private IndicesOptions indicesOptions = IndicesOptions.strictExpandOpen();
    private ActiveShardCount waitForActiveShards = ActiveShardCount.DEFAULT;
    private TimeValue taskExecutionTimeout;

    /**
     * Creates a new open index request
     *
     * @param indices the indices to open
     */
    public OpenIndexRequest(String... indices) {
        this.indices = indices;
    }

    /**
     * Returns the indices to open
     */
    public String[] indices() {
        return indices;
    }

    /**
     * Specifies what type of requested indices to ignore and how to deal with wildcard expressions.
     * For example indices that don't exist.
     *
     * @return the current behaviour when it comes to index names and wildcard indices expressions
     */
    public IndicesOptions indicesOptions() {
        return indicesOptions;
    }

    /**
     * Specifies what type of requested indices to ignore and how to deal with wildcard expressions.
     * For example indices that don't exist.
     *
     * @param indicesOptions the desired behaviour regarding indices to ignore and wildcard indices expressions
     */
    public OpenIndexRequest indicesOptions(IndicesOptions indicesOptions) {
        this.indicesOptions = indicesOptions;
        return this;
    }

    /**
     * Returns the wait for active shard count or null if the default should be used
     */
    public ActiveShardCount waitForActiveShards() {
        return waitForActiveShards;
    }

    /**
     * Sets the number of shard copies that should be active for indices opening to return.
     * Defaults to {@link ActiveShardCount#DEFAULT}, which will wait for one shard copy
     * (the primary) to become active. Set this value to {@link ActiveShardCount#ALL} to
     * wait for all shards (primary and all replicas) to be active before returning.
     * Otherwise, use {@link ActiveShardCount#from(int)} to set this value to any
     * non-negative integer, up to the number of copies per shard (number of replicas + 1),
     * to wait for the desired amount of shard copies to become active before returning.
     * Indices opening will only wait up until the timeout value for the number of shard copies
     * to be active before returning.  Check {@link OpenIndexResponse#isShardsAcknowledged()} to
     * determine if the requisite shard copies were all started before returning or timing out.
     *
     * @param waitForActiveShards number of active shard copies to wait on
     */
    public OpenIndexRequest waitForActiveShards(ActiveShardCount waitForActiveShards) {
        this.waitForActiveShards = waitForActiveShards;
        return this;
    }

    /**
     * Sets the execution timeout of the open index task, only useful when using submitOpenTask
     * to submit an open index task
     *
     * @param taskExecutionTimeout the execution timeout of the resize task
     */
    public void setTaskExecutionTimeout(TimeValue taskExecutionTimeout) {
        this.taskExecutionTimeout = taskExecutionTimeout;
    }

    /**
     * Returns the execution timeout of the open index task.
     */
    public TimeValue getTaskExecutionTimeout() {
        return taskExecutionTimeout;
    }

    @Override
    public Optional<ValidationException> validate() {
        if (indices == null || indices.length == 0) {
            ValidationException validationException = new ValidationException();
            validationException.addValidationError("index is missing");
            return Optional.of(validationException);
        } else {
            return Optional.empty();
        }
    }
}
