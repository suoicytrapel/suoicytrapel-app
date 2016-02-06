package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lepartycious.daos.DecoratorDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Address;
import lepartycious.models.Decorator;
import lepartycious.services.DecoratorService;

@Service
public class DecoratorServiceImpl implements DecoratorService {

	@Autowired
	private DecoratorDAO decoratorDAO;

	@Override
	public SearchResponseDTOWrapper getDecorators(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalDecoratorCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalDecoratorCount = decoratorDAO.getDecoratorCount(searchDTO.getCityId(), searchDTO.getSearchString());
			if(totalDecoratorCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populateDecoratorResults(searchResponseDTOList, searchDTO);
		searchResponseDTOWrapper.setResultCount(totalDecoratorCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadDecoratorList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
			List<Decorator> Decorators = decoratorDAO.loadDecoratorList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
			for(Decorator Decorator : Decorators){
				list.add(Decorator.getName());
			}
		return list;
	}
	
	private void populateDecoratorResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO) {
		List<Decorator> Decorators = decoratorDAO.getDecorators(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder());
		for(Decorator Decorator : Decorators){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(Decorator.getName());
			searchResponseDTO.setMainImagerURL(Decorator.getAttachments().get(0).getImageURL());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchDecoratorDetails(DataRequestDTO dataRequestDTO) {
		Decorator Decorator = decoratorDAO.fetchDecoratorDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<String> serviceList = new ArrayList<String>();
		List<String> amenitiesList = new ArrayList<String>();
		Address address = Decorator.getAddresses().get(0);
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(Decorator.getName());
		detailResponseDTO.setAddressLine1(address.getAddressLine1());
		detailResponseDTO.setAddressLine2(address.getAddressLine2());
		detailResponseDTO.setCity(address.getCity());
		detailResponseDTO.setState(address.getState());
		detailResponseDTO.setServices(serviceList);
		detailResponseDTO.setAmenities(amenitiesList);
		return detailResponseDTO;
	}

}
