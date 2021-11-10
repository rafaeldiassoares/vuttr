package com.startaideia.vuttr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.entities.TagsToTools;

@Repository
public interface TagsToToolsRepository extends JpaRepository<TagsToTools, Long>{
		
}
