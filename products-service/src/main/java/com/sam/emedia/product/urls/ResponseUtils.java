package com.sam.emedia.product.urls;

import com.sam.emedia.product.models.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static ResponseEntity<ResponseObject> getResponse(ResponseObject responseObject, HttpStatus status) {
        return ResponseEntity.status(status).body(responseObject);
    }


    public static ResponseEntity<ResponseObject> successResponse(Object data, String message, HttpStatus status) {

        return getResponse(ResponseObject.builder()
                .success(true)
                .message(message)
                .data(data).build(), status);
    }
    public static ResponseEntity<ResponseObject> successResponse(Object data, HttpStatus status) {
        return successResponse(data, "", status);
    }
    public static ResponseEntity<ResponseObject> failureResponse(Object data, String message, HttpStatus status) {
        return getResponse(ResponseObject.builder()
                .success(false)
                .message(message)
                .data(data).build(), status);
    }
    public static ResponseEntity<ResponseObject> failureResponse(Object data, HttpStatus status) {
        return failureResponse(data,"",status);
    }

    public static ResponseEntity<ResponseObject> internalServerError(String message) {
        return failureResponse(null,message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ResponseObject> badRequest(String message) {
        return failureResponse(null,message, HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity<ResponseObject> getSuccess(Object data) {
        return getSuccess(data,"");
    }
    public static ResponseEntity<ResponseObject> getSuccess(Object data, String message) {
        return successResponse(data,message, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> createSuccess(Object data, String message) {
        return successResponse(data,"", HttpStatus.CREATED);
    }
    public static ResponseEntity<ResponseObject> createSuccess(Object data) {
        return createSuccess(data,"");
    }

    public static ResponseEntity<ResponseObject> createSuccess() {
        return createSuccess(null,"");
    }
}
