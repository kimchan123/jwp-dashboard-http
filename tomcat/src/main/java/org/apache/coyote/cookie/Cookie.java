package org.apache.coyote.cookie;

import java.util.Objects;

public class Cookie {

    private static final String JSESSION = "JSESSIONID";
    private static final String KEY_VALUE_SEPARATOR = "=";
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private final String name;
    private final String value;

    public Cookie(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public static Cookie from(final String request) {
        String[] tokens = request.split(KEY_VALUE_SEPARATOR);
        return new Cookie(tokens[KEY_INDEX], tokens[VALUE_INDEX]);
    }

    public static Cookie ofJsession(final String id) {
        return new Cookie(JSESSION, id);
    }

    public String toHeader() {
        return name + KEY_VALUE_SEPARATOR + value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cookie cookie = (Cookie) o;
        return Objects.equals(name, cookie.name) && Objects.equals(value, cookie.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
