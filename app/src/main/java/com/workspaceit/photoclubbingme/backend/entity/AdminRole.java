package com.workspaceit.photoclubbingme.backend.entity;

/**
 * Created by vikram on 27-Mar-18.
 */

public class AdminRole {
    private int id;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}