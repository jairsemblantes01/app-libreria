package com.dristribuida.conections;

import com.dristribuida.db.Author;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/authors")
public interface AuthorService {
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	List<Author> findAll();
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	Author getOne(@PathParam("id") Integer id);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response addOne(Author author);
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	Response editOne(@PathParam("id") Integer id, Author author);
	
	@DELETE
	@Path("/{id}")
	Response deleteOne(@PathParam("id") Integer id);
	
}
