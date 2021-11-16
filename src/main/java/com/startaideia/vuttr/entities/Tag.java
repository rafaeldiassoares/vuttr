package com.startaideia.vuttr.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tag implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "tag")	
	private Set<TagsToTools> tagsToTools;

	public Tag() {		
	}
	
	public Tag(String name) {
		this.name = name;
	}
		
	public Tag(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<TagsToTools> getTagsToTools() {
		return tagsToTools;
	}

	public void setTagsToTools(Set<TagsToTools> tagsToTools) {
		this.tagsToTools = tagsToTools;
	}
}
