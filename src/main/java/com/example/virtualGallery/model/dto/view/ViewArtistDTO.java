package com.example.virtualGallery.model.dto.view;

import javax.persistence.Column;
import java.util.List;

public class ViewArtistDTO {

    private long artistId;

    private String name;

    private String description;

    public ViewArtistDTO() {}

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
