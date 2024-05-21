package com.charles.desafiobackend.web.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioCreateDto {

	@Email(message = "Formato do e-mail invalido!", regexp="^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
	@NotBlank
	private String username;
	
	@NotBlank
	@Size(min = 6, max = 6)
	private String password;
	
	@NotBlank
	private String nome;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UsuarioCreateDto(
			@Email(message = "Formato do e-mail invalido!", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$") @NotBlank String username,
			@NotBlank @Size(min = 6, max = 6) String password, @NotBlank String nome) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
	}
	
	public UsuarioCreateDto() {
		super();
	}	
}
