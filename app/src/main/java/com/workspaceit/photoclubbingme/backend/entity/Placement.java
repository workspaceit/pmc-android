package com.workspaceit.photoclubbingme.backend.entity;

/**
 * Created by vikram on 27-Mar-18.
 */

public enum Placement {
    tl("Top Left"),
    tc("Top Center"),
    tr("Top Right"),
    cl("Center left"),
    cc("Center"),
    cr("Center right"),
    bl("Bottom Left"),
    bc("Bottom Center"),
    br("Bottom Right");
    private String displayName;

    Placement(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "Placement{" +
                "displayName='" + displayName + '\'' +
                '}';
    }
}
