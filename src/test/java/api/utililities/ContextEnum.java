package api.utililities;

public enum ContextEnum {
    GET_STATUS_CODE,
    POST_STATUS_CODE,
    DEL_STATUS_CODE,
    FIRST_NAME_PRESENCE,
    NEW_CONTACT_ID;

    private String value;
    private int intValue;
    private boolean boolValue;

    public void saveValue(String value) {
        this.value = value;
    }

    public void saveIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void saveBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return intValue;
    }

    public boolean getBoolValue() {
        return boolValue;
    }

}
