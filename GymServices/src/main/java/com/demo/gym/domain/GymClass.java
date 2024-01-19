package com.demo.gym.domain;

import javax.persistence.*;


@Entity
@Table(name  = "classmaster" )
public class GymClass {
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "product_id")
	private Product product;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "status", nullable = false)
	String status;
	
	@Column(name = "quota", nullable = false)
	int quota;

	public GymClass() {
		super();
	}

	public GymClass(User user, Product product, String status, int quota) {
		super();
		this.user = user;
		this.product = product;
		this.status = status;
		this.quota = quota;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}
	
	
}
