package Handler;

import Request.LoadRequest;
import Result.LoadResult;
import Service.LoadService;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.List;

import static java.nio.file.Files.write;
import static java.nio.file.Files.writeString;

public class LoadHandler implements HttpHandler {
    private Gson gson = new Gson();
    public LoadHandler() {}

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                InputStream reqBody = exchange.getRequestBody();
                String reqData = StringHandler.readString(reqBody);
                System.out.println(reqData);

                LoadRequest request = (LoadRequest)gson.fromJson(reqData, LoadRequest.class);
                LoadService service = new LoadService();
                LoadResult result = service.load(request);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream resBody = exchange.getResponseBody();
                String json = gson.toJson(result);
                StringHandler.writeString(json, resBody);
                resBody.close();
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
