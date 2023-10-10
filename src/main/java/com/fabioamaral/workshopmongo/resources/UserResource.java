package com.fabioamaral.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fabioamaral.workshopmongo.domain.User;
import com.fabioamaral.workshopmongo.dto.UserDTO;
import com.fabioamaral.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
 	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		//transforma a lista original em stream(coleção compativel com expressoes lambdas)
		//chama a função map pega cada objeto x q é um user da lista original é transformado em um UserDTO
		//por fim, voltar o stream para uma lista original com o .collect
		return ResponseEntity.ok().body(listDTO);
	}
}