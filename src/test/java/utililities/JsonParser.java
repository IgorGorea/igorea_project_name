package utililities;

import api.actions.ApiActions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.reflect.exception.FieldNotFoundException;


public class JsonParser {
    protected static final Logger logger = LogManager.getLogger(ApiActions.class);

    public String extractValueFromResponseBody(String responseBody, String fieldName) {
        try {
            // It uses ObjectMapper to parse the response body into a JSON object
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // Here we extract the value of specific field from the JSON object
            JsonNode fieldValue = jsonNode.get(fieldName);
            if (fieldValue == null) {
                throw new FieldNotFoundException("Field '" + fieldName + "' not found in the response body.");
            }
            return fieldValue.asText();

        } catch (JsonProcessingException e) {
            throw new CustomExceptions.JsonParsingException("Error parsing JSON response body.", e);
        }
    }

    public String extractValueFromResponseBody(String responseBody, String parentField, String fieldName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // We check if the parent object exists in the JSON
            JsonNode parentNode = jsonNode.get(parentField);
            if (parentNode == null || !parentNode.isObject()) {
                throw new IllegalArgumentException("'" + parentField + "' object not found in the response body.");
            }
            // Here we extract the value of specific field from the parent object
            JsonNode fieldValue = parentNode.get(fieldName);
            if (fieldValue == null) {
                throw new IllegalArgumentException("Field '" + fieldName + "' not found in the '" + parentField + "' object.");
            }
            return fieldValue.asText();

        } catch (JsonProcessingException e) {
            throw new CustomExceptions.JsonParsingException("Error parsing JSON response body.", e);
        }
    }

}
