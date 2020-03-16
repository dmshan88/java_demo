package com.example.common.core;

/**自定义异常*/
public class CustomException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private final Integer code;
    
    public CustomException(ErrorCode errorCode) {
        this(errorCode, "");
    }
    
    public CustomException(ErrorCode errorCode, String additionalMessage) {
        super(makeAdditionalMessage(errorCode, additionalMessage));
        this.code = checkErrorCode(errorCode).getCode();
    }
    
    public CustomException(ErrorCode errorCode, Exception exception) {
        this(errorCode, "", exception);
    }
    
    public CustomException(ErrorCode errorCode, String additionalMessage, Exception exception) {
        super(makeAdditionalMessage(errorCode, additionalMessage), exception);
        this.code = checkErrorCode(errorCode).getCode();
    }
    
    public Integer getCode() {
        return code;
    }
    
    private static ErrorCode checkErrorCode(ErrorCode errorCode) {
        if (errorCode == null) {
            errorCode = ErrorCode.UNKNOWN;
        }
        return errorCode;
    }
    
    private static String makeAdditionalMessage(ErrorCode errorCode, String message) {
        errorCode = checkErrorCode(errorCode);
        if (message == null || message.isEmpty()) {
            return errorCode.getMessage();
        } else {
            return errorCode.getMessage() + ": " + message;
        }
    }

}
