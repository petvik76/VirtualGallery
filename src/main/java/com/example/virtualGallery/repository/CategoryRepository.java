package com.example.virtualGallery.repository;

import com.example.virtualGallery.model.entity.Category;
import com.example.virtualGallery.model.enums.ArtCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(ArtCategory categoryName);

}
