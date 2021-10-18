package org.generation.blogPessoal.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class UsuarioLoginDTO {
	
	
	@ApiModelProperty(example = "email@email.com.br")
	@NotNull
	@Email(message = "O atributo Usuário deve ser um email válido!")
	private  String email;
	
	@NotBlank @Size(min = 5, max = 15, message = "Senha deve ter de 5 á 15 caracteres")
	private  String senha;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
