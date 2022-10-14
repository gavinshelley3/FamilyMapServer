package Handler;

public class RequestHandler implements SuperHandler {
    public RequestHandler() {}

    public void handle() {
        System.out.println("Handling...");
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
