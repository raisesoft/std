package com.cd.bbh.addr;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "id", "name", "letter", "sub" })
public class Country {

	private String name;
	@JsonProperty("sub")
	private List<Province> proviences;

	private String letter;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Province> getProviences() {
		return proviences;
	}

	public void setProviences(List<Province> proviences) {
		this.proviences = proviences;
	}

}
