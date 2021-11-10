package com.startaideia.vuttr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.startaideia.vuttr.entities.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long>{

}
