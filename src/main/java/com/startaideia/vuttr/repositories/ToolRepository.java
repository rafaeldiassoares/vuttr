package com.startaideia.vuttr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.startaideia.vuttr.entities.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long>{

	/**
	 * Query personalizada para que seja possível excluir uma Tool
	 * é necessário que seja excluído antes o relacionamento para
	 * depois ser possível excluir a Tool.
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM TagsToTools t WHERE t.tool.id = ?1")
    public void deleteTagsToToolsForIdTool(Long id);
	
	/**
	 * Query personalizada para buscar Tools que contenha uma Tag
	 * passada por parâmetro
	 * @param tagName: Tag desejada
	 * @return Lista de tools que contenham a tag em questão
	 */
	@Query("SELECT tl FROM TagsToTools tt "
			+ "	INNER JOIN Tool tl ON tt.tool.id = tl.id "
			+ "	INNER JOIN Tag tg ON tt.tag.id = tg.id "
			+ " WHERE "
			+ "	tg.name = ?1")
	public List<Tool> findToolsByTagName(String tagName);
	
}
