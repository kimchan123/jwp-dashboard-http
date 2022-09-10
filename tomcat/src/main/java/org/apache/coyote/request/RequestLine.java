package org.apache.coyote.request;

public class RequestLine {

    private static final String REQUEST_LINE_SEPARATOR = " ";
    private static final int HTTP_METHOD_INDEX = 0;
    private static final int REQUEST_URI_INDEX = 1;
    private static final int HTTP_VERSION_INDEX = 2;

    private final HttpMethod httpMethod;
    private final RequestUri requestUri;
    private final HttpVersion httpVersion;

    private RequestLine(final String httpMethod, final String requestUri, final String httpVersion) {
        this.httpMethod = HttpMethod.from(httpMethod);
        this.requestUri = new RequestUri(requestUri);
        this.httpVersion = HttpVersion.from(httpVersion);
    }

    public static RequestLine from(final String requestLine) {
        validateRequestLine(requestLine);
        String[] tokens = requestLine.split(REQUEST_LINE_SEPARATOR);
        return new RequestLine(tokens[HTTP_METHOD_INDEX], tokens[REQUEST_URI_INDEX], tokens[HTTP_VERSION_INDEX]);
    }

    private static void validateRequestLine(final String requestLine) {
        if (requestLine == null) {
            throw new InvalidRequestException();
        }
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getRequestUri() {
        return requestUri.getValue();
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }
}
