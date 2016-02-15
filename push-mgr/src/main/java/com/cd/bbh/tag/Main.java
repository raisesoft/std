package com.cd.bbh.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cd.bbh.tag.executor.Executor;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		if (args.length < 1) {
			logger.error("please execute like\"java -jar push-sync-mgr.jar sync\"");
			logger.debug("");
			logger.debug("");
			logger.debug("");
			return;
		}
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring.xml");
		Executor executor = (Executor) context.getBean(args[0]);
		executor.execute();
		context.close();
		logger.debug("");
		logger.debug("");
		logger.debug("");
	}
}
