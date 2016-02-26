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
public interface DecoratorService {

	public SearchResponseDTOWrapper getDecorators(SearchRequestDTO searchDTO);

	public List<String> loadDecoratorList(SearchRequestDTO searchRequestDTO);
	
	public DetailResponseDTO fetchDecoratorDetails(DataRequestDTO dataRequestDTO);
	
	public FilterResponseWrapperDTO loadFilters(Long cityId);
	
	public List<SearchResponseDTO> fetchRecomendations(Long cityId);

}
