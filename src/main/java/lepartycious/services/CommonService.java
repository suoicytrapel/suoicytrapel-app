package lepartycious.services;

import java.util.List;
import java.util.Map;

import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.FilterRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.AddedDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Attachment;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
public interface CommonService {
	
	public SearchResponseDTOWrapper getEntities(SearchRequestDTO searchDTO);

	public List<String> loadList(SearchRequestDTO searchRequestDTO);
	
	public DetailResponseDTO fetchDetails(DataRequestDTO dataRequestDTO);
	
	public FilterResponseWrapperDTO loadFilters(String searchType, Long cityId);
	
	public List<SearchResponseDTO> fetchRecomendations(String searchType, Long cityId);
	
	public Map<String, List<AddedDTO>> getRecentAdditions(Long cityId);
	
	@Transactional(readOnly=false)
	public boolean pushDataToDatabase(String query);
	
	public List<Attachment> getDefaultImageList();

}
