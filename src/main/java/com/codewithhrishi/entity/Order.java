package com.codewithhrishi.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
	@ManyToOne
	private UserData customer;
	@ManyToOne
	private Restaurant restaurant;
	private long orderAmount;
	private String orderStatus;
	
	private Date createdAt;
	@ManyToOne
	private Address deliveryAddress;
	@OneToMany
	private List<OrderItem>items;
	private int totalItem;
	private long totalPrice;
	
	
	
	
	
	
	
}
