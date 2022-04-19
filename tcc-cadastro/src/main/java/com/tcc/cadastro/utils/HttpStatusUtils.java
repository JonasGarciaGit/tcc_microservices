package com.tcc.cadastro.utils;

import org.springframework.http.HttpStatus;

public class HttpStatusUtils {

	public static HttpStatus validHttpStatus(String code) {
		if("200".equals(code)) {
			return HttpStatus.OK;
		}else if("201".equals(code)) {
			return HttpStatus.CREATED;
		}else if("422".equals(code)) {
			return HttpStatus.UNPROCESSABLE_ENTITY;
		}else if("404".equals(code)) {
			return HttpStatus.NOT_FOUND;
		}else{
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
}
