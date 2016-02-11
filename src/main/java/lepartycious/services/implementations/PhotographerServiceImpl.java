package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lepartycious.daos.PhotographerDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Address;
import lepartycious.models.Photographer;
import lepartycious.services.PhotographerService;


@Service
public class PhotographerServiceImpl implements PhotographerService {

	@Autowired
	private PhotographerDAO photographerDAO;

	@Override
	public SearchResponseDTOWrapper getPhotographers(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalPhotographerCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalPhotographerCount = photographerDAO.getPhotographerCount(searchDTO.getCityId(), searchDTO.getSearchString());
			if(totalPhotographerCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populatePhotographerResults(searchResponseDTOList, searchDTO);
		searchResponseDTOWrapper.setResultCount(totalPhotographerCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadPhotographerList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
			List<Photographer> Photographers = photographerDAO.loadPhotographerList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
			for(Photographer Photographer : Photographers){
				list.add(Photographer.getName());
			}
		return list;
	}
	
	private void populatePhotographerResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO) {
		List<Photographer> Photographers = photographerDAO.getPhotographers(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder());
		for(Photographer Photographer : Photographers){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(Photographer.getName());
			searchResponseDTO.setMainImagerURL(Photographer.getAttachments().get(0).getImageURL());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchPhotographerDetails(DataRequestDTO dataRequestDTO) {
		Photographer Photographer = photographerDAO.fetchPhotographerDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<String> serviceList = new ArrayList<String>();
		List<String> amenitiesList = new ArrayList<String>();
		Address address = Photographer.getAddresses().get(0);
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(Photographer.getName());
		detailResponseDTO.setAddressLine1(address.getAddressLine1());
		detailResponseDTO.setAddressLine2(address.getAddressLine2());
		detailResponseDTO.setCity(address.getCity());
		detailResponseDTO.setState(address.getState());
		//detailResponseDTO.setServices(serviceList);
		//detailResponseDTO.setAmenities(amenitiesList);
		return detailResponseDTO;
	}

}
