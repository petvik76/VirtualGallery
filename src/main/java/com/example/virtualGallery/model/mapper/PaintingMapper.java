package com.example.virtualGallery.model.mapper;

import com.example.virtualGallery.model.dto.PaintingDTO;
import com.example.virtualGallery.model.dto.view.ViewPaintingDTO;
import com.example.virtualGallery.model.entity.Painting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaintingMapper {

    @Mapping(source = "id", target = "paintingId")
    @Mapping(source = "artist.name", target = "artist")
    @Mapping(source = "category.categoryName", target = "category")
    ViewPaintingDTO paintingToPaintingDTO(Painting painting);


    Painting paintingDtoToPainting(PaintingDTO painting);
}
