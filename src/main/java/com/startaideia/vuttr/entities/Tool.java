package com.startaideia.vuttr.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.startaideia.vuttr.dto.ToolDTO;

@Entity
public class Tool implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String link;
	private String description;

	@OneToMany(mappedBy = "tool")	
	private Set<TagsToTools> tagsToTools;
	
	public Tool() {
		
	}
	
	public Tool (ToolDTO toolDTO) {
		this.setId(toolDTO.getId());
		this.setTitle(toolDTO.getTitle());
		this.setLink(toolDTO.getLink());
		this.setDescription(toolDTO.getDescription());
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

	public Set<TagsToTools> getTagsToTools() {
		return tagsToTools;
	}

	public void setTagsToTools(Set<TagsToTools> tagsToTools) {
		this.tagsToTools = tagsToTools;
	}
}
