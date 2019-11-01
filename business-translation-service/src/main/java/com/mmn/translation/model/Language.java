package com.mmn.translation.model;

import java.util.Map;

import lombok.Data;

@Data
public class Language {

	private String locale;
	private Map<String, String> dictionary;
}
