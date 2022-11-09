package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class PersonHandler implements HttpHandler {
    public PersonHandler() {}

    public void handle() {
        System.out.println("Handling person...");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
}
