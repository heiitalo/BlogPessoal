package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.UsuarioModel;
import org.generation.blogPessoal.model.dtos.CredenciaisDTO;
import org.generation.blogPessoal.model.dtos.UsuarioLoginDTO;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/usuarios")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsuarioController {

	private @Autowired UsuarioRepository repository;
	private @Autowired UsuarioService servicos;

	@GetMapping
	public ResponseEntity<List<UsuarioModel>> pegarTodes() {
		List<UsuarioModel> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/{id_usuario}")
	public ResponseEntity<UsuarioModel> pegarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		return repository.findById(idUsuario).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(400).build());

	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody UsuarioModel novoUsuario) {
		return servicos.cadastrarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(400).build());

	}
	
	@PostMapping("/logar")
	public ResponseEntity<CredenciaisDTO> credenciais(@Valid @RequestBody UsuarioLoginDTO usuarioParaAutenticar) {
		return servicos.pegarCredenciais(usuarioParaAutenticar);
	}
	
	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<UsuarioModel> deletar(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<UsuarioModel> objetoOptional = repository.findById(idUsuario);

		if (objetoOptional.isPresent()) {
			repository.deleteById(idUsuario);
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}
}