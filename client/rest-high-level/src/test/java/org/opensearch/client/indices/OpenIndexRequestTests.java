/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.client.indices;

import org.opensearch.action.support.ActiveShardCount;
import org.opensearch.action.support.IndicesOptions;
import org.opensearch.client.TimedRequest;
import org.opensearch.client.ValidationException;
import org.opensearch.common.Strings;
import org.opensearch.common.unit.TimeValue;
import org.opensearch.test.OpenSearchTestCase;

import java.util.Optional;

public class OpenIndexRequestTests extends OpenSearchTestCase {

    public void testIndices() {
        String[] indices = generateRandomStringArray(5, 5, false, true);
        OpenIndexRequest request = new OpenIndexRequest(indices);
        assertArrayEquals(indices, request.indices());
    }

    public void testValidate() {
        String[] indices = randomBoolean() ? null : Strings.EMPTY_ARRAY;
        OpenIndexRequest request = new OpenIndexRequest(indices);
        Optional<ValidationException> validation = request.validate();
        assertTrue(validation.isPresent());
        assertEquals(validation.get().validationErrors().get(0), "index is missing");
    }

    public void testIndicesOptions() {
        IndicesOptions indicesOptions = IndicesOptions.fromOptions(randomBoolean(), randomBoolean(), randomBoolean(), randomBoolean());
        OpenIndexRequest request = new OpenIndexRequest().indicesOptions(indicesOptions);
        assertEquals(indicesOptions, request.indicesOptions());
    }

    public void testWaitForActiveShards() {
        final OpenIndexRequest request = new OpenIndexRequest("index");
        final int count = randomIntBetween(0, 10);
        request.waitForActiveShards(ActiveShardCount.from(count));
        assertEquals(request.waitForActiveShards(), ActiveShardCount.from(count));
    }

    public void testTimeout() {
        final OpenIndexRequest request = new OpenIndexRequest("index");
        assertEquals(request.timeout(), TimedRequest.DEFAULT_ACK_TIMEOUT);
        assertEquals(request.clusterManagerNodeTimeout(), TimedRequest.DEFAULT_CLUSTER_MANAGER_NODE_TIMEOUT);

        final TimeValue timeout = TimeValue.timeValueSeconds(randomIntBetween(0, 1000));
        request.setTimeout(timeout);

        final TimeValue clusterManagerTimeout = TimeValue.timeValueSeconds(randomIntBetween(0, 1000));
        request.setClusterManagerTimeout(clusterManagerTimeout);

        final TimeValue taskExecutionTimeout = TimeValue.timeValueSeconds(randomIntBetween(0, 1000));
        request.setTaskExecutionTimeout(taskExecutionTimeout);

        assertEquals(request.timeout(), timeout);
        assertEquals(request.clusterManagerNodeTimeout(), clusterManagerTimeout);
        assertEquals(request.getTaskExecutionTimeout(), taskExecutionTimeout);
    }

}
