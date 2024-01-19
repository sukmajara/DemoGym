package com.demo.gym.domain;


import javax.persistence.*;

@Entity
@Table(name = "productmaster")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "productName", nullable = false)
	private String productName;
	
	@Column(name = "schedule", nullable = false)
	private String schedule;
	
	@Column(name = "meeting", nullable = false)
	private String meeting;
	
	@Column(name = "duration", nullable = false)
	private String duration;
	
	@Column(name = "price", nullable = false)
	private String price;
	
	@Column(name = "description", nullable = false)
	private String description;

	
	
	public Product() {
		super();
	}

	public Product(String productName, String schedule, String meeting, String duration, String price,
			String description) {
		super();
		this.productName = productName;
		this.schedule = schedule;
		this.meeting = meeting;
		this.duration = duration;
		this.price = price;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getMeeting() {
		return meeting;
	}

	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
