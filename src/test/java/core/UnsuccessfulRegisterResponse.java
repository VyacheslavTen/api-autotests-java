package core;

public class UnsuccessfulRegisterResponse {

    private String error;

    public UnsuccessfulRegisterResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
