package com.charles.desafiobackend.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService processoService;

	@GetMapping
	public Page<ProcessoResponseDto> listProcessos(Pageable pageable) {
		Page<Processo> processosPage = processoService.getAllProcessos(pageable);
		return processosPage.map(ProcessoMapper::toDto);
	}

	@GetMapping("/{id}")
	public ProcessoViewDto getProcesso(@PathVariable Long id) {
		return processoService.findById(id);
	}
	

	@PutMapping("/{id}/atualizar-visualizacao")
	public void updateLastView(@PathVariable Long id) {
		processoService.updateLastView(id);
	}

	@PutMapping()
	public ResponseEntity<Processo> updateProcesso(@RequestParam MultipartFile documento,
			@RequestParam(name = "processoCreateDto") @Valid String processoCreateDto) throws IOException {
		return processoService.updateProcesso(documento, processoCreateDto);
	}
	

	@PostMapping
	public ProcessoResponseDto createProcesso(@RequestParam MultipartFile documento,
			@RequestParam(name = "processoCreateDto") @Valid String processoCreateDto) throws IOException {
		return processoService.createProcesso(documento, processoCreateDto);
	}
		
	@DeleteMapping("/{id}")
	public void deleteProcesso(@PathVariable Long id) throws IOException {
		 processoService.deleteProcesso(id);
	}

}
