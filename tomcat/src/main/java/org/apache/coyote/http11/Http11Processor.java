package org.apache.coyote.http11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import nextstep.jwp.exception.UncheckedServletException;
import org.apache.coyote.Processor;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Http11Processor implements Runnable, Processor {

    private static final Logger log = LoggerFactory.getLogger(Http11Processor.class);

    private final Socket connection;
    private final Dispatcher dispatcher;

    public Http11Processor(final Socket connection, final Dispatcher dispatcher) {
        this.connection = connection;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        process(connection);
    }

    @Override
    public void process(final Socket connection) {
        try (final var inputStream = connection.getInputStream();
             final var outputStream = connection.getOutputStream();
             final var bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
        ) {

            HttpRequest httpRequest = toHttpRequest(bufferedReader);
            HttpResponse httpResponse = new HttpResponse();
            log.info("path : {}", Thread.activeCount());
            dispatcher.doDispatch(httpRequest, httpResponse);

            outputStream.write(httpResponse.getResponse());
            outputStream.flush();
        } catch (IOException | UncheckedServletException e) {
            log.error(e.getMessage(), e);
        }
    }

    private HttpRequest toHttpRequest(final BufferedReader bufferedReader) throws IOException {
        String requestLine = bufferedReader.readLine();
        List<String> headers = parseHeaders(bufferedReader);
        StringBuilder requestBody = parseBody(bufferedReader);
        return HttpRequest.of(requestLine, headers, requestBody.toString());
    }

    private List<String> parseHeaders(final BufferedReader bufferedReader) throws IOException {
        List<String> requests = new ArrayList<>();
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            if ("".equals(line)) {
                break;
            }
            requests.add(line);
        }
        return requests;
    }

    private StringBuilder parseBody(final BufferedReader bufferedReader) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        while (bufferedReader.ready()) {
            requestBody.append((char) bufferedReader.read());
        }
        return requestBody;
    }
}
