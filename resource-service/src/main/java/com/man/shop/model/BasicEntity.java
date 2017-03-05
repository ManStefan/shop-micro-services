package com.man.shop.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BasicEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5155317036414204969L;
	private Long id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BasicEntity that = (BasicEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
