package com.workspaceit.photoclubbingme.backend.entity;

/**
 * Created by vikram on 27-Mar-18.
 */

import java.util.*;

/**
 * Created by anik on 12/20/17.
 */

public class Location {

    private int id;

    private String name;

    private String address;

    private State state;

    private City city;

    private String zip;

    private String phone;

    private String locationLogo;

    private Boolean hasSlideshow;

    private Double durationSpeed;

    private Double breakTime;

    private Double fadeInTime;

    private Double fadeOutTime;

    private Date createdAt;

    private Date updatedAt;

    private Admin createdBy;

    Set<LocationBackgroundImage> locationBackgroundImages;

    private Boolean active;

    private Boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocationLogo() {
        return locationLogo;
    }

    public void setLocationLogo(String locationLogo) {
        this.locationLogo = locationLogo;
    }

    public Boolean getHasSlideshow() {
        return hasSlideshow;
    }

    public void setHasSlideshow(Boolean hasSlideshow) {
        this.hasSlideshow = hasSlideshow;
    }

    public Double getDurationSpeed() {
        return durationSpeed;
    }

    public void setDurationSpeed(Double durationSpeed) {
        this.durationSpeed = durationSpeed;
    }

    public Double getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Double breakTime) {
        this.breakTime = breakTime;
    }

    public Double getFadeInTime() {
        return fadeInTime;
    }

    public void setFadeInTime(Double fadeInTime) {
        this.fadeInTime = fadeInTime;
    }

    public Double getFadeOutTime() {
        return fadeOutTime;
    }

    public void setFadeOutTime(Double fadeOutTime) {
        this.fadeOutTime = fadeOutTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }

    public Set<LocationBackgroundImage> getLocationBackgroundImages() {
        return locationBackgroundImages;
    }

    public void setLocationBackgroundImages(Set<LocationBackgroundImage> locationBackgroundImages) {
        this.locationBackgroundImages = locationBackgroundImages;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (id != location.id) return false;
        if (name != null ? !name.equals(location.name) : location.name != null) return false;
        if (address != null ? !address.equals(location.address) : location.address != null) return false;
        if (state != null ? !state.equals(location.state) : location.state != null) return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        if (zip != null ? !zip.equals(location.zip) : location.zip != null) return false;
        if (phone != null ? !phone.equals(location.phone) : location.phone != null) return false;
        if (locationLogo != null ? !locationLogo.equals(location.locationLogo) : location.locationLogo != null)
            return false;
        if (hasSlideshow != null ? !hasSlideshow.equals(location.hasSlideshow) : location.hasSlideshow != null)
            return false;
        if (durationSpeed != null ? !durationSpeed.equals(location.durationSpeed) : location.durationSpeed != null)
            return false;
        if (breakTime != null ? !breakTime.equals(location.breakTime) : location.breakTime != null) return false;
        if (fadeInTime != null ? !fadeInTime.equals(location.fadeInTime) : location.fadeInTime != null) return false;
        if (fadeOutTime != null ? !fadeOutTime.equals(location.fadeOutTime) : location.fadeOutTime != null)
            return false;
        if (createdAt != null ? !createdAt.equals(location.createdAt) : location.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(location.updatedAt) : location.updatedAt != null) return false;
        if (createdBy != null ? !createdBy.equals(location.createdBy) : location.createdBy != null) return false;
        if (locationBackgroundImages != null ? !locationBackgroundImages.equals(location.locationBackgroundImages) : location.locationBackgroundImages != null)
            return false;
        if (active != null ? !active.equals(location.active) : location.active != null) return false;
        return deleted != null ? deleted.equals(location.deleted) : location.deleted == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (locationLogo != null ? locationLogo.hashCode() : 0);
        result = 31 * result + (hasSlideshow != null ? hasSlideshow.hashCode() : 0);
        result = 31 * result + (durationSpeed != null ? durationSpeed.hashCode() : 0);
        result = 31 * result + (breakTime != null ? breakTime.hashCode() : 0);
        result = 31 * result + (fadeInTime != null ? fadeInTime.hashCode() : 0);
        result = 31 * result + (fadeOutTime != null ? fadeOutTime.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (locationBackgroundImages != null ? locationBackgroundImages.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", state=" + state +
                ", city=" + city +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", locationLogo='" + locationLogo + '\'' +
                ", hasSlideshow=" + hasSlideshow +
                ", durationSpeed=" + durationSpeed +
                ", breakTime=" + breakTime +
                ", fadeInTime=" + fadeInTime +
                ", fadeOutTime=" + fadeOutTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", createdBy=" + createdBy +
                ", locationBackgroundImages=" + locationBackgroundImages +
                ", active=" + active +
                ", deleted=" + deleted +
                '}';
    }


}