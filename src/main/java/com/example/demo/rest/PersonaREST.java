package com.example.demo.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;

@RestController
@RequestMapping("/person")
public class PersonaREST {

	@Autowired
	private PersonaService personaService;
	
	@PostMapping("/save")
	private ResponseEntity<Persona> save(@RequestBody Persona persona){
		Persona temporal = personaService.create(persona);
		try {
			return ResponseEntity.created(new URI("/persona/" + temporal.getId())).body(temporal);
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/list")
	private ResponseEntity<List<Persona>> getAll(){
		return ResponseEntity.ok(personaService.getAllPersonas());
	}
	
	@DeleteMapping("/delete")
	private ResponseEntity<Void> delete(@RequestBody Persona persona){
		personaService.delete(persona);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/one")
	private ResponseEntity<Persona> getOne(@RequestParam("id") Long id){
		return ResponseEntity.ok(personaService.getPersona(id).get());
	}
	
	@PutMapping("/edit")
	private ResponseEntity<Persona> getOne(@RequestBody Persona persona){
		return ResponseEntity.ok(personaService.editPersona(persona));
	}
	
}
