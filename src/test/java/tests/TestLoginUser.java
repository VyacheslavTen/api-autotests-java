package tests;

import core.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;

public class TestLoginUser {

    private static String URL = "https://reqres.in/api/login";

    private static String EMAIL = "eve.holt@reqres.in";

    private static String PASSWORD = "cityslicka";

    @ParameterizedTest(name = "several parameters")
    @Disabled("123")
    @CsvSource(value = {"eve.holt@reqres.in, cityslicka, 200", "dar@mail.ru, cityslicka, 400", "eve.holt@reqres.in, , 400"})
    @Epic("Методы для логина")
    @DisplayName("Тест на логин юзера")
    @Description("успешный логин пользователя")
    public void testLoginUser(String EMAIL, String PASSWORD, int CODE) {
        Allure.step("отправляем запрос");
        UserLoginData userLoginData = new UserLoginData(EMAIL, PASSWORD);
        Response response = given().body(userLoginData).when().contentType(ContentType.JSON).post(URL);
        Assertions.assertEquals(CODE, response.statusCode());
        Allure.step("проверяем ответ");
        if (response.statusCode() == 200) {
            UserLoggedInResponse userLoggedInResponse = response.then().log().all().extract().as(UserLoggedInResponse.class);
            Assertions.assertNotNull(userLoggedInResponse.getToken());
        } else {
            UserLoggedInErrorResponse userLoggedInErrorResponse = response.then().log().all().extract().as(UserLoggedInErrorResponse.class);
            Assertions.assertNotNull(userLoggedInErrorResponse.getError());
        }
        Allure.step("тест прошел успешно");
    }

    @Test
    @Epic("Методы для логина")
    @DisplayName("Тест на проваленный логин юзера")
    @Description("проваленный логин пользователя")
    public void testErrorLoginUser() {
        Allure.step("отправляем запрос");
        UserErrorLoginData userErrorLoginData = new UserErrorLoginData(EMAIL);
        Response response = given().body(userErrorLoginData).when().contentType(ContentType.JSON).post(URL);
        Assertions.assertEquals(400, response.statusCode());
        Allure.step("проверяем ответ");
        UserErrorLoginResponse userErrorLoginResponse = response.then().log().all().extract().as(UserErrorLoginResponse.class);
        Assertions.assertNotNull(userErrorLoginResponse.getError());
        Allure.step("тест прошел успешно");
    }
}
