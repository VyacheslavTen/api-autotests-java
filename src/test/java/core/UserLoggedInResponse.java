package core;

public class UserLoggedInResponse {

    private String token;


    public UserLoggedInResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
