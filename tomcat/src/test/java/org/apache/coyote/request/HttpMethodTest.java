package org.apache.coyote.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HttpMethodTest {

    @Test
    void httpMethod를_반환한다() {
        HttpMethod httpMethod = HttpMethod.from("GET");
        assertThat(httpMethod).isEqualTo(HttpMethod.GET);
    }
}
