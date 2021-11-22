package com.startaideia.vuttr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.entities.TagsToTools;

/**
 * Classe para manter o relacionamento n para n entre as entidades Tool e Tag
 * Todas as classes em entities são utilizadas para criação e persistência no
 * bando de dados utilizadno o JPA
 * @author Rafael
 *
 */
@Repository
public interface TagsToToolsRepository extends JpaRepository<TagsToTools, Long>{
		
}
