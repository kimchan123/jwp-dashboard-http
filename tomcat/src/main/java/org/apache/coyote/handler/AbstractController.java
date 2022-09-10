package org.apache.coyote.handler;

import nextstep.jwp.exception.UncheckedServletException;
import org.apache.coyote.request.HttpMethod;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;

public abstract class AbstractController implements Controller {

    @Override
    public void service(final HttpRequest httpRequest, final HttpResponse httpResponse) {
        if (httpRequest.getHttpMethod() == HttpMethod.GET) {
            doGet(httpRequest, httpResponse);
            return;
        }
        doPost(httpRequest, httpResponse);
    }

    protected void doGet(final HttpRequest httpRequest, final HttpResponse httpResponse) {
        throw new UncheckedServletException(null);
    }

    protected void doPost(final HttpRequest httpRequest, final HttpResponse httpResponse) {
        throw new UncheckedServletException(null);
    }
}
