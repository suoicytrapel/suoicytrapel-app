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
public interface RentalService {
	
	public SearchResponseDTOWrapper getRentals(SearchRequestDTO searchDTO);

	public List<String> loadRentalList(SearchRequestDTO searchRequestDTO);
	
	public DetailResponseDTO fetchRentalDetails(DataRequestDTO dataRequestDTO);
	
	public List<SearchResponseDTO> fetchRecomendations(Long cityId);
	
	public FilterResponseWrapperDTO loadFilters(Long cityId);

}
