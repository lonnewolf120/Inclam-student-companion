package codenamex.smc;

import java.io.IOException;

public class fxmlException extends IOException {
    private String errorCode;

    public fxmlException(String message) {
        super(message);
//        this.errorCode = errorCode;
    }
    public fxmlException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void logError() {
        // Implement your logic to log the error message and error code
        System.err.println("Error Code: " + errorCode);
        System.err.println("Error Message: " + getMessage());
    }

    public void sendNotification(String recipient) {
        // Implement your logic to send a notification to the specified recipient
        System.out.println("Sending notification to: " + recipient);
        System.out.println("Error Code: " + errorCode);
        System.out.println("Error Message: " + getMessage());
    }
}