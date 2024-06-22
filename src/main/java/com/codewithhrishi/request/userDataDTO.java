package com.codewithhrishi.request;

import com.codewithhrishi.domain.USER_ROLE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class userDataDTO {
	private String fullName;
	private String email;
	private String password;

	private USER_ROLE role;

}
