package com.cd.bbh;

import com.cd.bbh.common.Processor;
import com.cd.bbh.login.RegistorProcessor;

public class Executor {

	public static void main(String[] args) throws Exception {
		Processor processor = new RegistorProcessor();
		// Processor processor = new LoginProcessor();
		// Processor processor = new PasswordUpdateProcessor();
		// Processor processor = new SchoolParentProcessor();
		// Processor processor = new HomeSearchProcessor();

		System.out.println(processor.execute());;
	}
}
