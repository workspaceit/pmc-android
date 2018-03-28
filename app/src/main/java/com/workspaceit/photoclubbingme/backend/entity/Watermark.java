package com.workspaceit.photoclubbingme.backend.entity;

import android.util.Size;

import java.util.Date;

/**
 * Created by vikram on 27-Mar-18.
 */

public class Watermark {
    private int id;
    private String name;
    private WatermarkType type;
    private String sampleImageName;
    private String logoImage;
    private Size size;
    private Double fade;
    private String watermarkText;
    private Font font;
    private Placement placement;
    private String color;
    private Date createdAt;
    private Date updatedAt;
    private Admin createdBy;
    private Boolean active;
    private Boolean deleted;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "watermarks", fetch = FetchType.EAGER)
//    private Set<Event> events = new HashSet<Event>();


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

    public WatermarkType getType() {
        return type;
    }

    public void setType(WatermarkType type) {
        this.type = type;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getSampleImageName() {
        return sampleImageName;
    }

    public void setSampleImageName(String sampleImageName) {
        this.sampleImageName = sampleImageName;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Double getFade() {
        return fade;
    }

    public void setFade(Double fade) {
        this.fade = fade;
    }

    public String getWatermarkText() {
        return watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
//
//    public Set<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(Set<Event> events) {
//        this.events = events;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Watermark watermark = (Watermark) o;
        if (id != watermark.id) return false;
        if (name != null ? !name.equals(watermark.name) : watermark.name != null) return false;
        if (type != watermark.type) return false;
        if (sampleImageName != null ? !sampleImageName.equals(watermark.sampleImageName) : watermark.sampleImageName != null) return false;
        if (logoImage != null ? !logoImage.equals(watermark.logoImage) : watermark.logoImage != null) return false;
        if (size != watermark.size) return false;
        if (fade != null ? !fade.equals(watermark.fade) : watermark.fade != null) return false;
        if (watermarkText != null ? !watermarkText.equals(watermark.watermarkText) : watermark.watermarkText != null)
            return false;
        if (font != null ? !font.equals(watermark.font) : watermark.font != null) return false;
        if (placement != watermark.placement) return false;
        if (color != null ? !color.equals(watermark.color) : watermark.color != null) return false;
        if (createdAt != null ? !createdAt.equals(watermark.createdAt) : watermark.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(watermark.updatedAt) : watermark.updatedAt != null) return false;
        if (createdBy != null ? !createdBy.equals(watermark.createdBy) : watermark.createdBy != null) return false;
        if (active != null ? !active.equals(watermark.active) : watermark.active != null) return false;
        return deleted != null ? deleted.equals(watermark.deleted) : watermark.deleted == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (sampleImageName != null ? sampleImageName.hashCode() : 0);
        result = 31 * result + (logoImage != null ? logoImage.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (fade != null ? fade.hashCode() : 0);
        result = 31 * result + (watermarkText != null ? watermarkText.hashCode() : 0);
        result = 31 * result + (font != null ? font.hashCode() : 0);
        result = 31 * result + (placement != null ? placement.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}