package com.codewithhrishi.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientsCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
	private String name;
	@JsonIgnore
	@ManyToOne	
	private Restaurant restaurant;
	@OneToMany(mappedBy = "category")
	private List<IngredientsItems>ingredientsItems=new ArrayList<>();
	
	
	
}
