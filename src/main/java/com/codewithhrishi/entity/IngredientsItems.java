package com.codewithhrishi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientsItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
	private String name;
	@ManyToOne
	private IngredientsCategory category;
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	private boolean inDStock=true;
	
}
