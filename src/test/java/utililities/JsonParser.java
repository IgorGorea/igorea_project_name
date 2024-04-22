package utililities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    public String extractValueFromResponseBody(String responseBody, String fieldName) {
        try {
            // It uses ObjectMapper to parse the response body into a JSON object
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // Here we extract the value of specific field from the JSON object
            JsonNode fieldValue = jsonNode.get(fieldName);
            if (fieldValue == null) {
                throw new IllegalArgumentException("Field '" + fieldName + "' not found in the response body.");
            }
            return fieldValue.asText();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
