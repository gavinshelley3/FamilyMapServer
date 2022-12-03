package Handler;

import Request.PersonRequest;
import Result.PersonResult;
import Service.PersonService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler {
    Gson gson = new Gson();

    public PersonHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String authToken = null;
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {
                if (exchange.getRequestHeaders().containsKey("Authorization")) {
                    authToken = exchange.getRequestHeaders().getFirst("Authorization");
                }

                if (exchange.getRequestURI().toString().contains("/person/")) {
                    String url = exchange.getRequestURI().toString();
                    String[] urlArray = url.split("/");
                    String personID = urlArray[2];


                    PersonRequest request = new PersonRequest(personID, authToken);
                    PersonService service = new PersonService();
                    PersonResult result = service.getPerson(request);

                    if (result.isSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }
                    OutputStream respBody = exchange.getResponseBody();
                    String gsonString = gson.toJson(result);
                    StringHandler.writeString(gsonString, respBody);
                    respBody.close();
                } else {
                    PersonRequest request = new PersonRequest(null, authToken);
                    PersonService service = new PersonService();
                    PersonResult result = service.getPersons(request);

                    if (result.isSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }
                    OutputStream respBody = exchange.getResponseBody();
                    String gsonString = gson.toJson(result);
                    StringHandler.writeString(gsonString, respBody);
                    respBody.close();
                }
            }
        } catch (Exception e) {
            exchange.sendResponseHeaders((HttpURLConnection.HTTP_SERVER_ERROR), 0);
            exchange.getResponseBody().close();
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
