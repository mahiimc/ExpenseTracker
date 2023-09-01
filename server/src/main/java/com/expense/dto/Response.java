package com.expense.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Response<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private transient T data;
	

}
