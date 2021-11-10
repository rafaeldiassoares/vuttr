package com.startaideia.vuttr.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.repositories.TagRepository;

@RestController
@RequestMapping(value = "/tags")
public class TagResource {
	
	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping
	public ResponseEntity<List<Tag>> findAll() {
		List<Tag> list = tagRepository.findAll();
		return ResponseEntity.ok().body(list);		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tag> findById(@PathVariable Long id) {		
		return ResponseEntity.ok().body(tagRepository.findById(id).get());		
	}	
}
