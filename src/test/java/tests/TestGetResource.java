package tests;

import core.ResourceData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.path.json.JsonPath.given;
import static io.restassured.RestAssured.given;

public class TestGetResource {

    private static String URL = "https://reqres.in/api/uknown";

    private static String URL_SINGLE_RESOURCE = "https://reqres.in/api/uknown/2";

    private static String URL_RESOURCE_NOT_FOUND = "https://reqres.in/api/uknown/23";

    @Test
    public void getListResourceTest() {
        Response response = given().when().contentType(ContentType.JSON).get(URL);
        Assertions.assertEquals(200, response.statusCode());
        List<ResourceData> resource = response.then().log().all().extract().body().jsonPath().getList("data", ResourceData.class);
        Assertions.assertNotNull(resource);
        resource.stream().forEach(resourceData -> Assertions.assertAll(()-> Assertions.assertNotNull(resourceData.getId()),
                ()-> Assertions.assertNotNull(resourceData.getColor()),
                ()-> Assertions.assertNotNull(resourceData.getYear()),
                ()-> Assertions.assertNotNull(resourceData.getPantone_value()),
                ()-> Assertions.assertNotNull(resourceData.getName())));
    }

    @Test
    public void getSingleResourceTest() {
        Response response = given().when().contentType(ContentType.JSON).get(URL_SINGLE_RESOURCE);
        Assertions.assertEquals(200, response.statusCode());
        ResourceData resource = response.then().log().all().extract().body().jsonPath().getObject("data", ResourceData.class);
        Assertions.assertEquals(2001, resource.getYear());
    }

    @Test
    public void getResourceNotFoundTest() {
        Response response = given().when().contentType(ContentType.JSON).get(URL_RESOURCE_NOT_FOUND);
        Assertions.assertEquals(404, response.statusCode());
        ResourceData resource = response.then().log().all().extract().body().jsonPath().getObject("data", ResourceData.class);
        Assertions.assertNull(resource);
    }
}
