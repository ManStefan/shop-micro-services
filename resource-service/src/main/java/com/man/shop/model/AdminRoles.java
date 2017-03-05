package com.man.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "admin_roles")
public class AdminRoles extends BasicEntity{
	private String authority;
	private Admin admin;
	
	@Column(name = "authority")
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_admin")		
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
