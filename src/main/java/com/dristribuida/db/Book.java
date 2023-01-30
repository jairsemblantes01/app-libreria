package com.dristribuida.db;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.json.bind.annotation.JsonbAnnotation;


@XmlRootElement(name="list")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
	public class Book {
@XmlElement(name = "book")

	@Getter @Setter private String id;
	@Getter @Setter private int author;
	@Getter @Setter private String title;
	@Getter @Setter private String isbn;
@Getter @Setter private int price;


public Book(int author, String title, String isbn, int price) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.price = price;
		
	}
	public Book() {
	}
}