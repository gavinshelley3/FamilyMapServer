package Result;

public class ClearResult {
    private String message;
    private boolean success;
    public ClearResult(boolean success) {
        if (success) {
            message = "Clear succeeded.";
        }
        else {
            message = "Error:[Description of the error]";
        }
    }

    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ClearResult() {
        message = null;
        success = false;
    }

    public void clear() {

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
