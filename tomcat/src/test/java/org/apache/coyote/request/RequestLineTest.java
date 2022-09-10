package org.apache.coyote.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class RequestLineTest {

    @Test
    void requestLine이_null이면_예외가_발생한다() {
        String requestLine = null;
        assertThatThrownBy(() -> RequestLine.from(requestLine))
                .isInstanceOf(InvalidRequestException.class);

    }

    @Test
    void requestLine을_읽어_파싱한다() {
        String requestLine = "GET /index.html HTTP/1.1";
        RequestLine actual = RequestLine.from(requestLine);
        assertThat(actual).extracting("httpMethod", "requestUri", "httpVersion")
                .containsExactly(HttpMethod.GET, "/index.html", "HTTP/1.1");
    }
}
