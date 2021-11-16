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

import com.startaideia.vuttr.dto.TagDTO;
import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.resources.TagResource;
import com.startaideia.vuttr.services.TagService;
import com.startaideia.vuttr.services.ToolService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
public class TagResourceTest {

	@Autowired
	private TagResource tagResource;
	
	@MockBean
	private ToolService toolService;
	
	@MockBean
	private TagService tagService;
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.tagResource);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarTags() {
		List<TagDTO> tags = new ArrayList<TagDTO>();
		tags.add(new TagDTO(1L, "node"));
		tags.add(new TagDTO(2L, "json"));
		tags.add(new TagDTO(3L, "api"));
		
		when(this.tagService.findAll()).thenReturn(tags);
		
		RestAssuredMockMvc.given()
			.accept(ContentType.JSON)
		.when()
			.get("/tags")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarTags() {
		when(this.tagService.findById(1L)).thenReturn(new Tag(1L, "node"));
		
		RestAssuredMockMvc.given()
			.accept(ContentType.JSON)
		.when()
			.get("/tag/{id}", 1L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}
