package com.example.virtualGallery.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VirtualGalleryControllerAdvice {
    @ExceptionHandler({RuntimeException.class})
    public String handleError() {
        return "redirect:/error";
    }
}
