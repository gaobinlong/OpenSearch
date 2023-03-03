/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.client.indices;

import org.opensearch.client.core.AcknowledgedResponse;
import org.opensearch.client.core.ShardsAcknowledgedResponse;
import org.opensearch.core.ParseField;
import org.opensearch.core.xcontent.ConstructingObjectParser;
import org.opensearch.core.xcontent.XContentParser;

import static org.opensearch.core.xcontent.ConstructingObjectParser.constructorArg;

public class OpenIndexResponse extends ShardsAcknowledgedResponse {

    private static final ConstructingObjectParser<OpenIndexResponse, Void> PARSER = new ConstructingObjectParser<>(
        "open_index_response",
        true,
        args -> {
            boolean acknowledged = (boolean) args[0];
            boolean shardsAcknowledged = args[1] != null ? (boolean) args[1] : acknowledged;
            return new OpenIndexResponse(acknowledged, shardsAcknowledged);
        }
    );

    static {
        PARSER.declareBoolean(constructorArg(), new ParseField(AcknowledgedResponse.PARSE_FIELD_NAME));
        PARSER.declareBoolean(constructorArg(), new ParseField(SHARDS_PARSE_FIELD_NAME));
    }

    public OpenIndexResponse(final boolean acknowledged, final boolean shardsAcknowledged) {
        super(acknowledged, shardsAcknowledged);
    }

    public static OpenIndexResponse fromXContent(final XContentParser parser) {
        return PARSER.apply(parser, null);
    }
}
