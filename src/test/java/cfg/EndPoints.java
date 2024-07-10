package cfg;

public enum EndPoints {
    BASEURL("https://thinking-tester-contact-list.herokuapp.com"),
    CONTACTS("/contacts"),
    USERS("/users"),
    USER_ME("/users/me");

    private final String endPoint;
    EndPoints(String value){this.endPoint = value;}
    public String getEndPoint(){return endPoint;}
}
