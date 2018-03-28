package com.workspaceit.photoclubbingme.backend.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vikram on 27-Mar-18.
 */

public class Event {
    private int id;
    private String name;
    private Date startsAt;
    private Date endsAt;
    private Boolean eventPrivate;
    private Venue venue;
    private Location location;
    private String eventPhoto;
    private boolean isAllAdvertiser;
    private Date createdAt;
    private Date updatedAt;
    private Admin createdBy;
    private Set<Photographer> photographers = new HashSet<>();
    private Set<Watermark> watermarks = new HashSet<>();
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

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public Boolean getEventPrivate() {
        return eventPrivate;
    }

    public void setEventPrivate(Boolean eventPrivate) {
        this.eventPrivate = eventPrivate;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(String eventPhoto) {
        this.eventPhoto = eventPhoto;
    }

    public boolean getIsAllAdvertiser() {
        return isAllAdvertiser;
    }

    public void setIsAllAdvertiser(boolean allAdvertiser) {
        isAllAdvertiser = allAdvertiser;
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

    public Set<Photographer> getPhotographers() { return photographers; }

    public void setPhotographers(Set<Photographer> photographers) { this.photographers = photographers; }

    public Set<Watermark> getWatermarks() {
        return this.watermarks;
    }

    public void setWatermarks(Set<Watermark> watermarks) {
        this.watermarks = watermarks;
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

        Event event = (Event) o;

        if (id != event.id) return false;
        return name != null ? name.equals(event.name) : event.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}