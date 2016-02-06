package lepartycious.dtos.responseDTOs;

import java.util.List;
import java.util.Map;

public class SearchResponseDTOWrapper {
	
	private List<SearchResponseDTO> searchResponseDTOList;
	private Long resultCount;
	private List<String> services;
	private List<String> amenities;
	
	public List<SearchResponseDTO> getSearchResponseDTOList() {
		return searchResponseDTOList;
	}
	public void setSearchResponseDTOList(
			List<SearchResponseDTO> searchResponseDTOList) {
		this.searchResponseDTOList = searchResponseDTOList;
	}
	public Long getResultCount() {
		return resultCount;
	}
	public void setResultCount(Long resultCount) {
		this.resultCount = resultCount;
	}
	public List<String> getServices() {
		return services;
	}
	public void setServices(List<String> services) {
		this.services = services;
	}
	public List<String> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}
}
