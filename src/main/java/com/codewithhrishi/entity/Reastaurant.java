package com.codewithhrishi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reastaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
	@OneToOne
	private UserData owner;
	private String name;
	private String description;
	private String cuisineType;
	@OneToOne
	private Address address;
	@Embedded
	private ContactInformation contactInformation;
	private String openingHours;
	@OneToMany(mappedBy ="restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
private List<Order>orders=new ArrayList<>();
	@ElementCollection
	@Column(length = 1000)
	private List<String>images;
	
	private LocalDateTime registrationTime;
	private boolean open;
	@JsonIgnore
	@OneToMany(mappedBy ="reastaurant",cascade = CascadeType.ALL)
	private List<Food>foods=new ArrayList<>();
	
}
