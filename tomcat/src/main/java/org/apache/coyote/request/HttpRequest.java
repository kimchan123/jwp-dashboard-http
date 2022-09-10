package org.apache.coyote.request;

public class HttpRequest {

    private final RequestLine requestLine;

    public HttpRequest(final RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public HttpMethod getHttpMethod() {
        return requestLine.getHttpMethod();
    }

    public String getRequestUri() {
        return requestLine.getRequestUri();
    }

    public HttpVersion getHttpVersion() {
        return requestLine.getHttpVersion();
    }
}
