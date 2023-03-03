/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.client.indices;

import org.opensearch.client.AbstractResponseTestCase;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.core.xcontent.XContentParser;

import java.io.IOException;

public class OpenIndexResponseTests extends AbstractResponseTestCase<
    org.opensearch.action.admin.indices.open.OpenIndexResponse,
    OpenIndexResponse> {

    @Override
    protected org.opensearch.action.admin.indices.open.OpenIndexResponse createServerTestInstance(XContentType xContentType) {
        boolean acknowledged = true;

        final boolean shardsAcknowledged = acknowledged ? randomBoolean() : false;
        return new org.opensearch.action.admin.indices.open.OpenIndexResponse(acknowledged, shardsAcknowledged);
    }

    @Override
    protected OpenIndexResponse doParseToClientInstance(final XContentParser parser) throws IOException {
        return OpenIndexResponse.fromXContent(parser);
    }

    @Override
    protected void assertInstances(
        final org.opensearch.action.admin.indices.open.OpenIndexResponse serverInstance,
        final OpenIndexResponse clientInstance
    ) {
        assertNotSame(serverInstance, clientInstance);
        assertEquals(clientInstance.isAcknowledged(), serverInstance.isAcknowledged());
        assertEquals(clientInstance.isShardsAcknowledged(), serverInstance.isShardsAcknowledged());
    }

}
