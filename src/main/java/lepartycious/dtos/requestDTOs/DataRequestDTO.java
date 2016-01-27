package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class DataRequestDTO implements Serializable{
	
	private String name;
	private Long cityId;
	private String searchType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
}
