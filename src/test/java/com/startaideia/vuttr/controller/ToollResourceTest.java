package com.startaideia.vuttr.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.startaideia.vuttr.dto.ToolDTO;
import com.startaideia.vuttr.resources.ToolResource;
import com.startaideia.vuttr.services.TagService;
import com.startaideia.vuttr.services.ToolService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
public class ToollResourceTest {

	@Autowired
	private ToolResource toolResource;
	
	@MockBean
	private ToolService toolService;
	
	@MockBean
	private TagService tagService;
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.toolResource);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarTools() {
		List<ToolDTO> listTool = new ArrayList<>();
		List<String> listTags = new ArrayList<>();
		listTags.add("rest");
		listTags.add("json");
		listTags.add("api");
		
		listTool.add(new ToolDTO(1L, "Title Test 1", "http://test.test.1", "data test 1", listTags));
		listTool.add(new ToolDTO(2L, "Title Test 2", "http://test.test.2", "data test 2", listTags));
		
		when(this.toolService.findByTagName("rest")).thenReturn(listTool);
		
		RestAssuredMockMvc.given()
			.accept(ContentType.JSON)
		.when()
			.get("/tools?tag={tag}", "rest")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarTools() {
		List<ToolDTO> listTools = new ArrayList<>();
		
		when(this.toolService.findByTagName("rest")).thenReturn(listTools);
		
		RestAssuredMockMvc.given()
			.accept(ContentType.JSON)
		.when()
			.get("/tools?tag={tag}", "rest")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}
