package com.example.virtualGallery.service;

import com.example.virtualGallery.exception.ArtistNotFoundException;
import com.example.virtualGallery.exception.PaintingNotFoundException;
import com.example.virtualGallery.model.dto.PaintingDTO;
import com.example.virtualGallery.model.dto.view.ViewPaintingDTO;
import com.example.virtualGallery.model.entity.Artist;
import com.example.virtualGallery.model.entity.Category;
import com.example.virtualGallery.model.entity.Painting;
import com.example.virtualGallery.model.enums.ArtCategory;
import com.example.virtualGallery.model.mapper.PaintingMapper;
import com.example.virtualGallery.repository.ArtistRepository;
import com.example.virtualGallery.repository.CategoryRepository;
import com.example.virtualGallery.repository.PaintingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaintingService {

    private final PaintingRepository paintingRepository;
    private final PaintingMapper paintingMapper;
    private final ArtistRepository artistRepository;
    private final CategoryRepository categoryRepository;

    public PaintingService(PaintingRepository paintingRepository, PaintingMapper paintingMapper, ArtistRepository artistRepository, CategoryRepository categoryRepository) {
        this.paintingRepository = paintingRepository;
        this.paintingMapper = paintingMapper;
        this.artistRepository = artistRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<ViewPaintingDTO> getPaintingsByArtistId(long id) {
        return this.paintingRepository.findAllByArtistId(id).stream().map(paintingMapper::paintingToPaintingDTO).collect(Collectors.toList());
    }

    public List<ViewPaintingDTO> getPaintingsByCategory(String category) {
        return this.paintingRepository.findAllByCategoryCategoryName(ArtCategory.valueOf(category)).stream().map(paintingMapper::paintingToPaintingDTO).collect(Collectors.toList());
    }

    public ViewPaintingDTO getPaintingById(long id) {
        Painting paintingById = getOrderPaintingById(id);
        return paintingMapper.paintingToPaintingDTO(paintingById);

    }

    public void addPainting(PaintingDTO addPaintingModel) {
        Painting newPainting = paintingMapper.paintingDtoToPainting(addPaintingModel);
        Artist artist = this.artistRepository.findById(addPaintingModel.getArtistId()).orElseThrow(ArtistNotFoundException::new);
        newPainting.setArtist(artist);
        Category byCategoryName = this.categoryRepository.findByCategoryName(ArtCategory.valueOf(addPaintingModel.getCategoryName()));
        newPainting.setCategory(byCategoryName);
        this.paintingRepository.save(newPainting);
    }

    public Painting getOrderPaintingById(long id) {
        return this.paintingRepository.findById(id).orElseThrow(PaintingNotFoundException::new);

    }

    public void removePainting(Painting paintingById) {
        this.paintingRepository.delete(paintingById);
    }


    public List<ViewPaintingDTO> getAllPaintings() {
        return this.paintingRepository.findAll().stream().map(paintingMapper::paintingToPaintingDTO).collect(Collectors.toList());
    }
}
