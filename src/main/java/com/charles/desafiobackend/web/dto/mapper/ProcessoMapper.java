package com.charles.desafiobackend.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.charles.desafiobackend.entity.Processo;
import com.charles.desafiobackend.web.dto.processo.ProcessoCreateDto;
import com.charles.desafiobackend.web.dto.processo.ProcessoResponseDto;
import com.charles.desafiobackend.web.dto.processo.ProcessoViewDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessoMapper {

	public static Processo toProcesso(ProcessoCreateDto processoCreateDto) {
		return new ModelMapper().map(processoCreateDto, Processo.class);
	}
	
	public static Processo toProcesso(String processoCreateDto) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(processoCreateDto, Processo.class);
		
		//return new ModelMapper().map(processoCreateDto, Processo.class);
	}
	
	public static ProcessoResponseDto toDto(Processo processo) {
		return new ModelMapper().map(processo, ProcessoResponseDto.class);
	}
	
	public static ProcessoViewDto toViewDto(Processo processo) {
		return new ModelMapper().map(processo, ProcessoViewDto.class);
	}
	
	public static List<ProcessoResponseDto> toListDto(List<Processo> processos){
		
		 return	processos.stream().map(processo-> toDto(processo)).collect(Collectors.toList());
	}
}
