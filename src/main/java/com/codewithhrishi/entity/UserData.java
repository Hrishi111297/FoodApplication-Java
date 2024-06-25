package com.codewithhrishi.entity;

import java.util.ArrayList;
import java.util.List;

import com.codewithhrishi.DTO.RestaurantDto;
import com.codewithhrishi.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data

public class UserData {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String fullName;
private String email;
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private String password;

private USER_ROLE role;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Order>orders=new ArrayList<>();
@ElementCollection
	private List<RestaurantDto>favourites=new ArrayList<>();
@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Address>adresses=new ArrayList<>();
private String status;
}
