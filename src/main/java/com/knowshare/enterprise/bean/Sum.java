package com.knowshare.enterprise.bean;

import org.springframework.stereotype.Component;

@Component
public class Sum implements ISum{
	
	public int sum(int a, int b){
		return a + b;
	}
}
