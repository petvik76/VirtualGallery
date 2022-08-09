package com.example.virtualGallery.service;

import com.example.virtualGallery.exception.ArtistNotFoundException;
import com.example.virtualGallery.model.dto.ArtistDTO;
import com.example.virtualGallery.model.dto.view.ViewArtistDTO;
import com.example.virtualGallery.model.entity.Artist;
import com.example.virtualGallery.model.mapper.ArtistMapper;
import com.example.virtualGallery.model.mapper.ArtistMapperImpl;
import com.example.virtualGallery.repository.ArtistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {

    @Mock
    private ArtistRepository mockArtistRepository;

    private Artist testArtist;
    private ArtistService toTest;
    private ArtistMapper artistMapper;


    @BeforeEach
    void setUp() {
        this.artistMapper = new ArtistMapperImpl();
        toTest = new ArtistService(mockArtistRepository, artistMapper);
        this.testArtist = new Artist();
        testArtist.setName("Aa");
        testArtist.setDescription("aaa");
        testArtist.setId(1);

    }

    @Test
    void testGetAllArtists() {
        when(mockArtistRepository.findAll()).thenReturn(List.of(testArtist));
        List<ViewArtistDTO> allArtists = toTest.getAllArtists();
        Assertions.assertEquals(1, allArtists.size());
        Assertions.assertEquals(testArtist.getName(), allArtists.get(0).getName());
        Assertions.assertEquals(testArtist.getId(), allArtists.get(0).getArtistId());
        Assertions.assertEquals(testArtist.getDescription(), allArtists.get(0).getDescription());
    }

    @Test
    void testGetArtistByIdIfArtistExists() {
        when(mockArtistRepository.findById(testArtist.getId())).thenReturn(Optional.of(testArtist));
        ViewArtistDTO artistById = toTest.getArtistById(testArtist.getId());
        Assertions.assertEquals(testArtist.getName(), artistById.getName());
        Assertions.assertEquals(testArtist.getId(), artistById.getArtistId());
        Assertions.assertEquals(testArtist.getDescription(), artistById.getDescription());
    }

    @Test
    void testGetArtistByIdIfArtistNotExists() {
        Assertions.assertThrows(ArtistNotFoundException.class, () -> toTest.getArtistById(2));
    }

    @Test
    void testAddArtist() {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setName("Bb");
        artistDTO.setDescription("bbb");
        when(mockArtistRepository.save(Mockito.any(Artist.class))).thenReturn(new Artist());
        Artist artist = toTest.addArtist(artistDTO);
        verify(mockArtistRepository, times(1)).save(Mockito.any(Artist.class));

    }


}
