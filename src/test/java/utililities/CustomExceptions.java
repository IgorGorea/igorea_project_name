package utililities;

public class CustomExceptions {
    public static class JsonParsingException extends RuntimeException {
        public JsonParsingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
