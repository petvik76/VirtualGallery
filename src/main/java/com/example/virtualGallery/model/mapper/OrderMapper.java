package com.example.virtualGallery.model.mapper;

import com.example.virtualGallery.model.dto.view.ViewOrderDTO;
import com.example.virtualGallery.model.entity.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "painting.title", target = "painting")
    ViewOrderDTO orderToOrderDTO(Order order);
}
