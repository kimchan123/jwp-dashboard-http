package org.apache.coyote.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HttpVersionTest {

    @Test
    void httpVersion을_반환한다() {
        HttpVersion httpVersion = HttpVersion.from("HTTP/1.1");
        assertThat(httpVersion).isEqualTo(HttpVersion.HTTP_11);
    }
}
