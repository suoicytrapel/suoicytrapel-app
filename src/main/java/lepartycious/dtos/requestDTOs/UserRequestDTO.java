package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class UserRequestDTO implements Serializable{
	
	private String username;
	private String password;
	private String email;
	private String userRole;
	private String name;
	private Boolean isAppUser = Boolean.FALSE;
	private String vendorType;
	private String entityName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsAppUser() {
		return isAppUser;
	}
	public void setIsAppUser(Boolean isAppUser) {
		this.isAppUser = isAppUser;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
