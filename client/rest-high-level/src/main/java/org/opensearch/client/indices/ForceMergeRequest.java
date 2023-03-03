/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.client.indices;

import org.opensearch.action.support.IndicesOptions;
import org.opensearch.client.Validatable;
import org.opensearch.client.ValidationException;

import java.util.Arrays;
import java.util.Optional;

/**
 * Request class to force-merge indices
 */
public class ForceMergeRequest implements Validatable {

    public static final class Defaults {
        public static final int MAX_NUM_SEGMENTS = -1;
        public static final boolean ONLY_EXPUNGE_DELETES = false;
        public static final boolean FLUSH = true;
    }

    protected String[] indices;
    private IndicesOptions indicesOptions = IndicesOptions.strictExpandOpen();
    private int maxNumSegments = Defaults.MAX_NUM_SEGMENTS;
    private boolean onlyExpungeDeletes = Defaults.ONLY_EXPUNGE_DELETES;
    private boolean flush = Defaults.FLUSH;

    /**
     * Constructs a merge request over one or more indices.
     *
     * @param indices The indices to merge, no indices passed means all indices will be merged.
     */
    public ForceMergeRequest(String... indices) {
        this.indices = indices;
    }

    /**
     * Returns the indices to force-merge
     */
    public String[] indices() {
        return this.indices;
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
    public ForceMergeRequest indicesOptions(IndicesOptions indicesOptions) {
        this.indicesOptions = indicesOptions;
        return this;
    }

    /**
     * Will merge the index down to &lt;= maxNumSegments. By default, will cause the merge
     * process to merge down to half the configured number of segments.
     */
    public int maxNumSegments() {
        return maxNumSegments;
    }

    /**
     * Will merge the index down to &lt;= maxNumSegments. By default, will cause the merge
     * process to merge down to half the configured number of segments.
     */
    public ForceMergeRequest maxNumSegments(int maxNumSegments) {
        this.maxNumSegments = maxNumSegments;
        return this;
    }

    /**
     * Should the merge only expunge deletes from the index, without full merging.
     * Defaults to full merging ({@code false}).
     */
    public boolean onlyExpungeDeletes() {
        return onlyExpungeDeletes;
    }

    /**
     * Should the merge only expunge deletes from the index, without full merge.
     * Defaults to full merging ({@code false}).
     */
    public ForceMergeRequest onlyExpungeDeletes(boolean onlyExpungeDeletes) {
        this.onlyExpungeDeletes = onlyExpungeDeletes;
        return this;
    }

    /**
     * Should flush be performed after the merge. Defaults to {@code true}.
     */
    public boolean flush() {
        return flush;
    }

    /**
     * Should flush be performed after the merge. Defaults to {@code true}.
     */
    public ForceMergeRequest flush(boolean flush) {
        this.flush = flush;
        return this;
    }

    @Override
    public Optional<ValidationException> validate() {
        return Optional.empty();
    }

    public String getDescription() {
        return "Force-merge indices "
            + Arrays.toString(indices())
            + ", maxSegments["
            + maxNumSegments
            + "], onlyExpungeDeletes["
            + onlyExpungeDeletes
            + "], flush["
            + flush
            + "]";
    }
}
