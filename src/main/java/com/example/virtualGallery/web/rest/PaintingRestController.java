package com.example.virtualGallery.web.rest;

import com.example.virtualGallery.model.dto.view.ViewPaintingDTO;
import com.example.virtualGallery.service.PaintingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaintingRestController {

    private final PaintingService paintingService;

    public PaintingRestController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping("/api/paintings")
    public ResponseEntity<List<ViewPaintingDTO>> getAllPaintings() {
        return ResponseEntity.
                ok(paintingService.getAllPaintings());
    }
}
