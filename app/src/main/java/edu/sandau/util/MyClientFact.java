package edu.sandau.util;

import javax.ws.rs.core.Context;

import edu.sandau.activity.UserLoginActivity;
import edu.sandau.service.UserService;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxrs.JAXRSContract;

import edu.sandau.service.AuthService;


public class MyClientFact {
	static MyClientFact inst=new MyClientFact();
	static String base_url = Data.loadServiceUrl();
	public static MyClientFact getInstance()
	{
		return inst;
	}



	public AuthService getAuthService() {
		AuthService client1 = Feign.builder()
				.contract(new JAXRSContract())
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.target(AuthService.class, base_url);

		return client1;
	}
	public UserService getUserService() {
		UserService client1 = Feign.builder()
				.contract(new JAXRSContract())
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder()).requestInterceptor(new RequestInterceptor(){
					@Override
					public void apply(RequestTemplate template) {
						template.header("AUTHORIZATION", UserLoginActivity.loginUser.getToken());
					}
				})
				.target(UserService.class, base_url);


		return client1;
	}



	public static void setBase_url(String base_url) {
		MyClientFact.base_url = base_url;
	}
}