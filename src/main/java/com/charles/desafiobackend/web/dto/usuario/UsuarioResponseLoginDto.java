package com.charles.desafiobackend.web.dto.usuario;


public class UsuarioResponseLoginDto {

	private String username;
	
	private String nome;
	
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UsuarioResponseLoginDto(String username, String token, String nome) {
		super();
		this.username = username;
		this.token = token;
		this.nome = nome;
	}

	public UsuarioResponseLoginDto() {
		super();
	}
}
