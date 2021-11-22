package com.startaideia.vuttr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.startaideia.vuttr.dto.ToolDTO;
import com.startaideia.vuttr.entities.Tag;
import com.startaideia.vuttr.entities.TagsToTools;
import com.startaideia.vuttr.entities.Tool;
import com.startaideia.vuttr.repositories.TagRepository;
import com.startaideia.vuttr.repositories.TagsToToolsRepository;
import com.startaideia.vuttr.repositories.ToolRepository;

@Service
public class ToolService {

	@Autowired
	private ToolRepository toolRepositoriy;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private TagsToToolsRepository tagsToToolsRepository;
	/**
	 * Busca todas as Tools cadastradas
	 * @return lista de ToolDTO
	 */
	public List<ToolDTO> findAll() {
		List<Tool> tools = toolRepositoriy.findAll();
		List<String> tags;
		List<ToolDTO> toolsDTO = new ArrayList<>();
		ToolDTO toolDTO;
		
		/**
		 * Organizar todas as tags de uma Tool que são retornadas
		 * em formato TagsToTool em uma lista de tags no DTO de Tool
		 */
		for(Tool tool : tools) {
			tags = new ArrayList<>();
			for(TagsToTools tagToTool : tool.getTagsToTools()) {				
				tags.add(tagToTool.getTags().getName());				
			}
			
			toolDTO = new ToolDTO(tool);
			toolDTO.setTags(tags);
			toolsDTO.add(toolDTO);
		}
		
		return toolsDTO;		
	}
	
	/**
	 * Buscar por TagName
	 * @param tag: string com o nome da Tag
	 * @return Lista de TagDTO
	 */
	public List<ToolDTO> findByTagName(String tag) {
		List<Tool> tools = toolRepositoriy.findToolsByTagName(tag);
		List<String> tags = new ArrayList<>();
		List<ToolDTO> toolsDTO = new ArrayList<>();
		ToolDTO toolDTO;
		
		/**
		 * Organizar todas as tags de uma Tool que são retornadas
		 * em formato TagsToTool em uma lista de tags no DTO de Tool
		 */
		for(Tool tool : tools) {
			tags = new ArrayList<>();
			for(TagsToTools tagToTool : tool.getTagsToTools()) {
				tags.add(tagToTool.getTags().getName());				
			}
			
			toolDTO = new ToolDTO(tool);
			toolDTO.setTags(tags);
			toolsDTO.add(toolDTO);
		}		
		
		return toolsDTO;		
	}
	
	/**
	 * Salvar uma nova Tool
	 * @param toolDTO: Objeto contendo todos os parâmetros de uma Tool
	 * @return objeto ToolDTO inserido
	 */
	public ToolDTO save(ToolDTO toolDTO) {
		Tag tagExists;
		Tool tool;
		TagsToTools tagsToTools;
		List<String> tags = new ArrayList<String>();  
		
		/**
		 * Criando um novo registro de Tool
		 */
		tool = toolRepositoriy.save(new Tool(toolDTO));
		
		/**
		 * Antes de salvar as tags passadas no objeto Tool
		 * é feita uma busca na entidade Tags para verificar se
		 * alguma tag passada já existe no banco
		 */
		for (String t : toolDTO.getTags()) {
			tagExists = tagRepository.fingByTagName(t);
			
			/**
			 * Caso não exista é criado um novo registro de tag
			 * e retornado para o objeto tagExists
			 */
			if(tagExists == null) {
				tagExists = new Tag();
				tagExists.setName(t);				
				tagExists = tagRepository.save(tagExists);				
			}
			
			/**
			 * Logo após é inserido o relacionamento entre
			 * Tool e Tag evitando assim a redundância de 
			 * Tags
			 */
			tagsToTools = new TagsToTools();
			tagsToTools.setTags(tagExists);
			tagsToTools.setTool(tool);
			
			/**
			 * Salvando a relação de Tool e suas tags
			 */
			tagsToToolsRepository.save(tagsToTools);
			tags.add(tagExists.getName());
		}		
		
		toolDTO = new ToolDTO(tool);
		toolDTO.setTags(tags);
		return toolDTO;
	}
	
	/**
	 * Exclusão do registro da tabela TagsToTools antes de
	 * excluir a Tool em questão
	 * @param id
	 */
	public void delete(@PathVariable Long id) {		
		toolRepositoriy.deleteTagsToToolsForIdTool(id);
		toolRepositoriy.deleteById(id);		
	}

}
