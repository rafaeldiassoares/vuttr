package com.startaideia.vuttr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.startaideia.vuttr.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

	/**
	 * Query personalizada para buscar tags por nome
	 * @param t: nome da tag a ser buscada
	 * @return Tag buscada
	 */
	@Query("SELECT t FROM Tag t WHERE t.name = ?1")
    public Tag fingByTagName(String t);
}
