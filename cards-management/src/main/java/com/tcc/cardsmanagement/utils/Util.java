package com.tcc.cardsmanagement.utils;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class Util {

	@SuppressWarnings("deprecation")
	public static HttpStatus validHttpStatus(String code) {
		Integer validCode = 0;
		
		
		if(!StringUtils.isEmpty(code)) {
		 validCode = Integer.parseInt(code);
		}
		
		if(validCode == 200) {
			return HttpStatus.OK;
		}else if(validCode == 422) {
			return HttpStatus.UNPROCESSABLE_ENTITY;
		}else if(validCode == 404) {
			return HttpStatus.NOT_FOUND;
		}else if(validCode == 1000) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}else if(validCode == 201){
			return HttpStatus.CREATED;
		}else {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
	}
	
	public static String removeAllSpecialCaracters(String value) {
		String newValue = "";
		newValue = value.replaceAll("[^A-Za-z0-9]","");
		return newValue;
	}
}
