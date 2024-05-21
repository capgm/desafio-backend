package com.charles.desafiobackend.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charles.desafiobackend.entity.Usuario;
import com.charles.desafiobackend.jwt.JwtToken;
import com.charles.desafiobackend.jwt.JwtUserDetailsService;
import com.charles.desafiobackend.service.UsuarioService;
import com.charles.desafiobackend.web.dto.mapper.UsuarioMapper;
import com.charles.desafiobackend.web.dto.usuario.UsuarioCreateDto;
import com.charles.desafiobackend.web.dto.usuario.UsuarioResponseDto;
import com.charles.desafiobackend.web.dto.usuario.UsuarioResponseLoginDto;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/usuarios")
@RestController
public class UsuarioController {

	@Autowired
	private JwtUserDetailsService detailService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	public ResponseEntity<UsuarioResponseLoginDto> create(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto) {

		Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(usuarioCreateDto));

		//return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(usuarioCreateDto.getUsername(), usuarioCreateDto.getPassword());
		
		authenticationManager.authenticate(authenticationToken);
		
		JwtToken token = detailService.getTokenAuthenticates(usuarioCreateDto.getUsername());
		
		Usuario usuario = usuarioService.buscarPorUsername(usuarioCreateDto.getUsername());
		
		UsuarioResponseLoginDto responseLoginDto = new UsuarioResponseLoginDto(usuarioCreateDto.getUsername().toString(), token.getToken(), usuario.getNome());
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		String json = objectMapper.writeValueAsString(responseLoginDto);
		
		return ResponseEntity.ok(responseLoginDto);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDto>>findAll() {

		List<Usuario> users = usuarioService.findAll();

		return ResponseEntity.ok(UsuarioMapper.toListDto(users));
	}
}
