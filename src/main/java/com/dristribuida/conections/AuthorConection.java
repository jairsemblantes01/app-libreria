package com.dristribuida.conections;
import com.dristribuida.db.Author;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

@ApplicationScoped
public class AuthorConection implements AuthorService {


private AuthorService authorConection;

public AuthorConection() {
	
	Client cliente = ClientBuilder.newBuilder().build();
	ResteasyWebTarget target = (ResteasyWebTarget) cliente.target("http://localhost:9000");
	authorConection = target.proxy(AuthorService.class);
}

@Override
public List<Author> findAll() {
	return authorConection.findAll();
}

@Override
public Author getOne(Integer id) {
	return authorConection.getOne(id);
}

@Override
public Response addOne(Author author) {
	return authorConection.addOne(author);
}

@Override
public Response editOne(Integer id, Author author) {
	return authorConection.editOne(id, author);
}

@Override
public Response deleteOne(Integer id) {
	return authorConection.deleteOne(id);
}
}
