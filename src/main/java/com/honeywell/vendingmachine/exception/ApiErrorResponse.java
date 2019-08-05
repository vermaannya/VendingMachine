package com.honeywell.vendingmachine.exception;

import java.util.List;

public final class ApiErrorResponse {
    private int status;
    private String message;
    private List<ApiErrorObject> errors;

    public ApiErrorResponse(int status, String message, List<ApiErrorObject> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ApiErrorObject> getErrors() {
        return errors;
    }

    public static final class ApiErrorObject {
        private String field;
        private String message;

        public ApiErrorObject(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
