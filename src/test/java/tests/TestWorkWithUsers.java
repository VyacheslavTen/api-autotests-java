package tests;

import core.UserCreateData;
import core.UserCreateResponse;
import core.UserData;
import core.UserUpdateResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class TestWorkWithUsers {
    private static String URL = "https://reqres.in/api/users";

    private static String UPDATE_URL = "https://reqres.in/api/users/2";

    private static String NAME = "John";

    private static String JOB = "COOK";



    @Test
    @Epic("Методы для работы с даными пользователей")
    @DisplayName("Тест на создание юзера")
    @Description("create user")
    public void testCreateUser() {
        Allure.step("отправляем запрос");
        UserCreateData userCreateData = new UserCreateData(NAME, JOB);
        Response response = given().body(userCreateData).when().contentType(ContentType.JSON).post(URL);
        Assertions.assertEquals(201, response.statusCode());
        Allure.step("проверяем ответ");
        UserCreateResponse userCreateResponse = response.then().log().all().extract().as(UserCreateResponse.class);
        Assertions.assertAll(()-> Assertions.assertEquals(NAME, userCreateResponse.getName()),
                ()-> Assertions.assertEquals(JOB, userCreateResponse.getJob()),
                ()-> Assertions.assertNotNull(userCreateResponse.getId()),
                ()-> Assertions.assertNotNull(userCreateResponse.getCreatedAt()));
        Allure.step("тест прошел успешно");
//        Assertions.assertEquals(NAME, userCreateResponse.getName());
//        Assertions.assertEquals(JOB, userCreateResponse.getJob());
//        Assertions.assertNotNull(userCreateResponse.getId());
//        Assertions.assertNotNull(userCreateResponse.getCreatedAt());
    }

    @Test
    @Epic("Методы для работы с даными пользователей")
    @DisplayName("Тест на обновление юзера")
    @Description("пут запрос для юзера")
    public void testUpdateUser() {
        Allure.step("отправляем запрос");
        UserCreateData userCreateData = new UserCreateData(NAME, JOB);
        Response response = given().body(userCreateData).when().contentType(ContentType.JSON).put(UPDATE_URL);
        Assertions.assertEquals(200, response.statusCode());
        Allure.step("проверяем ответ");
        UserUpdateResponse userUpdateResponse = response.then().log().all().extract().as(UserUpdateResponse.class);
        Assertions.assertEquals(NAME, userUpdateResponse.getName());
        Assertions.assertEquals(JOB, userUpdateResponse.getJob());
        Assertions.assertNotNull(userUpdateResponse.getUpdatedAt());
        Allure.step("тест прошел успешно");
    }

    @Test
    @Epic("Методы для работы с даными пользователей")
    @DisplayName("Тест на частичное обновление юзера")
    @Description("патч запрос для юзера")
    public void testPatchUser() {
        UserCreateData userCreateData = new UserCreateData(NAME, JOB);
        Response response = given().body(userCreateData).when().contentType(ContentType.JSON).patch(UPDATE_URL);
        Assertions.assertEquals(200, response.statusCode());
        UserUpdateResponse userUpdateResponse = response.then().log().all().extract().as(UserUpdateResponse.class);
        Assertions.assertEquals(NAME, userUpdateResponse.getName());
        Assertions.assertEquals(JOB, userUpdateResponse.getJob());
        Assertions.assertNotNull(userUpdateResponse.getUpdatedAt());
        Allure.step("тест прошел успешно");
    }

    @Test
    @Epic("Методы для работы с даными пользователей")
    @DisplayName("Тест на удаление юзера")
    @Description("делит запрос для юзера")
    public void testDeleteUser() {
        Allure.step("отправляем запрос");
        UserCreateData userCreateData = new UserCreateData(NAME, JOB);
        Response response = given().body(userCreateData).when().contentType(ContentType.JSON).delete(UPDATE_URL);
        Assertions.assertEquals(204, response.statusCode());
        Allure.step("проверяем ответ");
        Allure.step("тест прошел успешно");
//        UserUpdateResponse userUpdateResponse = response.then().log().all().extract().as(UserUpdateResponse.class);
//        Assertions.assertEquals(NAME, userUpdateResponse.getName());
//        Assertions.assertEquals(JOB, userUpdateResponse.getJob());
//        Assertions.assertNotNull(userUpdateResponse.getUpdatedAt());
    }

    @ParameterizedTest
    @CsvSource(value = {"Sasha, Musician", "Alex, Lawyer"}) // когда несколько параметров
    @Epic("Методы для работы с даными пользователей")
    @DisplayName("Тест на создание юзера")
    @Description("create user")
    public void testCreateUserParameter(String NAME, String JOB) {
        Allure.step("отправляем запрос");
        UserCreateData userCreateData = new UserCreateData(NAME, JOB);
        Response response = given().body(userCreateData).when().contentType(ContentType.JSON).post(URL);
        Assertions.assertEquals(201, response.statusCode());
        Allure.step("проверяем ответ");
        UserCreateResponse userCreateResponse = response.then().log().all().extract().as(UserCreateResponse.class);
        Assertions.assertAll(()-> Assertions.assertEquals(NAME, userCreateResponse.getName()),
                ()-> Assertions.assertEquals(JOB, userCreateResponse.getJob()),
                ()-> Assertions.assertNotNull(userCreateResponse.getId()),
                ()-> Assertions.assertNotNull(userCreateResponse.getCreatedAt()));
        Allure.step("тест прошел успешно");
//        Assertions.assertEquals(NAME, userCreateResponse.getName());
//        Assertions.assertEquals(JOB, userCreateResponse.getJob());
//        Assertions.assertNotNull(userCreateResponse.getId());
//        Assertions.assertNotNull(userCreateResponse.getCreatedAt());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sasha", "Alex"}) // когда один параметр
    @Epic("Методы для работы с даными пользователей")
    @DisplayName("Тест на создание юзера")
    @Description("create user")
    public void testCreateUserSingleParameter(String NAME) {
        Allure.step("отправляем запрос");
        UserCreateData userCreateData = new UserCreateData(NAME, JOB);
        Response response = given().body(userCreateData).when().contentType(ContentType.JSON).post(URL);
        Assertions.assertEquals(201, response.statusCode());
        Allure.step("проверяем ответ");
        UserCreateResponse userCreateResponse = response.then().log().all().extract().as(UserCreateResponse.class);
        Assertions.assertAll(()-> Assertions.assertEquals(NAME, userCreateResponse.getName()),
                ()-> Assertions.assertEquals(JOB, userCreateResponse.getJob()),
                ()-> Assertions.assertNotNull(userCreateResponse.getId()),
                ()-> Assertions.assertNotNull(userCreateResponse.getCreatedAt()));
        Allure.step("тест прошел успешно");
//        Assertions.assertEquals(NAME, userCreateResponse.getName());
//        Assertions.assertEquals(JOB, userCreateResponse.getJob());
//        Assertions.assertNotNull(userCreateResponse.getId());
//        Assertions.assertNotNull(userCreateResponse.getCreatedAt());
    }
}
