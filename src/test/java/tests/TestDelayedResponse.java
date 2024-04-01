package tests;

import core.DelayedData;
import core.ResourceData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestDelayedResponse {

    private static String URL = "https://reqres.in/api/users?delay=3";

    @Test
    @Epic("Метод для запроса с задержкой")
    @DisplayName("Тест с задержкой")
    @Description("200 тест с задержкой")
    public void getListResourceTest() {
        Response response = given().when().contentType(ContentType.JSON).get(URL);
        Assertions.assertEquals(200, response.statusCode());
        List<DelayedData> resource = response.then().log().all().extract().body().jsonPath().getList("data", DelayedData.class);
//        Assertions.assertNotNull(resource);
        resource.stream().forEach(delayedData -> Assertions.assertAll(()-> Assertions.assertNotNull(delayedData.getId()),
                ()-> Assertions.assertNotNull(delayedData.getEmail()),
                ()-> Assertions.assertNotNull(delayedData.getFirst_name()),
                ()-> Assertions.assertNotNull(delayedData.getLast_name()),
                ()-> Assertions.assertNotNull(delayedData.getAvatar())));
    }
}
