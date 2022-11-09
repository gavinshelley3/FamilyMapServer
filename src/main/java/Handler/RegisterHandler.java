package Handler;

import Request.RegisterRequest;
import Result.RegisterResult;
import Service.RegisterService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import static Handler.StringHandler.readString;
import static Handler.StringHandler.writeString;

public class RegisterHandler implements HttpHandler {
    Gson gson = new Gson();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);
                System.out.println(reqData);

                RegisterRequest request = (RegisterRequest) gson.fromJson(reqData, RegisterRequest.class);
                RegisterService service = new RegisterService();
                RegisterResult result = service.register(request);

                if (result.getAuthToken() == null) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    OutputStream respBody = exchange.getResponseBody();
                    String respData = gson.toJson(result);
                    writeString(respData, respBody);
                    respBody.close();
                    success = true;
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream respBody = exchange.getResponseBody();
                    String respData = gson.toJson(result);
                    writeString(respData, respBody);
                    respBody.close();
                    success = true;
                }
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders((HttpURLConnection.HTTP_SERVER_ERROR), 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}