package org.apache.coyote.response;

import java.util.StringJoiner;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.request.HttpVersion;
import org.apache.coyote.support.ResourceUtil;

public class HttpResponse {

    private final HttpVersion httpVersion;
    private HttpStatus httpStatus;
    private String contentType;
    private String responseBody;

    private HttpResponse(final HttpResponseBuilder httpResponseBuilder) {
        this.httpVersion = httpResponseBuilder.httpVersion;
        this.httpStatus = httpResponseBuilder.httpStatus;
        this.contentType = httpResponseBuilder.contentType;
        this.responseBody = httpResponseBuilder.responseBody;
    }

    public static HttpResponse init(final HttpRequest httpRequest) {
        return new HttpResponseBuilder()
                .httpVersion(httpRequest.getHttpVersion())
                .httpStatus(HttpStatus.OK)
                .contentType(ResourceUtil.getContentType(httpRequest.getRequestUri()))
                .build();
    }

    public static class HttpResponseBuilder {

        private HttpVersion httpVersion;
        private HttpStatus httpStatus;
        private String contentType;
        private String responseBody;

        public HttpResponseBuilder() {
        }

        public HttpResponseBuilder httpVersion(final HttpVersion httpVersion) {
            this.httpVersion = httpVersion;
            return this;
        }

        public HttpResponseBuilder httpStatus(final HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public HttpResponseBuilder contentType(final String contentType) {
            this.contentType = contentType;
            return this;
        }

        public HttpResponseBuilder responseBody(final String responseBody) {
            this.responseBody = responseBody;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }
    }

    public String toHttpMessage() {
        final StringJoiner joiner = new StringJoiner("\r\n");
        joiner.add(httpVersion.getValue() + " " + httpStatus.getCode() + " " + httpStatus.getMessage());
        joiner.add("Content-Type: " + contentType);

        if (responseBody != null) {
            joiner.add("Content-Length: " + responseBody.length());
        }
        joiner.add("");
        if (responseBody != null) {
            joiner.add(responseBody);
        }
        return joiner.toString();
    }
}
