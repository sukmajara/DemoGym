package com.demo.gym.domain;

import javax.persistence.*;

@Entity
@Table(name = "transactionmaster")
public class Transaction {

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false, name = "gymclass_id")
    private GymClass gymClass;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    int status;

    public Transaction() {
    }

    public Transaction(User user, Product product, GymClass gymClass, Long id, int status) {
        this.user = user;
        this.product = product;
        this.gymClass = gymClass;
        this.id = id;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public GymClass getGymClass() {
        return gymClass;
    }

    public void setGymClass(GymClass gymClass) {
        this.gymClass = gymClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
