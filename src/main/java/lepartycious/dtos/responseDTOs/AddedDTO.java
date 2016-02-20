package lepartycious.dtos.responseDTOs;

import java.io.Serializable;

public class AddedDTO implements Serializable{
	
	private String name;
	private String locality;
	private String city;
	
	public AddedDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddedDTO(String name, String locality, String city) {
		super();
		this.name = name;
		this.locality = locality;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
