package com.dristribuida.db;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement(name="list")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
	public class Author {
@XmlElement(name = "author")
		@Getter @Setter private long id;
		@Getter @Setter private String first_name;
		@Getter @Setter private String last_name;
		
		public Author(String first_name, String last_name) {
		this.first_name = first_name;
		this.last_name = last_name;
		
		}
		public Author() {
		}
}