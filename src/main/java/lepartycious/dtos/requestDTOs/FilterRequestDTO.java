package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class FilterRequestDTO implements Serializable{
	
	private String searchType;
	private Long cityId;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

}
