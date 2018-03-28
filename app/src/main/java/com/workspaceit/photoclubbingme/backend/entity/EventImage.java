package com.workspaceit.photoclubbingme.backend.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vikram on 27-Mar-18.
 */

public class EventImage {
    private int id;
    private Event event;
    private String image;
    private Watermark watermark;
    private Boolean isActive;
    private Boolean inSlideshow;
    private Boolean isDeleted;
    private Date createdAt;
    private Date updatedAt;
    private Photographer createdBy;

    private Set<SentSlideshow> sentSlideshows = new HashSet<SentSlideshow>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getInSlideshow() {
        return inSlideshow;
    }

    public void setInSlideshow(Boolean inSlideshow) {
        this.inSlideshow = inSlideshow;
    }

    public Set<SentSlideshow> getSentSlideshows() {
        return sentSlideshows;
    }

    public void setSentSlideshows(Set<SentSlideshow> sentSlideshows) {
        this.sentSlideshows = sentSlideshows;
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

    public Photographer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Photographer createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}