package com.ssafy.happyhouse.model.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
	int code;
	String error;
	Object result;

	public static ResponseEntity<Response> newError(HttpStatus status, String error) {
		return new ResponseEntity<Response>(new Response(0, error, null), status);
	}

	public static ResponseEntity<Response> newResult(HttpStatus status, Object result) {
		return new ResponseEntity<Response>(new Response(0, null, result), status);
	}
}
