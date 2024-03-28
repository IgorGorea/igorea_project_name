package api.utililities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    public static String extractValueFromResponseBody(String responseBody, String fieldName) {
        try {
            // Parse the response body into a JSON object
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // Extract the value of the specified field from the JSON object
            JsonNode fieldValue = jsonNode.get(fieldName);
            if (fieldValue != null) {
                return fieldValue.asText();
            } else {
                throw new IllegalArgumentException("Field '" + fieldName + "' not found in the response body.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
