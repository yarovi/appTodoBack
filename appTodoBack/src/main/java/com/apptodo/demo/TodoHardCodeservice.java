package com.apptodo.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apptodo.demo.Todo.Todo;

@Service
public class TodoHardCodeservice {

	private static List<Todo> todos = new ArrayList<>();
	
	private static int idCounter=0;
	
	static {
		todos.add(new Todo(++idCounter,"in28minutes","Learn to Dance",new Date(),false));
		todos.add(new Todo(++idCounter,"in28minutes","Learn about Microservices",new Date(),false));
		todos.add(new Todo(++idCounter,"in28minutes","Learn about Angular",new Date(),false));
	}
	
	public List<Todo> findAll(){
		return todos;
	}
	
	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			todos.add(todo);
		}else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	public Todo deleteById(long id) {
		Todo todo=findBy(id);
		if(todo==null) return null;
		
		if(todos.remove(todo)) {
			return todo;
		}
		return null;
	}

	public Todo findBy(long id) {
		// TODO Auto-generated method stub
		for(Todo todo:todos) {
			if(todo.getId()==id) {
				return todo;
			}
		}
		return null;
	}
}
