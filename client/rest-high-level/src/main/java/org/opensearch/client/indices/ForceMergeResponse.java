/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.client.indices;

import org.opensearch.client.core.BroadcastResponse;
import org.opensearch.core.xcontent.ConstructingObjectParser;
import org.opensearch.core.xcontent.XContentParser;

import java.io.IOException;

public class ForceMergeResponse extends BroadcastResponse {

    private static final ConstructingObjectParser<ForceMergeResponse, Void> PARSER = new ConstructingObjectParser<>(
        "force_merge_response",
        true,
        arg -> {
            Shards shards = (Shards) arg[0];
            return new ForceMergeResponse(shards);
        }
    );

    static {
        declareShardsField(PARSER);
    }

    ForceMergeResponse(Shards shards) {
        super(shards);
    }

    public static ForceMergeResponse fromXContent(final XContentParser parser) throws IOException {
        return PARSER.apply(parser, null);
    }
}
