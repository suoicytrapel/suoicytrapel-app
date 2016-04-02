package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lepartycious.daos.CatererDAO;
import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
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
import lepartycious.models.Caterer;
import lepartycious.models.City;
import lepartycious.models.EntityServices;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.services.CatererService;
import lepartycious.services.CommonService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CatererServiceImpl implements CatererService {

	@Autowired
	private CatererDAO catererDAO;

	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private CommonService commonService;

	@Override
	public SearchResponseDTOWrapper getCaterers(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		FilterWrapperDTO filters = searchDTO.getFilters();
		Long totalCatererCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalCatererCount = catererDAO.getCatererCount(searchDTO.getCityId(), searchDTO.getSearchString(), filters);
		}
		populateCatererResults(searchResponseDTOList, searchDTO, filters);
		searchResponseDTOWrapper.setResultCount(totalCatererCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadCatererList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		List<Caterer> Caterers = catererDAO.loadCatererList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
		if(CollectionUtils.isEmpty(Caterers)){
			return list;
		}
		for(Caterer Caterer : Caterers){
			list.add(Caterer.getName());
		}
		return list;
	}

	private void populateCatererResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, FilterWrapperDTO filters) {
		List<Caterer> caterers = catererDAO.getCaterers(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), filters);
		for(Caterer caterer : caterers){
			if(CollectionUtils.isEmpty(caterer.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				caterer.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(caterer.getName());
			searchResponseDTO.setLocality(caterer.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(caterer.getStartingPrice());
			searchResponseDTO.setMainImagerURL(caterer.getAttachments().get(0).getImageURL());
			searchResponseDTO.setMaxCapacity(caterer.getMaxCapacity());
			searchResponseDTO.setIsPureVeg(caterer.getIsPureVeg());
			searchResponseDTO.setMinCapacity(caterer.getMinCapacity());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchCatererDetails(DataRequestDTO dataRequestDTO) {
		Caterer caterer = catererDAO.fetchCatererDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		List<TabResponseDTO> menuList = new ArrayList<TabResponseDTO>();
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> tabMap = new HashMap<String, List<TabResponseDTO>>();
		Address address = caterer.getAddresses().get(0);
		List<AttachmentResponseDTO> menuImageList = new ArrayList<AttachmentResponseDTO>();
		String cuisinesOffered = caterer.getCuisineOffered();
		StringTokenizer tokens = new StringTokenizer(cuisinesOffered, ",");
		while(tokens.hasMoreTokens()){
			TabResponseDTO cuisine = new TabResponseDTO();
			cuisine.setName(tokens.nextToken());
			menuList.add(cuisine);
		}
		for(EntityServices catererService : caterer.getCatererServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			serviceDTO.setName(catererService.getServiceId().getTabDataName());
			serviceList.add(serviceDTO);
		}
		for(Attachment attachment : caterer.getAttachments()){
			AttachmentResponseDTO attachmentDTO = new AttachmentResponseDTO(attachment.getImageURL(), attachment.getHelpText());
			if(StringUtils.isNotBlank(attachment.getAttachmentType()) && attachment.getAttachmentType().equalsIgnoreCase("MENU")){
				menuImageList.add(attachmentDTO);
			}
			else{
				attachmentList.add(attachmentDTO);
			}
		}
		if(!CollectionUtils.isEmpty(serviceList)){
			tabMap.put("Services", serviceList);
		}
		if(!CollectionUtils.isEmpty(menuList)){
			tabMap.put("Cuisines", menuList);
		}
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(caterer.getName());
		detailResponseDTO.setDescription(caterer.getDescription());
		detailResponseDTO.setAddressLine1(address.getAddressLine1());
		detailResponseDTO.setAddressLine2(address.getAddressLine2());
		detailResponseDTO.setCity(address.getCity());
		detailResponseDTO.setState(address.getState());
		detailResponseDTO.setPrimaryPhoneNumber(address.getPrimaryPhone());
		detailResponseDTO.setSecondaryPhoneNumber(address.getSecondaryPhone());
		detailResponseDTO.setLatitude(address.getLatitude());
		detailResponseDTO.setLongitude(address.getLongitude());
		if(!CollectionUtils.isEmpty(tabMap)){
			detailResponseDTO.setServiceAmenityTabMap(tabMap);
		}
		detailResponseDTO.setAttachments(attachmentList);
		detailResponseDTO.setMenuImages(menuImageList);
		detailResponseDTO.setIsPureVeg(caterer.getIsPureVeg());
		detailResponseDTO.setPolicies(caterer.getPolicies());
		detailResponseDTO.setServingSince(caterer.getServingSince());
		detailResponseDTO.setMaxCapacity(caterer.getMaxCapacity());
		detailResponseDTO.setStartingFrom(caterer.getStartingPrice());
		detailResponseDTO.setMinCapacity(caterer.getMinCapacity());
		return detailResponseDTO;
	}

	@Override
	public FilterResponseWrapperDTO loadFilters(Long cityId) {
		FilterResponseWrapperDTO filterResponseWrapperDTO = new FilterResponseWrapperDTO();
		List<FilterResponseDTO> services = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> localities = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> prices = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> events = new ArrayList<FilterResponseDTO>();
		City city = cityDAO.getCityById(cityId);
		List<Locality> localityList = city.getLocalities();
		List<FilterResponseDTO> capacity = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> catererType = new ArrayList<FilterResponseDTO>();
		
		List<lepartycious.models.Service> serviceList = commonDAO.getServiceFilters("CATERER", "SERVICE");
		List<Filter> eventList = commonDAO.getRequiredFilters("ALL", "EVENT");
		List<Filter> priceRangeList = commonDAO.getRequiredFilters("CATERER", "PRICE");
		List<Filter> capacityFilters = commonDAO.getRequiredFilters("CATERER", "CAPACITY");
		List<Filter> typeFilters = commonDAO.getRequiredFilters("CATERER", "TYPE");
		
		for(lepartycious.models.Service service : serviceList){
			FilterResponseDTO filter = new FilterResponseDTO(service.getFilterDataName(), service.getServiceType(), service.getServiceId());
			services.add(filter);
		}
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
		for(Filter capFilter : capacityFilters){
			FilterResponseDTO filter = new FilterResponseDTO(capFilter.getFilterName(), capFilter.getFilterType(), capFilter.getFilterid());
			capacity.add(filter);
		}
		for(Filter typeFilter : typeFilters){
			FilterResponseDTO filter = new FilterResponseDTO(typeFilter.getFilterName(), typeFilter.getFilterType(), typeFilter.getFilterid());
			catererType.add(filter);
		}
		filterResponseWrapperDTO.setServices(services);
		filterResponseWrapperDTO.setLocalities(localities);
		filterResponseWrapperDTO.setPriceRange(prices);
		filterResponseWrapperDTO.setEventType(events);
		filterResponseWrapperDTO.setCapacity(capacity);
		filterResponseWrapperDTO.setCatererType(catererType);
		return filterResponseWrapperDTO;
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(Long cityId) {
		List<SearchResponseDTO> recommendationList = new ArrayList<SearchResponseDTO>();
		List<Caterer> catererList = catererDAO.fetchRecomendations(cityId);
		for(Caterer caterer : catererList){
			if(CollectionUtils.isEmpty(caterer.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				caterer.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(caterer.getName());
			searchResponseDTO.setLocality(caterer.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(caterer.getStartingPrice());
			searchResponseDTO.setMainImagerURL(caterer.getAttachments().get(0).getImageURL());
			recommendationList.add(searchResponseDTO);
		}
		return recommendationList;
	}

}
