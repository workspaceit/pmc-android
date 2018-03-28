package com.workspaceit.photoclubbingme.backend.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vikram on 27-Mar-18.
 */

public class SentSlideshow {
    private int id;
    private String identifier;
    private PopupAdConstant type;
    private String address;
    private String message;
    private Boolean isSeen;
    private Date createdAt;
    private Event event;
    private Photographer photographer;
    private Set<EventImage> eventImages = new HashSet<EventImage>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public PopupAdConstant getType() {
        return type;
    }

    public void setType(PopupAdConstant type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public Set<EventImage> getEventImages() {
        return eventImages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEventImages(Set<EventImage> eventImages) {
        this.eventImages = eventImages;
    }
}