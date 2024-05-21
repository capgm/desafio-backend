package com.charles.desafiobackend.web.dto.usuario;

public class UsuarioResponseDto {

	private Long id;
	
	private String username;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UsuarioResponseDto(Long id, String username, String nome) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
	}

	public UsuarioResponseDto() {
		super();
	}
}
