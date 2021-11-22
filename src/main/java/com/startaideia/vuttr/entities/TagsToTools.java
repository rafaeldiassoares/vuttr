package com.startaideia.vuttr.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Esta classe foi necessária para construir o relacionamento "n to n" entre
 * as entidades Tool e Tag, o uso da mesma possibilita o reuso de registros de
 * tags para Tools diferentes, eliminando assim possível redundância de dados 
 * na tabela Tag
 * @author Rafael
 *
 */
@Entity
public class TagsToTools implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "tool_id")	
	private Tool tool;
	
	@ManyToOne
	@JoinColumn(name = "tag_id")	
	private Tag tag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public Tag getTags() {
		return tag;
	}

	public void setTags(Tag tag) {
		this.tag = tag;
	}
}
