package lepartycious.dtos.responseDTOs;

import java.io.Serializable;

public class FilterResponseDTO implements Serializable{
	
	private String name;
	private Long id;
	private String type;
	
	public FilterResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterResponseDTO(String name, String type, Long id) {
		super();
		this.name = name;
		this.type = type;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
