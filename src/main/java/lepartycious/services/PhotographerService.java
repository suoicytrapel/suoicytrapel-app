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
public interface PhotographerService {
	
	public SearchResponseDTOWrapper getPhotographers(SearchRequestDTO searchDTO);

	public List<String> loadPhotographerList(SearchRequestDTO searchRequestDTO);
	
	public DetailResponseDTO fetchPhotographerDetails(DataRequestDTO dataRequestDTO);
	
	public FilterResponseWrapperDTO loadFilters(Long cityId);
	
	public List<SearchResponseDTO> fetchRecomendations(Long cityId);

}
