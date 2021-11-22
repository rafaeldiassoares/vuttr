package com.startaideia.vuttr.dto;

import com.startaideia.vuttr.entities.Tag;

/**
 * Classe Data transfer object de TAG
 * @author Rafael
 *
 */
public class TagDTO {

	private Long id;
	private String name;
	
	public TagDTO() {}
	
	/**
	 * Este método contrutor é utilizado para fazer o casting
	 * do tipo Tag para TagDTO 
	 * @param tag
	 */
	public TagDTO(Tag tag) {
		this.id = tag.getId();
		this.name = tag.getName();
	}
	
	public TagDTO(Long id, String name) {
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
}
