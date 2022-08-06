package com.example.virtualGallery.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArtistDTO {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 3)
    private String description;

    public ArtistDTO() {

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
