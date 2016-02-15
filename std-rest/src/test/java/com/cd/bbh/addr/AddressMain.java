package com.cd.bbh.addr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressMain {
	public static void main(String[] args) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(new File("C:/Users/aowin/works/24技术实现/源码管理/java/bbh-wsapi/src/test/resources/areas.json"));
		Country country = mapper.convertValue(node, Country.class);

		List<Province> provinces = country.getProviences();

		int i = 1000;
		int j = 1000;
		int p = 1000;
		for (Province province : provinces) {
			province.setId(i++);
			List<City> cities = province.getCities();
			for (City city : cities) {
				city.setId(j++);
				List<County> counties = city.getCounties();
				for (County county : counties) {
					county.setId(p++);
				}
			}
		}
		
		System.out.println(mapper.writeValueAsString(country));
	}
}
