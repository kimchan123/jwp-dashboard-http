package org.apache.coyote.request;

public class RequestUri {

    private final String value;

    public RequestUri(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
