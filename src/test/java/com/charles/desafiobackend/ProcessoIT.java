package com.charles.desafiobackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.charles.desafiobackend.web.dto.processo.ProcessoCreateDto;
import com.charles.desafiobackend.web.dto.processo.ProcessoResponseDto;
import com.charles.desafiobackend.web.exception.ErrorMenssageAPI;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProcessoIT {
	
	@Autowired
	WebTestClient testClient;
	
	/*
	@Test
	public void createUsuario_ComUsernamePasswordValidos_RetornarUsuarioCriadoComStatus201() {
	
		ProcessoResponseDto responseBody =	testClient
			.post()
			.uri("/api/v1/usuarios")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new ProcessoCreateDto("tody@email.com", "123456"))
			.exchange()
			.expectStatus().isCreated()
			.expectBody(ProcessoResponseDto.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
	
	}
	
	@Test
	public void createUsuario_ComUsernameInvalido_RetornarErrorMessageStatus422() {
	
		ErrorMenssageAPI responseBody =	testClient
			.post()
			.uri("/api/v1/usuarios")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new ProcessoCreateDto("", "123456"))
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		
		 responseBody =	testClient
					.post()
					.uri("/api/v1/usuarios")
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(new ProcessoCreateDto("tody@", "123456"))
					.exchange()
					.expectStatus().isEqualTo(422)
					.expectBody(ErrorMenssageAPI.class)
					.returnResult().getResponseBody();
			
				org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
				org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
				
				
				 responseBody =	testClient
							.post()
							.uri("/api/v1/usuarios")
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue(new ProcessoCreateDto("tody@email", "123456"))
							.exchange()
							.expectStatus().isEqualTo(422)
							.expectBody(ErrorMenssageAPI.class)
							.returnResult().getResponseBody();
					
						org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
						org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
	
	}
	
	@Test
	public void createUsuario_ComPasswordInvalido_RetornarErroMessageComStatus422() {
	
		ErrorMenssageAPI responseBody =	testClient
			.post()
			.uri("/api/v1/usuarios")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new ProcessoCreateDto("tody@gmail.com", ""))
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		
		 responseBody =	testClient
					.post()
					.uri("/api/v1/usuarios")
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(new ProcessoCreateDto("tody@gmail.com", "123"))
					.exchange()
					.expectStatus().isEqualTo(422)
					.expectBody(ErrorMenssageAPI.class)
					.returnResult().getResponseBody();
			
				org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
				org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
				
				
				 responseBody =	testClient
							.post()
							.uri("/api/v1/usuarios")
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue(new ProcessoCreateDto("tody@gmail.com", "123456789"))
							.exchange()
							.expectStatus().isEqualTo(422)
							.expectBody(ErrorMenssageAPI.class)
							.returnResult().getResponseBody();
					
						org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
						org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
	
	}
	
	@Test
	public void createUsuario_ComUsernameRepetido_RetornarErroMessageComStatus409() {
	
	ErrorMenssageAPI responseBody =	testClient
			.post()
			.uri("/api/v1/usuarios")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new ProcessoCreateDto("ana@gmail.com", "123456"))
			.exchange()
			.expectStatus().isEqualTo(409)
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
	
	}
	
	@Test
	public void buscarUsuario_ComIdExistente_RetornarUsuarioCriadoComStatus200() {
	
	ProcessoResponseDto responseBody =	testClient
			.get()
			.uri("/api/v1/usuarios/100")
			.exchange()
			.expectStatus().isOk()
			.expectBody(ProcessoCreateDto.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(100);
		org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("ana@gmail.com");
		org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("ADMIN");
	
	}
	
	
	@Test
	public void buscarUsuario_ComIdInexistente_RetornarErrorMenssageComStatus404() {
	
		ErrorMenssageAPI responseBody =	testClient
			.get()
			.uri("/api/v1/usuarios/0")
			.exchange()
			.expectStatus().isNotFound()
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
	
	}
	
	
	@Test
	public void editarSenha_ComDadosValidos_RetornarStatus204() {
	
	testClient
			.patch()
			.uri("/api/v1/usuarios/100")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioSenhaDto("123456", "123456","123456"))
			.exchange()
			.expectStatus().isNoContent();
	
	}
	
	@Test
	public void editarSenha_ComIdInexistente_RetornarErroMenssageComStatus404() {
	
		ErrorMenssageAPI responseBody =	testClient
				.patch()
				.uri("/api/v1/usuarios/0")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("123456", "123456","123456"))
			.exchange()
			.expectStatus().isNotFound()
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
	
	}
	
	
	@Test
	public void editarSenha_ComCamposInvalidas_RetornarErroMenssageComStatus422() {
	
		ErrorMenssageAPI responseBody =	testClient
				.patch()
				.uri("/api/v1/usuarios/100")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("", "",""))
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody =	testClient
				.patch()
				.uri("/api/v1/usuarios/100")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("12345", "12345","12345"))
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody =	testClient
				.patch()
				.uri("/api/v1/usuarios/100")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("1234567", "1234567","1234567"))
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
	
	}
	
	@Test
	public void editarSenha_ComSenhaInvalidas_RetornarErroMenssageComStatus400() {
	
		ErrorMenssageAPI responseBody =	testClient
				.patch()
				.uri("/api/v1/usuarios/100")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("123456", "123456","000000"))
			.exchange()
			.expectStatus().isEqualTo(400)
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody =	testClient
				.patch()
				.uri("/api/v1/usuarios/100")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("000000", "123456","123456"))
			.exchange()
			.expectStatus().isEqualTo(400)
			.expectBody(ErrorMenssageAPI.class)
			.returnResult().getResponseBody();
	
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

	
	}
	*/
}
