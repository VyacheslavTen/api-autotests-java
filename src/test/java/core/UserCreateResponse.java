package core;

public class UserCreateResponse {
    private String name;

    private String job;

    private int id;

    private String createdAt;

    public UserCreateResponse(String name, String job, int id, String createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
