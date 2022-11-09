package Handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class RequestHandler implements HttpHandler {
    public RequestHandler() {}

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                handleGet(exchange);
            } else if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                handlePost(exchange);
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    private void handlePost(HttpExchange exchange) {

    }

    private void handleGet(HttpExchange exchange) throws IOException {
        try {
            boolean success = false;
            Headers reqHeaders = exchange.getRequestHeaders();
            if (reqHeaders.containsKey("Authorization")) {
                String authToken = reqHeaders.getFirst("Authorization");
                System.out.println("Auth token: " + authToken);
            }
            try {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            OutputStream respBody = exchange.getResponseBody();
            InputStream respData = exchange.getRequestBody();
            writeString(respData, respBody);
            try {
                exchange.getResponseBody().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            success = true;
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    private void writeString(InputStream is, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(is.toString());
        sw.flush();
    }

    public void register() {
        System.out.println("Registering...");
    }
    public void login() {
        System.out.println("Logging in...");
    }
    public void clear() {
        System.out.println("Clearing...");
    }
    public void fill() {
        System.out.println("Filling...");
    }
    public void load() {
        System.out.println("Loading...");
    }
    public void person() {
        System.out.println("Getting person...");
    }
    public void event() {
        System.out.println("Getting event...");
    }



}
