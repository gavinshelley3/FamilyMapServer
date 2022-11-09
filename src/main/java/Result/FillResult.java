package Result;

public class FillResult {
    private String message;
    private boolean success;
    public FillResult() {

    }

    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public void fill() {
        message = null;
        success = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
