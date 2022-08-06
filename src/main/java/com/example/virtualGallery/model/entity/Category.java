package com.example.virtualGallery.model.entity;

import com.example.virtualGallery.model.enums.ArtCategory;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "category_name")
    private ArtCategory categoryName;

    private String description;

    public Category() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArtCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(ArtCategory categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
