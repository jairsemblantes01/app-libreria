package com.dristribuida.conections;
import com.dristribuida.db.Book;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

@ApplicationScoped
public class BookConection implements BookService {


private BookService bookEndpoint;

public BookConection() {
	ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
	ResteasyWebTarget target = client.target("http://localhost:8081");
	bookEndpoint = target.proxy(BookService.class);
}

@Override
public List<Book> findAll() {
	return bookEndpoint.findAll();
}

@Override
public Book getOne(Integer id) {
	return bookEndpoint.getOne(id);
}

@Override
public Response addOne(Book book) {
	return bookEndpoint.addOne(book);
}

@Override
public Response editOne(Integer id, Book book) {
	return bookEndpoint.editOne(id, book);
}

@Override
public Response deleteOne(Integer id) {
	return bookEndpoint.deleteOne(id);
}
}
