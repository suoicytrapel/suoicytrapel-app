package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lepartycious.daos.CatererDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Address;
import lepartycious.models.Caterer;
import lepartycious.services.CatererService;

@Service
public class CatererServiceImpl implements CatererService {
	
	@Autowired
	private CatererDAO catererDAO;

	@Override
	public SearchResponseDTOWrapper getCaterers(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalCatererCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalCatererCount = catererDAO.getCatererCount(searchDTO.getCityId(), searchDTO.getSearchString());
			if(totalCatererCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populateCatererResults(searchResponseDTOList, searchDTO);
		searchResponseDTOWrapper.setResultCount(totalCatererCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadCatererList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
			List<Caterer> Caterers = catererDAO.loadCatererList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
			for(Caterer Caterer : Caterers){
				list.add(Caterer.getName());
			}
		return list;
	}
	
	private void populateCatererResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO) {
		List<Caterer> Caterers = catererDAO.getCaterers(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder());
		for(Caterer Caterer : Caterers){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(Caterer.getName());
			searchResponseDTO.setMainImagerURL(Caterer.getAttachments().get(0).getImageURL());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchCatererDetails(DataRequestDTO dataRequestDTO) {
		Caterer Caterer = catererDAO.fetchCatererDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<String> serviceList = new ArrayList<String>();
		List<String> amenitiesList = new ArrayList<String>();
		Address address = Caterer.getAddresses().get(0);
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(Caterer.getName());
		detailResponseDTO.setAddressLine1(address.getAddressLine1());
		detailResponseDTO.setAddressLine2(address.getAddressLine2());
		detailResponseDTO.setCity(address.getCity());
		detailResponseDTO.setState(address.getState());
		//detailResponseDTO.setServices(serviceList);
		//detailResponseDTO.setAmenities(amenitiesList);
		return detailResponseDTO;
	}

}
