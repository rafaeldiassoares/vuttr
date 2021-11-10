package com.startaideia.vuttr.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startaideia.vuttr.dto.ToolDTO;
import com.startaideia.vuttr.services.ToolService;

@RestController
@RequestMapping(value = "/tools")
public class ToolResource {
	
	@Autowired
	private ToolService toolService;
	
	@GetMapping
	public ResponseEntity<List<ToolDTO>> findAll() {		
		return ResponseEntity.ok(toolService.findAll());		
	}

	@GetMapping("/tag/{tag}")
	public ResponseEntity<List<ToolDTO>> findByTagName(@PathVariable String tag) {	
		return ResponseEntity.ok(toolService.findByTagName(tag));		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {		
		toolService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})	
	public ResponseEntity<ToolDTO> save(@RequestBody ToolDTO toolDTO) {		
		return new ResponseEntity<ToolDTO>(toolService.save(toolDTO), HttpStatus.CREATED);
	}
}
