package lepartycious.services;

import java.util.List;
import java.util.Map;

import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.FilterRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.AddedDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Amenities;
import lepartycious.models.Attachment;
import lepartycious.models.Filter;
import lepartycious.models.Room;

import org.springframework.stereotype.Service;
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
	public Map<String, String> pushDataToDatabase(String query);
	
	public List<Attachment> getDefaultImageList();

	public Map<String, List<FilterResponseDTO>> fetchSubCategories();

	Long getVendorIdByName(String vendorType, String vendorName) throws Exception;
	
	void createEntity(String vendorType, String vendorName);

}
