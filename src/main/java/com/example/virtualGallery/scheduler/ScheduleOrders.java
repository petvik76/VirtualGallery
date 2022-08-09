package com.example.virtualGallery.scheduler;

import com.example.virtualGallery.interceptor.LogInterceptor;
import com.example.virtualGallery.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduleOrders {
    private static final Logger log = LoggerFactory.getLogger(ScheduleOrders.class);
    private final OrderService orderService;

    public ScheduleOrders(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void countOrders() {
        long ordersCount = this.orderService.countOrders();
        log.info("The orders count is " + ordersCount + " on " + LocalDate.now());

    }
}
