package com.codewithhrishi.DTO;

import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Embeddable
public class RestaurantDto {
	private String title;
	
	private List<String>images;
	private String description;
	private long id;
	
	
}
