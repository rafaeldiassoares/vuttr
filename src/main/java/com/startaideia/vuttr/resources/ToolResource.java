package com.startaideia.vuttr.resources;

import java.util.ArrayList;
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
	public ResponseEntity<List<ToolDTO>> findAll() {
		List<Tool> tools = toolRepositoriy.findAll();
		List<String> tags;
		List<ToolDTO> toolsDTO = new ArrayList<>();
		ToolDTO toolDTO;
		
		for(Tool tool : tools) {
			tags = new ArrayList<>();
			for(TagsToTools tagToTool : tool.getTagsToTools()) {				
				tags.add(tagToTool.getTags().getName());				
			}
			
			toolDTO = new ToolDTO(tool);
			toolDTO.setTags(tags);
			toolsDTO.add(toolDTO);
		}		
		return ResponseEntity.ok(toolsDTO);		
	}

	@GetMapping("/tag/{tag}")
	public ResponseEntity<List<ToolDTO>> findByTagName(@PathVariable String tag) {
		List<Tool> tools = toolRepositoriy.findToolsByTagName(tag);
		List<String> tags = new ArrayList<>();
		List<ToolDTO> toolsDTO = new ArrayList<>();
		ToolDTO toolDTO;
		
		for(Tool tool : tools) {
			tags = new ArrayList<>();
			for(TagsToTools tagToTool : tool.getTagsToTools()) {
				tags.add(tagToTool.getTags().getName());				
			}
			
			toolDTO = new ToolDTO(tool);
			toolDTO.setTags(tags);
			toolsDTO.add(toolDTO);
		}		
		return ResponseEntity.ok(toolsDTO);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {		
		toolRepositoriy.deleteTagsToToolsForIdTool(id);
		toolRepositoriy.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})	
	public ResponseEntity<ToolDTO> save(@RequestBody ToolDTO toolDTO) {
		Tag tagExists;
		Tool tool;
		TagsToTools tagsToTools;
		List<String> tags = new ArrayList<String>();  
		
				
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
			tags.add(tagExists.getName());
		}		
		
		toolDTO = new ToolDTO(tool);
		toolDTO.setTags(tags);
		return new ResponseEntity(toolDTO, HttpStatus.CREATED);
	}
}
