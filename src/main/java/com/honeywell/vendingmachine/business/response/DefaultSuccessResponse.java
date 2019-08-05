package com.honeywell.vendingmachine.business.response;

public final class DefaultSuccessResponse {
    private String message;

    public DefaultSuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
