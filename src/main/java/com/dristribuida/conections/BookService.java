package com.dristribuida.conections;

import com.dristribuida.db.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes(MediaType.APPLICATION_JSON)
public interface BookService {
@GET

List<Book> findAll();

@GET
@Path("/{id}")

Book getOne(@PathParam("id") Integer id);

@POST
Response addOne(Book book);

@PUT
@Path("/{id}")
Response editOne(@PathParam("id") Integer id, Book book);

@DELETE
@Path("/{id}")
Response deleteOne(@PathParam("id") Integer id);

}
