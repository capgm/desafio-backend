package com.charles.desafiobackend.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.charles.desafiobackend.entity.Usuario;
import com.charles.desafiobackend.web.dto.usuario.UsuarioCreateDto;
import com.charles.desafiobackend.web.dto.usuario.UsuarioResponseDto;


public class UsuarioMapper {

	public static Usuario toUsuario(UsuarioCreateDto usuarioCreateDto) {
		return new ModelMapper().map(usuarioCreateDto, Usuario.class);
	}
	
	public static UsuarioResponseDto toDto(Usuario usuario) {
	
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(usuario, UsuarioResponseDto.class);
	}
	
	public static List<UsuarioResponseDto> toListDto(List<Usuario> users){
		
	 return	users.stream().map(user-> toDto(user)).collect(Collectors.toList());
	}
}
