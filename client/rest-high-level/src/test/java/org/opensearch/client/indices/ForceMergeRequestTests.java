/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.client.indices;

import org.opensearch.action.support.IndicesOptions;
import org.opensearch.test.OpenSearchTestCase;

public class ForceMergeRequestTests extends OpenSearchTestCase {

    public void testIndices() {
        String[] indices = generateRandomStringArray(5, 5, false, true);
        ForceMergeRequest request = new ForceMergeRequest(indices);
        assertArrayEquals(indices, request.indices());
    }

    public void testIndicesOptions() {
        IndicesOptions indicesOptions = IndicesOptions.fromOptions(randomBoolean(), randomBoolean(), randomBoolean(), randomBoolean());
        ForceMergeRequest request = new ForceMergeRequest().indicesOptions(indicesOptions);
        assertEquals(indicesOptions, request.indicesOptions());
    }

    public void testDescription() {
        ForceMergeRequest request = new ForceMergeRequest();
        assertEquals("Force-merge indices [], maxSegments[-1], onlyExpungeDeletes[false], flush[true]", request.getDescription());

        request = new ForceMergeRequest("shop", "blog");
        assertEquals("Force-merge indices [shop, blog], maxSegments[-1], onlyExpungeDeletes[false], flush[true]", request.getDescription());

        request = new ForceMergeRequest();
        request.maxNumSegments(12);
        request.onlyExpungeDeletes(true);
        request.flush(false);
        assertEquals("Force-merge indices [], maxSegments[12], onlyExpungeDeletes[true], flush[false]", request.getDescription());
    }

}
