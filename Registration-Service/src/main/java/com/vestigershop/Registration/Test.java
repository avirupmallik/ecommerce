package com.vestigershop.Registration;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) throws IOException {
		String a = "3";
		Object o = (String) a;
		if(o instanceof Integer) {
		Integer s = (Integer)o;
		}
		else {
			System.err.println("not instance of integer");
		}
	}
}
