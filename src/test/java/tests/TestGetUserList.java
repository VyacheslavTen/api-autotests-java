package tests;

import core.UserData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestGetUserList {
    private static String URL = "https://reqres.in/api/users?page=2";

    private static String URL_SINGLE_USER = "https://reqres.in/api/users/2";

    private static String URL_SINGLE_USER_NOT_FOUND = "https://reqres.in/api/users/23";

    @Test
    public void checkEmailTest() {
        Response response = given().when().contentType(ContentType.JSON).get(URL);
        Assertions.assertEquals(200, response.statusCode());
        List<UserData> users = response.then().log().all().extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(user->Assertions.assertTrue(user.getEmail().endsWith("@reqres.in")));
    }

    @Test
    public void checkAvatarTest() {
        Response response = given().when().contentType(ContentType.JSON).get(URL);
        Assertions.assertEquals(200, response.statusCode());
        List<UserData> users = response.then().log().all().extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(user->Assertions.assertTrue(user.getAvatar().contains(String.valueOf(user.getId()))));
    }

    @Test
    public void getSingleUserTest() {
        Response response = given().when().contentType(ContentType.JSON).get(URL_SINGLE_USER);
        Assertions.assertEquals(200, response.statusCode());
        UserData user = response.then().log().all().extract().body().jsonPath().getObject("data", UserData.class);
        Assertions.assertTrue(user.getEmail().endsWith("@reqres.in"));
        Assertions.assertTrue(user.getAvatar().contains(String.valueOf(user.getId())));
    }

    @Test
    public void getSingUserNotFoundTest() {
        Response response = given().when().contentType(ContentType.JSON).get(URL_SINGLE_USER_NOT_FOUND);
        Assertions.assertEquals(404, response.statusCode());
        UserData user = response.then().log().all().extract().body().jsonPath().getObject("data", UserData.class);
        Assertions.assertNull(user);
    }

}
