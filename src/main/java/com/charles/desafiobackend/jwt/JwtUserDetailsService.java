package com.charles.desafiobackend.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.charles.desafiobackend.entity.Usuario;
import com.charles.desafiobackend.service.UsuarioService;


@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.buscarPorUsername(username);
		return new JwtUserDetails(usuario);
	}
	
	public JwtToken getTokenAuthenticates(String username) {
		return JwtUtils.createToken(username);
	}
}
