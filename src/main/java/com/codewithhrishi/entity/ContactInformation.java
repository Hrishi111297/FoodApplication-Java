package com.codewithhrishi.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactInformation {
	private String email;
	private String mobile;
	private String twitter;
	private String instagram;
	
}
