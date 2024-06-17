package com.codewithhrishi.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JwtResponse {
private String token;
//we are sending only token to frontend
//private String username;
//private EmployeeDto employeeData;

}
