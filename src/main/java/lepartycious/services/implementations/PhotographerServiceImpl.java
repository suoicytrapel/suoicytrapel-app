package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.PhotographerDAO;
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
import lepartycious.models.EntityServices;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.models.Photographer;
import lepartycious.services.CommonService;
import lepartycious.services.PhotographerService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Service
public class PhotographerServiceImpl implements PhotographerService {

	@Autowired
	private PhotographerDAO photographerDAO;

	@Autowired
	private CityDAO cityDAO;

	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private CommonService commonService;

	@Override
	public SearchResponseDTOWrapper getPhotographers(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalPhotographerCount = null;
		FilterWrapperDTO filters = searchDTO.getFilters();
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalPhotographerCount = photographerDAO.getPhotographerCount(searchDTO.getCityId(), searchDTO.getSearchString(),filters);
		}
		populatePhotographerResults(searchResponseDTOList, searchDTO, filters);
		searchResponseDTOWrapper.setResultCount(totalPhotographerCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadPhotographerList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		List<Photographer> photographers = photographerDAO.loadPhotographerList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
		if(CollectionUtils.isEmpty(photographers)){
			return list;
		}
		for(Photographer Photographer : photographers){
			list.add(Photographer.getName());
		}
		return list;
	}

	private void populatePhotographerResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, FilterWrapperDTO filters) {
		List<Photographer> Photographers = photographerDAO.getPhotographers(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), filters);
		for(Photographer photographer : Photographers){
			if(CollectionUtils.isEmpty(photographer.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				photographer.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(photographer.getName());
			searchResponseDTO.setMainImagerURL(photographer.getAttachments().get(0).getImageURL());
			searchResponseDTO.setLocality(photographer.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(photographer.getStartingPrice());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchPhotographerDetails(DataRequestDTO dataRequestDTO) {
		Photographer photographer = photographerDAO.fetchPhotographerDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> tabMap = new HashMap<String, List<TabResponseDTO>>();
		List<String> policiList = new ArrayList<String>();
		Map<String, String> keyHighlighs = new LinkedHashMap<String, String>();
		Map<String, String> additionalServices = new HashMap<String, String>();
		Address address = photographer.getAddresses().get(0);
		
		keyHighlighs.put("Package Starts From", photographer.getStartingPrice());
		for(EntityServices photographerService : photographer.getPhotographerServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			String serviceName = photographerService.getServiceId().getTabDataName();
			String mapValue = photographerService.getMinCost() != null ? photographerService.getMinCost().toString():"";
			if(photographerService.getServiceId().getIsKeyHighlight()){
				keyHighlighs.put(serviceName, mapValue);
			}
			else if(photographerService.getServiceId().getIsAdditionalService()){
				additionalServices.put(serviceName, mapValue);
			}
			serviceDTO.setName(photographerService.getServiceId().getTabDataName());
			serviceList.add(serviceDTO);
		}
		for(Attachment attachment : photographer.getAttachments()){
			AttachmentResponseDTO attachmentDTO = new AttachmentResponseDTO(attachment.getImageURL(), attachment.getHelpText());
			attachmentList.add(attachmentDTO);
		}
		if(!CollectionUtils.isEmpty(serviceList)){
			tabMap.put("Services", serviceList);
		}
		if(StringUtils.isNotBlank(photographer.getPolicies())){
			StringTokenizer strTokenizer = new StringTokenizer(photographer.getPolicies(), "<br>");
			while(strTokenizer.hasMoreElements()){
				policiList.add((String) strTokenizer.nextElement());
			}
		}
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(photographer.getName());
		detailResponseDTO.setDescription(photographer.getDescription());
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
		detailResponseDTO.setServingSince(photographer.getServingSince());
		detailResponseDTO.setStartingFrom(photographer.getStartingPrice());
		return detailResponseDTO;
	}

	@Override
	public FilterResponseWrapperDTO loadFilters(Long cityId) {
		FilterResponseWrapperDTO filterResponseWrapperDTO = new FilterResponseWrapperDTO();
		List<FilterResponseDTO> localities = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> prices = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> events = new ArrayList<FilterResponseDTO>();
		City city = cityDAO.getCityById(cityId);
		List<Locality> localityList = city.getLocalities();

		List<Filter> eventList = commonDAO.getRequiredFilters("ALL", "EVENT");
		List<Filter> priceRangeList = commonDAO.getRequiredFilters("PHOTOGRAPHER", "PRICE");

		for(Filter priceFilter : priceRangeList){
			FilterResponseDTO filter = new FilterResponseDTO(priceFilter.getFilterName(), priceFilter.getFilterType(), priceFilter.getFilterid());
			prices.add(filter);
		}
		for(Filter eventFilter : eventList){
			FilterResponseDTO filter = new FilterResponseDTO(eventFilter.getFilterName(), eventFilter.getFilterType(), eventFilter.getFilterid());
			events.add(filter);
		}
		for(Locality locality : localityList){
			FilterResponseDTO filter = new FilterResponseDTO(locality.getDescription(), null, locality.getLocalityId());
			localities.add(filter);
		}
		filterResponseWrapperDTO.setLocalities(localities);
		filterResponseWrapperDTO.setPriceRange(prices);
		filterResponseWrapperDTO.setEventType(events);
		return filterResponseWrapperDTO;
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(Long cityId) {
		List<SearchResponseDTO> recommendationList = new ArrayList<SearchResponseDTO>();
		List<Photographer> photographerList = photographerDAO.fetchRecomendations(cityId);
		for(Photographer photographer : photographerList){
			if(CollectionUtils.isEmpty(photographer.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				photographer.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(photographer.getName());
			searchResponseDTO.setLocality(photographer.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(photographer.getStartingPrice());
			searchResponseDTO.setMainImagerURL(photographer.getAttachments().get(0).getImageURL());
			recommendationList.add(searchResponseDTO);
		}
		return recommendationList;
	}

}
