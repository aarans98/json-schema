package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import functest.APITest;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;

import java.util.logging.Logger;

public class JsonSchemaUtils {

    private static Logger LOGGER= Logger.getLogger(String.valueOf(JsonSchemaUtils.class));

    private ObjectMapper objMapper = new ObjectMapper();

    public void checkJsonSchema(String jsonSchemaPath, String jsonSubject) throws ValidationException {

        JSONObject retVal = new JSONObject();

        try {
            JSONObject jsonSchema = new JSONObject(new JSONTokener(APITest.class.getResourceAsStream(jsonSchemaPath)));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(objMapper.convertValue(jsonSubject, JSONObject.class));
            retVal.put("errorMessage","");
        } catch (ValidationException ex) {
            ex.printStackTrace();
            LOGGER.info("JSON Schema Error Message: " + ex.getMessage());
            retVal.put("errorMessage",ex.getMessage());
            Assert.assertEquals(retVal.getString("errorMessage"), "");
        }
        Assert.assertEquals(retVal.getString("errorMessage"), "");
    }
}

