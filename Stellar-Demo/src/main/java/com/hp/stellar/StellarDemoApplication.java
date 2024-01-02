package com.hp.stellar;

import java.security.Principal;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.util.JSONObjectUtils;

import net.minidev.json.JSONObject;

@SpringBootApplication
@EntityScan("com.hp.stellar")
@ComponentScan(basePackages = "com.hp.stellar")
public class StellarDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StellarDemoApplication.class, args);
	}
	
}
