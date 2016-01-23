package lepartycious.dtos.responseDTOs;

import java.util.List;

public class SearchResponseDTOWrapper {
	
	private List<SearchResponseDTO> searchResponseDTOList;
	private Long resultCount;
	
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
}
