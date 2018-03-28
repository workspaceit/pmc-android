package com.workspaceit.photoclubbingme.backend.entity;

import java.util.Date;

/**
 * Created by vikram on 27-Mar-18.
 */

public class LocationBackgroundImage {
    private int id;
    private String image;
    private Integer locationId;
    private Date createdAt;
    private Admin createdBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationBackgroundImage that = (LocationBackgroundImage) o;

        if (id != that.id) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return createdBy != null ? createdBy.equals(that.createdBy) : that.createdBy == null;
    }

    @Override
    public int hashCode() {
        return id;
    }
}