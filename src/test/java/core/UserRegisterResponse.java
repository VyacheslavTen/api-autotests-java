package core;

public class UserRegisterResponse {


    private String id;

    private String token;

    public UserRegisterResponse(String email, String password, String id, String token) {
        this.id = id;
        this.token = token;
    }



    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
