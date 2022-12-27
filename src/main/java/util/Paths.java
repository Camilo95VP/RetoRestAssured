package util;

public enum Paths {

    REQUEST_JSON_FILE_UPDATE_BOOKING("src/test/resources/files/partialUpdateBooking/partialUpdateBookingRequest.json"),
    REQUEST_JSON_FILE_BOOKING("src/test/resources/files/createBooking/createBookingRequest.json"),
    REQUEST_JSON_FILE_TOKEN("src/test/resources/files/generateToken/generateToken.json");

    private final String value;

    public String getValue() {
        return value;
    }

    Paths(String value){
        this.value=value;
    }
}
