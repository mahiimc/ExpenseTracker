package com.expense.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.expense.dto.Response;

@Component
public class ResponseBuilder<T> {

	public ResponseEntity<Response<T>> build(Integer status, String message, T data) {

		Response<T> response = new Response<>();
		response.setStatus(status);
		response.setMessage(message);
		response.setData(data);

		return ResponseEntity.status(HttpStatusCode.valueOf(status)).body(response);
	}

	public ResponseEntity<Response<T>> build(String message, T data) {
		return build(200,message,data);
	}

	public ResponseEntity<Response<T>> build(String message) {
		return build(200, message, null);
	}
	public ResponseEntity<Response<T>> build(int status,String message) {
		return build(status, message, null);
	}
	public ResponseEntity<Response<T>> build(T data) {
		return build(200,null,data);
	}

}
