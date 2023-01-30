package com.dristribuida;

import com.dristribuida.db.Author;
import com.dristribuida.db.Book;
import com.dristribuida.conections.AuthorConection;
import com.dristribuida.conections.AuthorService;
import com.dristribuida.conections.BookService;
import com.dristribuida.conections.BookConection;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Servidor {
	public static void main(String[] args) {
		staticFiles.location("/public");
		port(8080);
		final BookService servicioBook = new BookConection();
		final AuthorService servicioAuthor = new AuthorConection ();
		
		// RUTA INICIO
		
		
		get("/inicio", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			String mensaje = "BIENVENIDO";
			model.put("mensajeInicio", mensaje);
			
			return new ThymeleafTemplateEngine().render(
							new ModelAndView(model, "index")
			);
		});
		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			
			return new ThymeleafTemplateEngine().render(
							new ModelAndView(model, "inicio")
			);
		});
		
		get("/books", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("books", servicioBook.findAll());
			
			return new ThymeleafTemplateEngine().render(
							new ModelAndView(model, "book_listar")
			);
		});
		
		get("/authors", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("authors", servicioAuthor.findAll());
			
			return new ThymeleafTemplateEngine().render(
							new ModelAndView(model, "author_listar")
			);
		});
		get("/authors/edit/:id", (request, response) -> {
			try {
			Integer id = Integer.parseInt(request.params("id"));
			Map<String, Object> model = new HashMap<>();
			var author = servicioAuthor.getOne(id);
			System.out.println(author);
			model.put("author", author);
			
			return new ThymeleafTemplateEngine().render(
							new ModelAndView(model, "author_edit")
			);
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		});
		
		get("/authors-delete/:id", (request, response) -> {
			Integer id = Integer.parseInt(request.params("id"));
			servicioAuthor.deleteOne(id);
			response.redirect("/authors");
			return "ok";
		});
		
		post("/authors/:id", (request, response) -> {
			Integer id = Integer.parseInt(request.params("id"));
			String[] cuerpo = request.body().split("&");
			String first_name = cuerpo[0].split("=")[1];
			String last_name = cuerpo[1].split("=")[1];
			Author newAuth = new Author(first_name, last_name);
			servicioAuthor.editOne(id, newAuth);
			response.redirect("/authors");
			
			return "ok";
			
		});
		post("/authors", (request, response) -> {
			String[] cuerpo = request.body().split("&");
			System.out.println(cuerpo);
			String first_name = cuerpo[0].split("=")[1];
			String last_name = cuerpo[1].split("=")[1];
			Author author = new Author(first_name, last_name);
			servicioAuthor.addOne(author);
			response.redirect("/authors");
			
			return "ok";
			
		});
		
		get("/authors-add", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			Author author = new Author();
			model.put("author", author);
			
			return new ThymeleafTemplateEngine().render(
							new ModelAndView(model, "author_new")
			);
		});
		
		get("/books-add", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			Book book = new Book();
			model.put("book", book);
			
			return new ThymeleafTemplateEngine().render(
							new ModelAndView(model, "book_new")
			);
		});
		
		post("/books", (request, response) -> {
			String[] cuerpo = request.body().split("&");
			System.out.println(cuerpo);
			String titulo = cuerpo[0].split("=")[1];
			String isbn = cuerpo[1].split("=")[1];
			Integer price = Integer.valueOf(cuerpo[2].split("=")[1]);
			Integer author = Integer.valueOf(cuerpo[3].split("=")[1]);
			Book book = new Book(author, titulo, isbn, price);
			servicioBook.addOne(book);
			response.redirect("/books");
			
			return "ok";
			
		});
		get("/books/edit/:id", (request, response) -> {
			try {
				Integer id = Integer.parseInt(request.params("id"));
				System.out.println(id);
				Map<String, Object> model = new HashMap<>();
				var book = servicioBook.getOne(id);
				System.out.println(book);
				model.put("book", book);
				
				return new ThymeleafTemplateEngine().render(
								new ModelAndView(model, "book_edit")
				);
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		});
		
		get("/books-delete/:id", (request, response) -> {
			Integer id = Integer.parseInt(request.params("id"));
			servicioBook.deleteOne(id);
			response.redirect("/books");
			return "ok";
		});
		
		
	}
}
