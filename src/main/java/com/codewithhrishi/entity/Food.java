package com.codewithhrishi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
	private String name;
	private  String description;
	
	private long price;
	@ManyToOne
	private Category foodCategory;
	@Column(length = 1000)
	@ElementCollection
	private List<String>images;
	private boolean available;
    @ManyToOne
	private Restaurant restaurant;
	 private boolean isVegiterian;
	 private boolean isSeasonal;
	@ManyToMany
	 private List<IngredientsItems>ingredients=new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	 
	
	
	
	
}
