package lepartycious.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;

@Transactional(readOnly=true)
public interface OthersService {
	
	public SearchResponseDTOWrapper getOthers(SearchRequestDTO searchDTO);

	public List<String> loadOthersList(SearchRequestDTO searchRequestDTO);
	
	public DetailResponseDTO fetchOthersDetails(DataRequestDTO dataRequestDTO);
	
	public List<SearchResponseDTO> fetchRecomendations(Long cityId);
	
	public FilterResponseWrapperDTO loadFilters(Long cityId);

}
