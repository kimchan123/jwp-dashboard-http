package org.apache.coyote.request;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum HttpMethod {

    GET("GET"),
    ;

    private final String value;

    HttpMethod(final String value) {
        this.value = value;
    }

    public static HttpMethod from(final String method) {
        return Arrays.stream(values())
                .filter(httpMethod -> httpMethod.value.equals(method))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 HttpMethod는 존재하지 않습니다."));
    }
}
