package io.swagger;

public class MainApiException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public MainApiException(int statusCode, String statusMessage) {
        super();
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    
    public static final MainApiException INTERNAL_SERVER_ERROR = new MainApiException(500, "Internal Server Error"); 
}