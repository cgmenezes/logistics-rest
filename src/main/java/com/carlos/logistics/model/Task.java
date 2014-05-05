package com.carlos.logistics.model;

// JSON: uncomment to include json support (note json is not part of the JAX-RS standard)
//import org.codehaus.jackson.annotate.JsonIgnore;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.io.StringReader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User's task entity which is marked up with JPA annotations and JAXB for
 * serializing XML (and JSON if required)
 * 
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement(name = "task")
public class Task implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String title;

	public Task() {
	}

	public Task(String title) {
		super();
		this.title = title;
	}

	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Task other = (Task) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	public static Task stringToTask(String content) {
		return JAXB.unmarshal(new StringReader(content), Task.class);
	}
}
