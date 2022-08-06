package com.example.virtualGallery.web;

import com.example.virtualGallery.model.dto.PaintingDTO;
import com.example.virtualGallery.model.dto.view.ViewPaintingDTO;
import com.example.virtualGallery.service.ArtistService;
import com.example.virtualGallery.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PaintingController {

    private final PaintingService paintingService;
    private final ArtistService artistService;

    public PaintingController(PaintingService paintingService, ArtistService artistService) {
        this.paintingService = paintingService;
        this.artistService = artistService;
    }

    @ModelAttribute("paintingModel")
    public PaintingDTO initUserModel() {
        return new PaintingDTO();
    }

    @GetMapping("/categories")
    public String allCategories() {
        return "categories";
    }

    @GetMapping("/categories/{category}/paintings")
    public String allPaintings(@PathVariable("category") String category, Model model) {
        model.addAttribute("category", category);
        List<ViewPaintingDTO> paintingsByCategory = paintingService.getPaintingsByCategory(category);
        model.addAttribute("paintings", paintingsByCategory);
        return "categories-paintings.html";
    }

    @GetMapping("/paintings/details/{id}")
    public String paintingDetails(@PathVariable("id") long id, Model model) {
        model.addAttribute("painting", paintingService.getPaintingById(id));
        return "details";
    }

    @GetMapping("/paintings/add")
    public String addNewPainting(Model model) {
        model.addAttribute("artists", this.artistService.getAllArtists());
        return "add-painting";
    }

    @PostMapping("/paintings/add")
    public String addPainting(@Valid PaintingDTO paintingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("paintingModel", paintingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.paintingModel", bindingResult);
            return "redirect:/paintings/add";
        }

        this.paintingService.addPainting(paintingModel);
        return "redirect:/categories";
    }
}
