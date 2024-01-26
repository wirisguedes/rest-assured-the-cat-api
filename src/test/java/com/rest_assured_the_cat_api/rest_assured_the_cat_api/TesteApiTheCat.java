package com.rest_assured_the_cat_api.rest_assured_the_cat_api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TesteApiTheCat {

	
	@Test
	public void cadastro() {
		// condição
		given().
		contentType(ContentType.JSON).
		body("{\"email\": \"wirisntt@gmail.com\", \"appDescription\": \"test api\"}").
		//ação
		when().
		post("https://api.thecatapi.com/v1/user/passwordlesssignup").
		
		//verificação
		then().
		statusCode(200).
		log().
		all();
				
	}
	
	@Test
	public void favoritar() {
		// condição
		given().
		contentType(ContentType.JSON).
		body("{\"image_id\": \"MTUwNzgxNQ\"}").
		headers("x-api-key","live_wnYDgp9Y4BNPP6wCXqkSxrXZkgUjNTFOjjTCNPjIySAYv4tYCCIRuELKcm0T1tUc").
		//ação
		when().
		post("https://api.thecatapi.com/v1/favourites").
		
		//verificação
		then().
		statusCode(200).
		body("message", is("SUCCESS")).
		log().
		all();		
		
	}
	
	@Test
	public void pegarFavorito() {
		
		Response response =
		// condição
		given().
		contentType(ContentType.JSON).		
		headers("x-api-key","live_wnYDgp9Y4BNPP6wCXqkSxrXZkgUjNTFOjjTCNPjIySAYv4tYCCIRuELKcm0T1tUc").
		//ação
		when().
		get("https://api.thecatapi.com/v1/favourites");
		
		//verificação
		response.then().		
		log().
		all();		
		
		String id = response.jsonPath().getString("id[5]");
		
		System.out.println("ID =>" + id);
		
	}
	
	@Test
	public void deletarFavorito() {
		// condição
		given().		
		headers("x-api-key","live_wnYDgp9Y4BNPP6wCXqkSxrXZkgUjNTFOjjTCNPjIySAYv4tYCCIRuELKcm0T1tUc").
		pathParam("favouriteId", "232436378").
		//ação
		when().
		delete("https://api.thecatapi.com/v1/favourites/{favouriteId}").
		
		//verificação
		then().
		statusCode(200).
		//body("message", is("SUCCESS")).
		log().
		all();		
		
	}
}
