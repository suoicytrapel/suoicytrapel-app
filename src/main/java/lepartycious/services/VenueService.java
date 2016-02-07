package lepartycious.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.FilterRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;

@Transactional(readOnly=true)
public interface VenueService {

	public SearchResponseDTOWrapper getVenues(SearchRequestDTO searchDTO);

	public List<String> loadVenueList(SearchRequestDTO searchRequestDTO);
	
	public DetailResponseDTO fetchVenueDetails(DataRequestDTO dataRequestDTO);
	
	public FilterResponseWrapperDTO loadFilters(Long cityId);

}
