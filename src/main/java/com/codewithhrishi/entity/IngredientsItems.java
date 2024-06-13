package com.codewithhrishi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private IngredientsCategory category;
	
}
