package com.startaideia.vuttr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.startaideia.vuttr.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

	@Query("SELECT t FROM Tag t WHERE t.name = ?1")
    public Tag fingByTagName(String t);
}
