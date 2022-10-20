package Result;

public class LoadResult {
    private String message;
    private boolean success;

    public LoadResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public void load() {
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