package com.example.virtualGallery.model.mapper;


import com.example.virtualGallery.model.dto.ArtistDTO;
import com.example.virtualGallery.model.dto.view.ViewArtistDTO;
import com.example.virtualGallery.model.entity.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(source = "id", target = "artistId")
    ViewArtistDTO artistToArtistDTO(Artist artist);

    Artist artistDtoToArtist(ArtistDTO artistDTO);


    }


