package com.example.virtualGallery.repository;

import com.example.virtualGallery.model.entity.Category;
import com.example.virtualGallery.model.entity.Painting;
import com.example.virtualGallery.model.enums.ArtCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {

    List<Painting> findAllByArtistId(long id);

    List<Painting> findAllByCategoryCategoryName(ArtCategory category);



}
