package com.example.virtualGallery.service;

import com.example.virtualGallery.exception.ArtistNotFoundException;
import com.example.virtualGallery.model.dto.ArtistDTO;
import com.example.virtualGallery.model.dto.view.ViewArtistDTO;
import com.example.virtualGallery.model.entity.Artist;
import com.example.virtualGallery.model.entity.Painting;
import com.example.virtualGallery.model.mapper.ArtistMapper;
import com.example.virtualGallery.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public List<ViewArtistDTO> getAllArtists() {
         return this.artistRepository.findAll().stream().map(artistMapper::artistToArtistDTO).collect(Collectors.toList());
    }

    public ViewArtistDTO getArtistById(long id) {
        Artist artist = this.artistRepository.findById(id).orElseThrow(ArtistNotFoundException::new);

        return artistMapper.artistToArtistDTO(artist);


    }

    public Artist addArtist(ArtistDTO artistModel) {
        Artist newArtist = artistMapper.artistDtoToArtist(artistModel);
        return this.artistRepository.save(newArtist);
    }
}
