package com.startaideia.vuttr.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startaideia.vuttr.dto.TagDTO;
import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;
	
	public List<TagDTO> findAll() {
		List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
		
		for(Tag tag : tagRepository.findAll()) {
			tagsDTO.add(new TagDTO(tag));
		}
		
		return tagsDTO;		
	}
	
	public Tag findById(Long id) {		
		return tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found " + id));		
	}
}
