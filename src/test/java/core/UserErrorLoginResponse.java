package core;

public class UserErrorLoginResponse {

    private String error;

    public UserErrorLoginResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
