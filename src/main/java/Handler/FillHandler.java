package Handler;

import Request.FillRequest;
import Result.FillResult;
import Service.FillService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import static Handler.StringHandler.readString;
import static Handler.StringHandler.writeString;

public class FillHandler implements HttpHandler {
    Gson gson = new Gson();
    public FillHandler() {}

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                if (exchange.getRequestURI().toString().contains("/fill/")) {
                    String url = exchange.getRequestURI().toString();
                    String[] urlArray = url.split("/");
                    String username = urlArray[2];
                    if (exchange.getRequestURI().toString().contains("/fill/" + username + "/")) {
                        String generations = urlArray[3];
                        FillRequest request = new FillRequest(username, Integer.parseInt(generations));
                        FillService service = new FillService();
                        FillResult result = service.fillGen(request);

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                        OutputStream respBody = exchange.getResponseBody();
                        String gsonString = gson.toJson(result);
                        writeString(gsonString,respBody);
                        respBody.close();
                        success = true;
                    }
                    else {
                        FillRequest request = new FillRequest(username);
                        FillService service = new FillService();
                        FillResult result = service.fill(request);

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                        OutputStream respBody = exchange.getResponseBody();
                        String gsonString = gson.toJson(result);
                        writeString(gsonString,respBody);
                        respBody.close();
                        success = true;
                    }
                }
            }
        }
        catch(Exception e) {
            exchange.sendResponseHeaders((HttpURLConnection.HTTP_SERVER_ERROR), 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
