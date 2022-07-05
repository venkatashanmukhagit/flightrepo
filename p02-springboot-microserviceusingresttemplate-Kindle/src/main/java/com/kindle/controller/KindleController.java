package com.kindle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kindle.model.RequestModel;

@RestController
public class KindleController {
	
	@Value("${app.book.url}")
	private String bookMgmtUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping("/getbook")
	public String getAllBooks(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<RequestModel> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(bookMgmtUrl, HttpMethod.GET, entity, String.class).getBody();
	}
	
	@PostMapping(path = "/getbook")
	public String addBook(@Valid @RequestBody RequestModel book) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<RequestModel> entity = new HttpEntity<>(book,headers);
		return restTemplate.exchange(bookMgmtUrl, HttpMethod.POST, entity, String.class).getBody();
	}

	@GetMapping(path = "/getbook/{id}")
	public String getBookById(@PathVariable Long id){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<RequestModel> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(bookMgmtUrl+id, HttpMethod.GET, entity, String.class).getBody();

	}

	@PutMapping(path = "/getbook/{id}")
	public String updateBookById(@PathVariable Long id, @RequestBody RequestModel book){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<RequestModel> entity = new HttpEntity<>(book,headers);
		return restTemplate.exchange(bookMgmtUrl+id, HttpMethod.PUT, entity, String.class).getBody();

	}

	@DeleteMapping(path = "/getbook/{id}")
	public String deleteBookById(@PathVariable Long id){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<RequestModel> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(bookMgmtUrl+id, HttpMethod.DELETE, entity, String.class).getBody();

	}

}
