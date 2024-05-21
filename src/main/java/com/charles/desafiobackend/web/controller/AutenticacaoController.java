package com.charles.desafiobackend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charles.desafiobackend.entity.Usuario;
import com.charles.desafiobackend.jwt.JwtToken;
import com.charles.desafiobackend.jwt.JwtUserDetailsService;
import com.charles.desafiobackend.service.UsuarioService;
import com.charles.desafiobackend.web.dto.usuario.UsuarioLoginDto;
import com.charles.desafiobackend.web.dto.usuario.UsuarioResponseLoginDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {
	@Autowired
	private JwtUserDetailsService detailService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth")
	public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto,
			HttpServletRequest request) throws JsonProcessingException {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(usuarioLoginDto.getUsername(), usuarioLoginDto.getPassword());
			
			authenticationManager.authenticate(authenticationToken);
			
			JwtToken token = detailService.getTokenAuthenticates(usuarioLoginDto.getUsername());
			
			Usuario usuario = usuarioService.buscarPorUsername(usuarioLoginDto.getUsername());
			
			UsuarioResponseLoginDto responseLoginDto = new UsuarioResponseLoginDto(usuarioLoginDto.getUsername().toString(), token.getToken(), usuario.getNome());

			
			return ResponseEntity.ok(responseLoginDto);
			
		}catch (AuthenticationException e) {
			return ResponseEntity
					.badRequest()
					.body(e.getMessage() /*"Deu erro"/*new ErrorMenssageAPI(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas")*/);
		}
		

	}
}
