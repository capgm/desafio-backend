package com.charles.desafiobackend.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.charles.desafiobackend.entity.Processo;
import com.charles.desafiobackend.repository.IProcessoRepository;
import com.charles.desafiobackend.web.dto.mapper.ProcessoMapper;
import com.charles.desafiobackend.web.dto.processo.ProcessoResponseDto;
import com.charles.desafiobackend.web.dto.processo.ProcessoViewDto;

@Service
public class ProcessoService {

	@Autowired
	private IProcessoRepository processoRepository;

	@Transactional
	public Page<Processo> getAllProcessos(Pageable pageable) {
		return processoRepository.findAll(pageable);
	}

	@Transactional
	public void deleteProcesso(Long id) {
		processoRepository.deleteById(id);
	}

	@Transactional
	public ProcessoResponseDto createProcesso(MultipartFile documento, String processoCreateDto) throws IOException {
		Processo processo = ProcessoMapper.toProcesso(processoCreateDto);
		processo.setDocumentoPdf(documento.getBytes());
		processoRepository.save(processo);

		return ProcessoMapper.toDto(processo);

	}

	@Transactional
	public ProcessoViewDto findById(Long id) {
		return ProcessoMapper.toViewDto(processoRepository.findById(id).get());
	}

	@Transactional
	public ProcessoViewDto updateLastView(Long id) {
		Processo processo = processoRepository.findById(id).get();
		processo.setDataVisualizacao(LocalDateTime.now());
		processoRepository.save(processo);
		return ProcessoMapper.toViewDto(processo);
	}

	@Transactional
	public ResponseEntity<Processo> updateProcesso(MultipartFile documento, String processo) throws IOException {

		Processo processoConvertido = ProcessoMapper.toProcesso(processo);
		Processo processoConsulta = processoRepository.findById(processoConvertido.getId()).get();
		processoConvertido.setDocumentoPdf(documento.getBytes());
		processoConsulta.setMunicipio(processoConvertido.getMunicipio());
		processoConsulta.setUf(processoConvertido.getUf());
		processoConsulta.setNpu(processoConvertido.getNpu());
		processoConsulta.setDocumentoPdf(documento.getBytes());
		processoRepository.save(processoConsulta);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(processoConsulta);
	}

}
