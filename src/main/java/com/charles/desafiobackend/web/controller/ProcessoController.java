package com.charles.desafiobackend.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.charles.desafiobackend.entity.Processo;
import com.charles.desafiobackend.service.ProcessoService;
import com.charles.desafiobackend.web.dto.mapper.ProcessoMapper;
import com.charles.desafiobackend.web.dto.processo.ProcessoResponseDto;
import com.charles.desafiobackend.web.dto.processo.ProcessoViewDto;
import com.charles.desafiobackend.web.exception.ErrorMenssageAPI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Processos", description = "Contem as operações referentes ao processo")
@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService processoService;

	@Operation(summary = "Criar um novo processo", description = "Recurso para um novo processo", responses = {
			@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoResponseDto.class))),
			@ApiResponse(responseCode = "409", description = "O processo já está cadastrado no sistema", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMenssageAPI.class))),
			@ApiResponse(responseCode = "422", description = "Campos inválidos ou mal formatados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMenssageAPI.class))) })
	@PostMapping
	public ResponseEntity<ProcessoResponseDto> createProcesso(@RequestParam MultipartFile documento,
			@RequestParam(name = "processoCreateDto") @Valid String processoCreateDto) throws IOException {
		ProcessoResponseDto responseDto = processoService.createProcesso(documento, processoCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@Operation(summary = "Recuperar um processo pelo id", description = "Recuperar um processo pelo id", responses = {
			@ApiResponse(responseCode = "200", description = "Processo recuperado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoViewDto.class))),
			@ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMenssageAPI.class))) })
	@GetMapping("/{id}")
	public ResponseEntity<ProcessoViewDto> getProcesso(@PathVariable Long id) {
		ProcessoViewDto viewDto = processoService.findById(id);
		return ResponseEntity.ok().body(viewDto);
	}

	@Operation(summary = "Atualizar processo", description = "Atualizar senha", responses = {
			@ApiResponse(responseCode = "204", description = "Dados do processo atualizadas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMenssageAPI.class))),
			@ApiResponse(responseCode = "422", description = "Campos inválidos ou mal formatados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMenssageAPI.class))) })
	@PutMapping()
	public ResponseEntity<Void> updateProcesso(@RequestParam MultipartFile documento,
			@RequestParam(name = "processoCreateDto") @Valid String processoCreateDto) throws IOException {
		processoService.updateProcesso(documento, processoCreateDto);

		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Atualizar a data hora ultima visualização do Processo", description = "Atualizar a data hora ultima visualização do Processo", responses = {
			@ApiResponse(responseCode = "204", description = "Data hora ultima visualização alterada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMenssageAPI.class))), })
	@PutMapping("/{id}/atualizar-visualizacao")
	public ResponseEntity<Void> updateLastView(@PathVariable Long id) {

		processoService.updateLastView(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Deletar processo", description = "deletar processo", responses = {
			@ApiResponse(responseCode = "200", description = "Processo deletado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoViewDto.class))),
			@ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMenssageAPI.class))) })
	@DeleteMapping("/{id}")
	public void deleteProcesso(@PathVariable Long id) throws IOException {
		processoService.deleteProcesso(id);
	}

	@Operation(summary = "Consultar processos", description = "consultar processo")
	@GetMapping
	public Page<ProcessoResponseDto> listProcessos(Pageable pageable) {
		Page<Processo> processosPage = processoService.getAllProcessos(pageable);
		return processosPage.map(ProcessoMapper::toDto);
	}

}
