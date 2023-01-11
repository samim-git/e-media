package com.sam.emedia.user.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseObject {
    Object data;
    String message;
    boolean success;
    public static ResponseObject getResponse(Object data, boolean success) {
        return getResponse(data,"",success);
    }
    public static ResponseObject getResponse(Object data, String message, boolean success) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(data);
        responseObject.setSuccess(success);
        responseObject.setMessage(message);
        return responseObject;
    }
}
