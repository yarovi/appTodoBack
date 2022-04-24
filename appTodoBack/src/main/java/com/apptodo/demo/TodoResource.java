package com.apptodo.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apptodo.demo.Todo.Todo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class TodoResource {

	@Autowired
	private TodoHardCodeservice todoService;
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		return todoService.findAll();
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username,
			@PathVariable long id){
		return todoService.findBy(id);
	}
	
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String  username,
			@PathVariable long id){
		log.info("eliminando el id {}",id);
		Todo todo = todoService.deleteById(id);
		if(todo!=null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> update(
			@PathVariable String  username,
			@PathVariable long id,
			@RequestBody Todo todo){
		Todo todoupdate = todoService.save(todo);

		
		return new ResponseEntity<>(todo,HttpStatus.OK);

	}
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable String  username,
			@PathVariable long id,
			@RequestBody Todo todo){
		Todo createTodo = todoService.save(todo);
		
		URI uri=ServletUriComponentsBuilder.fromCurrentContextPath()
		.path("/{id}")
		.buildAndExpand(createTodo.getId()).toUri();

		
		return ResponseEntity.created(uri).build();
	}
}
