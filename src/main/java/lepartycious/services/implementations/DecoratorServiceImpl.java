package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.DecoratorDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.AttachmentResponseDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.dtos.responseDTOs.TabResponseDTO;
import lepartycious.models.Address;
import lepartycious.models.Attachment;
import lepartycious.models.City;
import lepartycious.models.Decorator;
import lepartycious.models.EntityServices;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.services.DecoratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class DecoratorServiceImpl implements DecoratorService {

	@Autowired
	private DecoratorDAO decoratorDAO;

	@Autowired
	private CityDAO cityDAO;

	@Autowired
	private CommonDAO commonDAO;

	@Override
	public SearchResponseDTOWrapper getDecorators(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalDecoratorCount = null;
		FilterWrapperDTO filters = searchDTO.getFilters();
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalDecoratorCount = decoratorDAO.getDecoratorCount(searchDTO.getCityId(), searchDTO.getSearchString(), filters);
		}
		populateDecoratorResults(searchResponseDTOList, searchDTO, filters);
		searchResponseDTOWrapper.setResultCount(totalDecoratorCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadDecoratorList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		List<Decorator> decorators = decoratorDAO.loadDecoratorList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
		if(CollectionUtils.isEmpty(decorators)){
			return list;
		}
		for(Decorator Decorator : decorators){
			list.add(Decorator.getName());
		}
		return list;
	}

	private void populateDecoratorResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, FilterWrapperDTO filters) {
		List<Decorator> Decorators = decoratorDAO.getDecorators(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), filters);
		for(Decorator decorator : Decorators){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(decorator.getName());
			searchResponseDTO.setLocality(decorator.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(decorator.getStartingPrice());
			searchResponseDTO.setMainImagerURL(decorator.getAttachments().get(0).getImageURL());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchDecoratorDetails(DataRequestDTO dataRequestDTO) {
		Decorator decorator = decoratorDAO.fetchDecoratorDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> tabMap = new HashMap<String, List<TabResponseDTO>>();
		Address address = decorator.getAddresses().get(0);
		for(EntityServices decoratorService : decorator.getDecoratorServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			serviceDTO.setName(decoratorService.getServiceId().getTabDataName());
			serviceList.add(serviceDTO);
		}
		for(Attachment attachment : decorator.getAttachments()){
			AttachmentResponseDTO attachmentDTO = new AttachmentResponseDTO(attachment.getImageURL(), attachment.getHelpText());
			attachmentList.add(attachmentDTO);
		}
		if(!CollectionUtils.isEmpty(serviceList)){
			tabMap.put("Services", serviceList);
		}
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(decorator.getName());
		detailResponseDTO.setDescription(decorator.getDescription());
		detailResponseDTO.setAddressLine1(address.getAddressLine1());
		detailResponseDTO.setAddressLine2(address.getAddressLine2());
		detailResponseDTO.setCity(address.getCity());
		detailResponseDTO.setState(address.getState());
		detailResponseDTO.setPrimaryPhoneNumber(address.getPrimaryPhone());
		detailResponseDTO.setSecondaryPhoneNumber(address.getSecondaryPhone());
		detailResponseDTO.setLatitude(address.getLatitude());
		detailResponseDTO.setLongitude(address.getLongitude());
		detailResponseDTO.setTabMap(tabMap);
		detailResponseDTO.setAttachments(attachmentList);
		return detailResponseDTO;
	}

	@Override
	public FilterResponseWrapperDTO loadFilters(Long cityId) {
		FilterResponseWrapperDTO filterResponseWrapperDTO = new FilterResponseWrapperDTO();
		List<FilterResponseDTO> services = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> localities = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> events = new ArrayList<FilterResponseDTO>();
		City city = cityDAO.getCityById(cityId);
		List<Locality> localityList = city.getLocalities();

		List<lepartycious.models.Service> serviceList = commonDAO.getServiceFilters("DECORATOR", "SERVICE");
		List<Filter> eventList = commonDAO.getRequiredFilters("ALL", "EVENT");

		for(lepartycious.models.Service service : serviceList){
			FilterResponseDTO filter = new FilterResponseDTO(service.getFilterDataName(), service.getServiceType(), service.getServiceId());
			services.add(filter);
		}
		for(Filter eventFilter : eventList){
			FilterResponseDTO filter = new FilterResponseDTO(eventFilter.getFilterName(), eventFilter.getFilterType(), eventFilter.getFilterid());
			events.add(filter);
		}
		for(Locality locality : localityList){
			FilterResponseDTO filter = new FilterResponseDTO(locality.getDescription(), null, locality.getLocalityId());
			localities.add(filter);
		}
		filterResponseWrapperDTO.setServices(services);
		filterResponseWrapperDTO.setLocalities(localities);
		filterResponseWrapperDTO.setEventType(events);
		return filterResponseWrapperDTO;
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(Long cityId) {
		List<SearchResponseDTO> recommendationList = new ArrayList<SearchResponseDTO>();
		List<Decorator> decoratorList = decoratorDAO.fetchRecomendations(cityId);
		for(Decorator decorator : decoratorList){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(decorator.getName());
			searchResponseDTO.setLocality(decorator.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(decorator.getStartingPrice());
			searchResponseDTO.setMainImagerURL(decorator.getAttachments().get(0).getImageURL());
			recommendationList.add(searchResponseDTO);
		}
		return recommendationList;
	}

}
