package com.startaideia.vuttr.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startaideia.vuttr.dto.TagDTO;
import com.startaideia.vuttr.services.TagService;

@RestController
@RequestMapping(value = "/tags")
public class TagResource {
	
	@Autowired
	private TagService tagService;
	
	/**
	 * Buscar todas as Tags
	 * @return Lista com todas as tags cadastradas
	 */
	@GetMapping
	public ResponseEntity<List<TagDTO>> findAll() {		
		return ResponseEntity.ok().body(tagService.findAll());
	}
	
	/**
	 * Buscar uma Tag por ID
	 * @param id
	 * @return Objeto Tag
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<TagDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(new TagDTO(tagService.findById(id)));
	}	
}
