package com.cd.bbh.addr;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressSqlMain {
	private static Logger logger = LoggerFactory.getLogger(AddressSqlMain.class);

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(new File("C:/Users/aowin/works/24技术实现/源码管理/java/bbh-wsapi/src/test/resources/areas.json"));

		Country country = mapper.convertValue(node, Country.class);

		List<Province> provinces = country.getProviences();

		int p = 1000;
		int c = 1000;
		for (Province province : provinces) {
			logger.debug("INSERT INTO `BBHBase`.`TB_PROVINCE`(`COUNTRYID`,`NAME`)VALUES('1000','" + province.getName() + "');");
			List<City> citys = province.getCities();
			for (City city : citys) {
				logger.debug("INSERT INTO `BBHBase`.`TB_CITY`(`COUNTRYID`,`PROVINCEID`,`NAME`)VALUES(1000," + p + ",'" + city.getName() + "');");
				List<County> countys = city.getCounties();
				for (County county : countys) {
					logger.debug("INSERT INTO `BBHBase`.`TB_COUNTY`(`COUNTRYID`,`PROVINCEID`,`CITYID`,`NAME`)VALUES(1000," + p + "," + c + ",'" + county.getName() + "');");
				}
				c++;
			}
			p++;
		}
	}
}
