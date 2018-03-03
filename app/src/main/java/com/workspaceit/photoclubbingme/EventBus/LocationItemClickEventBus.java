package com.workspaceit.photoclubbingme.EventBus;

/**
 * Created by smalam119 on 3/3/18.
 */

public class LocationItemClickEventBus {
    private String message;

    public LocationItemClickEventBus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
