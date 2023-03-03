/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.client.indices;

import org.opensearch.OpenSearchException;
import org.opensearch.action.support.DefaultShardOperationFailedException;
import org.opensearch.client.AbstractResponseTestCase;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.core.xcontent.XContentParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ForceMergeResponseTests extends AbstractResponseTestCase<
    org.opensearch.action.admin.indices.forcemerge.ForceMergeResponse,
    ForceMergeResponse> {

    @Override
    protected org.opensearch.action.admin.indices.forcemerge.ForceMergeResponse createServerTestInstance(XContentType xContentType) {
        String indexName = randomAlphaOfLengthBetween(3, 10);
        int shardId = randomIntBetween(0, 10);
        List<DefaultShardOperationFailedException> exceptions = new ArrayList<>();
        exceptions.add(new DefaultShardOperationFailedException(indexName, shardId, new OpenSearchException("boom")));
        return new org.opensearch.action.admin.indices.forcemerge.ForceMergeResponse(10, 3, 1, exceptions);
    }

    @Override
    protected ForceMergeResponse doParseToClientInstance(final XContentParser parser) throws IOException {
        return ForceMergeResponse.fromXContent(parser);
    }

    @Override
    protected void assertInstances(
        final org.opensearch.action.admin.indices.forcemerge.ForceMergeResponse serverInstance,
        final ForceMergeResponse clientInstance
    ) {
        assertNotSame(serverInstance, clientInstance);
        assertEquals(serverInstance.getTotalShards(), clientInstance.shards().total());
        assertEquals(serverInstance.getSuccessfulShards(), clientInstance.shards().successful());
        assertEquals(serverInstance.getFailedShards(), clientInstance.shards().failed());
        assertEquals(serverInstance.getShardFailures().length, clientInstance.shards().failures().size());
    }

}
