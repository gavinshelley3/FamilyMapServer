package Handler;

import Request.LoginRequest;
import Result.LoginResult;
import Service.LoginService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import static Handler.StringHandler.readString;
import static Handler.StringHandler.writeString;

public class LoginHandler implements HttpHandler {
    Gson gson = new Gson();
    public LoginHandler() {}

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);
                System.out.println(reqData);

                LoginRequest request = (LoginRequest) gson.fromJson(reqData, LoginRequest.class);
                LoginService service = new LoginService();
                LoginResult result = service.login(request);

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
        catch(Exception e) {
            exchange.sendResponseHeaders((HttpURLConnection.HTTP_SERVER_ERROR), 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

}
