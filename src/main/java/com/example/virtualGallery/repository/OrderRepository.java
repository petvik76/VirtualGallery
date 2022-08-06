package com.example.virtualGallery.repository;

import com.example.virtualGallery.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN o.buyer b WHERE b.id = :id")
    List<Order> findByBuyerId(long id);

    @Query("SELECT o FROM Order o JOIN o.painting p WHERE p.id = :id")
    Optional<Order> findByPaintingId(long id);
}
