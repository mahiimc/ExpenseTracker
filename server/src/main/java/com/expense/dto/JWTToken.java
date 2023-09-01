package com.expense.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTToken implements Serializable {
	private static final long serialVersionUID = 1L;
	private String token;
	private String message;
}
