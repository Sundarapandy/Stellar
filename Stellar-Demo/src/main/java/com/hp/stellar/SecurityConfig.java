package com.hp.stellar;


import java.text.ParseException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.util.JSONObjectUtils;

import net.minidev.json.JSONObject;

@Configuration
@EnableWebSecurity
@RestController
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {

			http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated(); //Comment once OAuth2 is ready is up
		
//			http.authorizeRequests()
//					.antMatchers( "/openid/**","/css/**", "/images/**").permitAll()
//					.anyRequest().authenticated().and()
//					.oauth2Login().redirectionEndpoint().baseUri("/ebiz/home/user");

		http.csrf().disable();

		http.sessionManagement().maximumSessions(1);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

	}
	
	@GetMapping("openid")
	public JSONObject decodeJWT(@RequestHeader Map<String, String> headers) {

		String functionName = "homeRedirect";

		String jwt = headers.get("authorization");
		String[] split_string = jwt.split("\\.");
		String base64EncodedHeader = split_string[0];
		String base64EncodedBody = split_string[1];
		Base64 base64Url = new Base64(true);
		String header = new String(base64Url.decode(base64EncodedHeader));
		String body = new String(base64Url.decode(base64EncodedBody));

		JSONObject json = new JSONObject();
		try {
			
			json = JSONObjectUtils.parse(body);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return json;
	}
}
