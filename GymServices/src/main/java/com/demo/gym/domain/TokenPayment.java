package com.demo.gym.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name  = "tokenpayment")
public class TokenPayment {
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createat;

    @Column(name = "exp_at", nullable = false)
    private LocalDateTime expat;

    @Column(name = "confirm_at", nullable = true)
    private LocalDateTime confirmat;

    public TokenPayment() {
    }

    public TokenPayment(User user, String token, LocalDateTime createat, LocalDateTime expat) {
        this.user = user;
        this.token = token;
        this.createat = createat;
        this.expat = expat;
        this.confirmat = confirmat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreateat() {
        return createat;
    }

    public void setCreateat(LocalDateTime createat) {
        this.createat = createat;
    }

    public LocalDateTime getExpat() {
        return expat;
    }

    public void setExpat(LocalDateTime expat) {
        this.expat = expat;
    }

    public LocalDateTime getConfirmat() {
        return confirmat;
    }

    public void setConfirmat(LocalDateTime confirmat) {
        this.confirmat = confirmat;
    }
}
