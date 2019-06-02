package com.optum.acoe.CucumberTransform;

import cucumber.api.Transformer;

public class EmailTransform extends Transformer<String>{

	@Override
	public String transform(String email) {
		return email.concat("@optum.com");
	}

}
