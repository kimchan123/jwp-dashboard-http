package org.apache.coyote.request;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum HttpVersion {

    HTTP_11("HTTP/1.1");

    private final String value;

    HttpVersion(final String value) {
        this.value = value;
    }

    public static HttpVersion from(final String httpVersion) {
        return Arrays.stream(values())
                .filter(version -> version.value.equals(httpVersion))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 HttpVersion은 존재하지 않습니다."));
    }

    public String getValue() {
        return value;
    }
}
