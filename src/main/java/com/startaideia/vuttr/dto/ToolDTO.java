package com.startaideia.vuttr.dto;

import java.util.List;

import com.startaideia.vuttr.entities.Tool;

public class ToolDTO {

	private Long id;
	private String title;
	private String link;
	private String description;
	private List<String> tags;
	
	public ToolDTO() {}
	
	public ToolDTO(Tool tool) {
		this.setId(tool.getId());
		this.setTitle(tool.getTitle());
		this.setLink(tool.getLink());
		this.setDescription(tool.getDescription());		
	}
	
	public ToolDTO(Long id, String title, String link, String description, List<String> tags) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
		this.description = description;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
