package com.startaideia.vuttr.resources;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.startaideia.vuttr.dto.ToolDTO;
import com.startaideia.vuttr.services.ToolService;

@RestController
@RequestMapping(value = "/tools")
public class ToolResource {
	
	@Autowired
	private ToolService toolService;

	/**
	 * Buscar todas as Tool's ou as Tool's que contenham uma tag específica passada por QueryParam
	 * @param tag: nome da tag a ser buscada
	 * @return Lista de tools que contenham a tag passada por QueryParam
	 */
	@GetMapping
	public ResponseEntity<List<ToolDTO>> find(@RequestParam(value = "tag", required = false) String tag) {
		/**
		 * Foi necessário fazer esta verificação se foi ou não passado uma query param no formato /tools?tag="<tag>"
		 * para utilizar o mesmo endpoint "/tools", uma vez que não é possível criar mais de um "@GetMapping" com 
		 * o mesmo nome. 
		 */
		if(tag != null) {
			/**
			 * Caso seja passado o parâmetro tag, é utilizado o método findByTagName 
			 * passando o parâmetro recebido que retornará uma lista de Tools que 
			 * contenham a tag desejada.
			 */
			List<ToolDTO> listTools = new ArrayList<>();
			listTools = toolService.findByTagName(tag);
			
			/**
			 * Caso não tenha encontrado nenhuma Tool contento a tag recebida o endpoint
			 * retornará o status 404 NOT_FOUND, caso contrário retorna Ok com a lista de
			 * Tools que contenham a tag desejada.
			 */
			if(listTools.size() > 0)
				return ResponseEntity.ok(listTools);
			else
				return new ResponseEntity<List<ToolDTO>>(listTools, HttpStatus.NOT_FOUND);
		} else {
			/**
			 * Caso não seja passada nenhuma tag via QueryParam, retorna todas as Tools cadastradas.
			 */
			return ResponseEntity.ok(toolService.findAll());
		}
	}
	
	/**
	 * Deleta a Tool com o ID passado por parâmetro
	 * @param id: id da tool em questão
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {		
		toolService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Salvar uma nova Tool
	 * @param toolDTO: Objeto toolDTO contendo os atributos a serem gravados
	 * @return Status 201
	 */
	@PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})	
	public ResponseEntity<ToolDTO> save(@RequestBody ToolDTO toolDTO) {		
		return new ResponseEntity<ToolDTO>(toolService.save(toolDTO), HttpStatus.CREATED);
	}
}
