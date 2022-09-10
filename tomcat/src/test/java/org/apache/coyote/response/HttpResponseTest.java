package org.apache.coyote.response;

import org.apache.coyote.request.HttpVersion;
import org.apache.coyote.response.HttpResponse.HttpResponseBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HttpResponseTest {

    @Test
    void httpMessage로_변환한다() {
        final HttpResponse response = new HttpResponseBuilder()
                .httpVersion(HttpVersion.HTTP_11)
                .httpStatus(HttpStatus.OK)
                .contentType("text/html")
                .responseBody("Hello")
                .build();
        final String actual = response.toHttpMessage();

        Assertions.assertThat(actual)
                .isEqualTo(String.join("\r\n",
                        "HTTP/1.1 200 OK",
                        "Content-Type: text/html",
                        "Content-Length: 5",
                        "",
                        "Hello"));
    }
}
