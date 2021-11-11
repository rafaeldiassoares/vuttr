package com.startaideia.vuttr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.startaideia.vuttr.dto.ToolDTO;
import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.entities.TagsToTools;
import com.startaideia.vuttr.entities.Tool;
import com.startaideia.vuttr.repositories.TagRepository;
import com.startaideia.vuttr.repositories.TagsToToolsRepository;
import com.startaideia.vuttr.repositories.ToolRepository;

@Service
public class ToolService {

	@Autowired
	private ToolRepository toolRepositoriy;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private TagsToToolsRepository tagsToToolsRepository;
	
	public List<ToolDTO> findAll() {
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
		
		return toolsDTO;		
	}
	
	public List<ToolDTO> findByTagName(String tag) {
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
		
		return toolsDTO;		
	}
	
	public ToolDTO save(ToolDTO toolDTO) {
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
		return toolDTO;
	}
	
	public void delete(@PathVariable Long id) {		
		toolRepositoriy.deleteTagsToToolsForIdTool(id);
		toolRepositoriy.deleteById(id);		
	}

}
