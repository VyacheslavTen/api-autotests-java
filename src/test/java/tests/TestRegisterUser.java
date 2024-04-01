package tests;

import core.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestRegisterUser {

    private static String URL = "https://reqres.in/api/register";

    private static String EMAIL = "eve.holt@reqres.in";

    private static String PASSWORD = "pistole";

    @Test
    @Epic("Методы для регистрации юзеров")
    @DisplayName("Тест на регистрацию юзера")
    @Description("успешная регистрация пользователя")
    public void testRegisterUser() {
        Allure.step("отправляем запрос");
        UserRegisterData userRegisterData = new UserRegisterData(EMAIL, PASSWORD);
        Response response = given().body(userRegisterData).when().contentType(ContentType.JSON).post(URL);
        Assertions.assertEquals(200, response.statusCode());
        Allure.step("проверяем ответ");
        UserRegisterResponse userRegisterResponse = response.then().log().all().extract().as(UserRegisterResponse.class);
        Assertions.assertNotNull(userRegisterResponse.getId());
        Assertions.assertNotNull(userRegisterResponse.getToken());
//        Assertions.assertAll(()-> Assertions.assertEquals(NAME, userCreateResponse.getName()),
//                ()-> Assertions.assertEquals(JOB, userCreateResponse.getJob()),
//                ()-> Assertions.assertNotNull(userRegisterResponse.getId()),
//                ()-> Assertions.assertNotNull(userRegisterResponse.getToken()));

        Allure.step("тест прошел успешно");
    }

    @Test
    @Epic("Методы для регистрации юзеров")
    @DisplayName("Тест на не успешную регистрацию юзера")
    @Description("не успешная регистрация пользователя")
    public void testUnsuccessfulRegisterUser() {
        Allure.step("отправляем запрос");
        UnsuccessfulRegisterData unsuccessfulRegisterData = new UnsuccessfulRegisterData(EMAIL);
        Response response = given().body(unsuccessfulRegisterData).when().contentType(ContentType.JSON).post(URL);
        Assertions.assertEquals(400, response.statusCode());
        Allure.step("проверяем ответ");
        UnsuccessfulRegisterResponse unsuccessfulRegisterResponse = response.then().log().all().extract().as(UnsuccessfulRegisterResponse.class);
        Assertions.assertNotNull(unsuccessfulRegisterResponse.getError());
        Allure.step("тест прошел успешно");
    }
}
