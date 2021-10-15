package com.matu.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.matu.mvc.models.StdQuittances;
//import com.matu.mvc.models.StdQuittances;
import com.matu.mvc.models.WAFAIMA_Contrats;
import com.matu.mvc.models.WAFAIMA_User;
import com.matu.mvc.repositories.StdQuittancesRepository;
//import com.matu.mvc.repositories.StdQuittancesRepository;
import com.matu.mvc.repositories.WAFAIMA_ContratsRpository;
import com.matu.mvc.repositories.WAFAIMA_UserRepository;

@SpringBootApplication
public class Matu_Wafa_Ima_Application  extends SpringBootServletInitializer {

	@Autowired
	WAFAIMA_UserRepository user;
	@Autowired
	StdQuittancesRepository stdquittanesQuittancesRepository;

	public static void main(String[] args) {
		SpringApplication.run(Matu_Wafa_Ima_Application.class, args);
	}

	
	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(Matu_Wafa_Ima_Application.class);
	   }

}
