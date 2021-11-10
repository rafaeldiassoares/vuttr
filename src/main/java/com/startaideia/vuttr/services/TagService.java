package com.startaideia.vuttr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;
	
	public List<Tag> findAll() {
		return tagRepository.findAll();		
	}
	
	public Tag findById(Long id) {		
		return tagRepository.findById(id).get();		
	}
}
