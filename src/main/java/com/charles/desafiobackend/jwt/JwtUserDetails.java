package com.charles.desafiobackend.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.charles.desafiobackend.entity.Usuario;



public class JwtUserDetails extends User {

	private com.charles.desafiobackend.entity.Usuario usuario;

	public JwtUserDetails(Usuario usuario) {
		super(usuario.getUsername(), usuario.getPassword(),
				AuthorityUtils.createAuthorityList(/*usuario.getRole().name()*/));
		this.usuario = usuario;
	}

	public Long getId() {
		return this.usuario.getId();
	}
}
