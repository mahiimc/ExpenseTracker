package com.expense.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long userId;
	
	@NotBlank
	@Length(min = 3, max = 16 , message = "{user.username}")
	private String userName;
	
	@NotBlank
	@Length(min = 3 , max = 16, message = "{user.firstName}")
	private String firstName;
	
	@NotBlank
	@Length(min = 3 , max = 16, message = "{user.lastName}")
	private String lastName;
	
	@Email(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",message = "{user.email}")
	private String email;
	
	@Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*|[\\-])?|[0]?)?([6789]\\d{2}([ -]?)\\d{3}([ -]?)\\d{4})$",message = "{user.mobile}")
	private String mobileNum;
	
	private String profileUrl;
	
	@Length(min = 8 , max = 32, message = "{user.password.length}")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "{user.password.pattern}")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

}
