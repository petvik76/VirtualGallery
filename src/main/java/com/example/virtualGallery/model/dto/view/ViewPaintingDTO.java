package com.example.virtualGallery.model.dto.view;

import com.example.virtualGallery.model.entity.Artist;
import com.example.virtualGallery.model.entity.Category;
import com.example.virtualGallery.model.enums.ArtCategory;


public class ViewPaintingDTO {

    private long paintingId;

    private String title;

    private String url;

    private String description;

    private double price;

    private String artist;

    private String category;

    public ViewPaintingDTO() {}

    public long getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(long paintingId) {
        this.paintingId = paintingId;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
