package com.example.virtualGallery.web;

import com.example.virtualGallery.model.dto.ArtistDTO;
import com.example.virtualGallery.model.dto.PaintingDTO;
import com.example.virtualGallery.model.dto.view.ViewArtistDTO;
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
public class ArtistController {

    private final ArtistService artistService;
    private final PaintingService paintingService;

    public ArtistController(ArtistService artistService, PaintingService paintingService) {
        this.artistService = artistService;
        this.paintingService = paintingService;
    }

    @ModelAttribute("artistModel")
    public ArtistDTO initUserModel() {
        return new ArtistDTO();
    }

    @GetMapping("/artists")
    public String getArtists(Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        return "artists";
    }

    @GetMapping("/artists/{id}/paintings")
    public String getArtistsPaintings(@PathVariable("id") long id, Model model) {
        ViewArtistDTO artistById = artistService.getArtistById(id);
        model.addAttribute("artist", artistById);
        List<ViewPaintingDTO> paintingsByArtistId = paintingService.getPaintingsByArtistId(id);
        model.addAttribute("paintings", paintingsByArtistId);
        return "artists-paintings";
    }

    @GetMapping("/artists/add")
    public String addNewArtist() {
        return "add-artist";
    }

    @PostMapping("/artists/add")
    public String addArtist(@Valid ArtistDTO artistModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("artistModel", artistModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.artistModel", bindingResult);
            return "redirect:/artists/add";
        }

        this.artistService.addArtist(artistModel);
        return "redirect:/artists";
    }


}
