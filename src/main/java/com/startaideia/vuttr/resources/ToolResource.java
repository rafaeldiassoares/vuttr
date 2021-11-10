package com.startaideia.vuttr.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startaideia.vuttr.dto.TagDTO;
import com.startaideia.vuttr.dto.ToolDTO;
import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.entities.TagsToTools;
import com.startaideia.vuttr.entities.Tool;
import com.startaideia.vuttr.repositories.TagRepository;
import com.startaideia.vuttr.repositories.TagsToToolsRepository;
import com.startaideia.vuttr.repositories.ToolRepository;

@RestController
@RequestMapping(value = "/tools")
public class ToolResource {
	
	@Autowired
	private ToolRepository toolRepositoriy;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private TagsToToolsRepository tagsToToolsRepository;
	
	@GetMapping
	public ResponseEntity<List<Tool>> findAll() {
		return null;
	}
	
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable Long id) {
		toolRepositoriy.deleteById(id);
	}
	
	@PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})	
	public void save(@RequestBody ToolDTO toolDTO) {
		Tag tagExists;
		Tool tool;
		TagsToTools tagsToTools;
				
		tool = toolRepositoriy.save(new Tool(toolDTO));
		
		for (String t : toolDTO.getTags()) {
			tagExists = tagRepository.fingByTagName(t);
			
			if(tagExists == null) {
				tagExists = new Tag();
				tagExists.setName(t);				
				tagExists = tagRepository.save(tagExists);
			}
			
			tagsToTools = new TagsToTools();
			tagsToTools.setTags(tagExists);
			tagsToTools.setTool(tool);
			
			tagsToToolsRepository.save(tagsToTools);
		}		
		
		//return ResponseEntity.ok(new Tool());
	}
}
