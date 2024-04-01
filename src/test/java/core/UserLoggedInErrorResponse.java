package core;

public class UserLoggedInErrorResponse {

    private String error;

    public String getError() {
        return error;
    }

    public UserLoggedInErrorResponse(String error) {
        this.error = error;
    }
}
