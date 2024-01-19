package com.demo.gym.event;

import com.demo.gym.domain.User;

public class PaymentEvent {
    private User user;

    public PaymentEvent(User user) {
        this.user = user;
    }
}
