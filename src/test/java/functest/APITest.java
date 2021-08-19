package functest;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import utils.JsonSchemaUtils;

public class APITest {

    private final static String JSON_SCHEMA_ACTIVITY_PATH = "/schema/apiTest-jsonSchema.response.json";

    JsonSchemaUtils jsonSchemaUtils = new JsonSchemaUtils();

    @Test
    public void getTest() {

        Response response = RestAssured.get("https://www.boredapi.com/api/activity/");
        JSONObject jsonObj = new JSONObject(response.getBody().asString());
        System.out.println(jsonObj.toString(4));
        jsonSchemaUtils.checkJsonSchema(JSON_SCHEMA_ACTIVITY_PATH,response.asString());

    }

}
