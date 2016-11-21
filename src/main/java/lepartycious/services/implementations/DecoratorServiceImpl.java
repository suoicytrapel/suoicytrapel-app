package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.DecoratorDAO;
import lepartycious.daos.ReviewCommentDAO;
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
import lepartycious.services.CommonService;
import lepartycious.services.DecoratorService;

import org.apache.commons.lang3.StringUtils;
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
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ReviewCommentDAO reviewCommentDAO;

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
			if(CollectionUtils.isEmpty(decorator.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				decorator.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(decorator.getName());
			
			//Code to get average vendor rating
			SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
			searchRequestDTO.setVendorId(decorator.getDecoratorId());
			Double rating = reviewCommentDAO.getAverageRatingOfVendor(searchRequestDTO);
			searchResponseDTO.setAverageRating(rating);
			//code ends here 
			
			searchResponseDTO.setLocality(decorator.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(decorator.getStartingPrice());
			searchResponseDTO.setMainImagerURL(decorator.getAttachments().get(0).getImageURL());
			searchResponseDTO.setStartingPrice(decorator.getStartingPrice());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchDecoratorDetails(DataRequestDTO dataRequestDTO) {
		Decorator decorator = decoratorDAO.fetchDecoratorDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> tabMap = new HashMap<String, List<TabResponseDTO>>();
		List<String> policiList = new ArrayList<String>();
		Map<String, String> keyHighlighs = new LinkedHashMap<String, String>();
		Map<String, String> additionalServices = new HashMap<String, String>();
		Address address = decorator.getAddresses().get(0);
		
		keyHighlighs.put("Package Starts From", decorator.getStartingPrice());
		for(EntityServices decoratorService : decorator.getDecoratorServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			String serviceName = decoratorService.getServiceId().getTabDataName();
			String mapValue = decoratorService.getMinCost() != null ? decoratorService.getMinCost().toString():"";
			if(decoratorService.getServiceId().getIsKeyHighlight()){
				keyHighlighs.put(serviceName, mapValue);
			}
			else if(decoratorService.getServiceId().getIsAdditionalService()){
				additionalServices.put(serviceName, mapValue);
			}
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
		if(StringUtils.isNotBlank(decorator.getPolicies())){
			StringTokenizer strTokenizer = new StringTokenizer(decorator.getPolicies(), "<br>");
			while(strTokenizer.hasMoreElements()){
				policiList.add((String) strTokenizer.nextElement());
			}
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
		detailResponseDTO.setEmail(address.getEmail());
		if(!CollectionUtils.isEmpty(tabMap)){
			detailResponseDTO.setServiceAmenityTabMap(tabMap);
		}
		if(!CollectionUtils.isEmpty(keyHighlighs)){
			detailResponseDTO.setKeyHighlighs(keyHighlighs);
		}
		if(!CollectionUtils.isEmpty(additionalServices)){
			detailResponseDTO.setAdditionalServices(additionalServices);
		}
		detailResponseDTO.setPolicies(policiList);
		detailResponseDTO.setAttachments(attachmentList);
		detailResponseDTO.setServingSince(decorator.getServingSince());
		detailResponseDTO.setStartingFrom(decorator.getStartingPrice());
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
			if(CollectionUtils.isEmpty(decorator.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				decorator.setAttachments(defaultImageList);;
			}
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
