package com.example.virtualGallery.model.dto;


import com.example.virtualGallery.model.entity.Category;
import com.example.virtualGallery.model.enums.ArtCategory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PaintingDTO {

    @NotNull
    @Size(min=2)
    private String title;

    @NotNull
    @Size(min=3)
    private String url;

    @NotNull
    @Size(min=3)
    private String description;

    @NotNull
    @Positive
    private double price;

    @NotNull
    private String categoryName;

    @NotNull
    private long artistId;

    public PaintingDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }
}
