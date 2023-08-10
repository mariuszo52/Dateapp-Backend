package com.dateapp.dateapp.config.webSocket.connectionTicket;

public class TicketDto {
    private Long id;
    private String text;
    private boolean validity;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
