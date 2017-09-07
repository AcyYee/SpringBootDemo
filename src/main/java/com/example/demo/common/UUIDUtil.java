package com.example.demo.common;

import java.util.UUID;

public class UUIDUtil {

	private final static String order ="mds";
	
	public static String getOrder(){
		UUID uuid = UUID.randomUUID();
		StringBuilder builder = new StringBuilder(order);
		String temp = uuid.toString();
		builder.append(TimeUtill.getDateTimeOR());
		builder.append(temp.replaceAll("-", "").substring(3, 8));
		return builder.toString();
	}
	
	public static String getSMSPassword(){
		UUID uuid = UUID.randomUUID();
		String temp = uuid.toString();
		return temp.replaceAll("-", "").substring(3, 9);
	}

	public static String getSTATE(){
		UUID uuid = UUID.randomUUID();
		String temp = uuid.toString();
		return temp.replaceAll("-", "").substring(3, 11);
	}
	
	public static void main(String[] args) {
		System.out.println(getOrder());
		System.out.println(getSMSPassword());
	}
	
}
