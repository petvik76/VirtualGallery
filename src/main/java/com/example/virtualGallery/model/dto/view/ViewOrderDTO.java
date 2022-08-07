package com.example.virtualGallery.model.dto.view;

import java.time.LocalDate;

public class ViewOrderDTO {

    private long id;

    private LocalDate dateOfOrder;

    private String status;

    private String painting;

    public ViewOrderDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPainting() {
        return painting;
    }

    public void setPainting(String painting) {
        this.painting = painting;
    }


}
