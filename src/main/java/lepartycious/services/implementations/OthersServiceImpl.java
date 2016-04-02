package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.EntertainmentDAO;
import lepartycious.daos.OthersDAO;
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
import lepartycious.models.Entertainment;
import lepartycious.models.Others;
import lepartycious.services.CommonService;
import lepartycious.services.EntertainmentService;
import lepartycious.services.OthersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class OthersServiceImpl implements OthersService {

	@Autowired
	private OthersDAO othersDAO;

	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private CommonService commonService;

	@Override
	public SearchResponseDTOWrapper getOthers(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		FilterWrapperDTO filters = searchDTO.getFilters();
		Long totalRentalCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalRentalCount = othersDAO.getOthersCount(searchDTO.getCityId(), searchDTO.getSearchString(), filters);
		}
		populateOthersResults(searchResponseDTOList, searchDTO, filters);
		searchResponseDTOWrapper.setResultCount(totalRentalCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadOthersList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		List<Others> others = othersDAO.loadOthersList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
		if(CollectionUtils.isEmpty(others)){
			return list;
		}
		for(Others other : others){
			list.add(other.getName());
		}
		return list;
	}

	private void populateOthersResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, FilterWrapperDTO filters) {
		List<Others> others = othersDAO.getOthers(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), filters);
		for(Others other : others){
			if(CollectionUtils.isEmpty(other.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				other.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(other.getName());
			searchResponseDTO.setLocality(other.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(other.getStartingPrice());
			searchResponseDTO.setMainImagerURL(other.getAttachments().get(0).getImageURL());
			searchResponseDTO.setStartingPrice(other.getStartingPrice());
			searchResponseDTO.setMinCapacity(other.getMinCapacity());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchOthersDetails(DataRequestDTO dataRequestDTO) {
		Others other = othersDAO.fetchOthersDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> tabMap = new HashMap<String, List<TabResponseDTO>>();
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		Address address = other.getAddresses().get(0);
		for(Attachment attachment : other.getAttachments()){
			AttachmentResponseDTO attachmentDTO = new AttachmentResponseDTO(attachment.getImageURL(), attachment.getHelpText());
			attachmentList.add(attachmentDTO);
		}
		for(EntityServices rentalService : other.getServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			serviceDTO.setName(rentalService.getServiceId().getTabDataName());
			serviceList.add(serviceDTO);
		}
		if(!CollectionUtils.isEmpty(serviceList)){
			tabMap.put("Services", serviceList);
		}
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(other.getName());
		detailResponseDTO.setDescription(other.getDescription());
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
		detailResponseDTO.setPolicies(other.getPolicies());
		detailResponseDTO.setAttachments(attachmentList);
		detailResponseDTO.setServingSince(other.getServingSince());
		detailResponseDTO.setStartingFrom(other.getStartingPrice());
		return detailResponseDTO;
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(Long cityId) {
		List<SearchResponseDTO> recommendationList = new ArrayList<SearchResponseDTO>();
		List<Others> othersList = othersDAO.fetchRecomendations(cityId);
		for(Others other : othersList){
			if(CollectionUtils.isEmpty(other.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				other.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(other.getName());
			searchResponseDTO.setLocality(other.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(other.getStartingPrice());
			searchResponseDTO.setMainImagerURL(other.getAttachments().get(0).getImageURL());
			recommendationList.add(searchResponseDTO);
		}
		return recommendationList;
	}

	@Override
	public FilterResponseWrapperDTO loadFilters(Long cityId) {
		FilterResponseWrapperDTO filterResponseWrapperDTO = new FilterResponseWrapperDTO();
		List<FilterResponseDTO> localities = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> events = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> rentalTypes = new ArrayList<FilterResponseDTO>();
		City city = cityDAO.getCityById(cityId);
		List<Locality> localityList = city.getLocalities();
		
		List<Filter> eventList = commonDAO.getRequiredFilters("ALL", "EVENT");
		List<Filter> rentalList = commonDAO.getRequiredFilters("OTHERS", "OTHERS");
		
		for(Filter eventFilter : eventList){
			FilterResponseDTO filter = new FilterResponseDTO(eventFilter.getFilterName(), eventFilter.getFilterType(), eventFilter.getFilterid());
			events.add(filter);
		}
		for(Filter rentTypeFilter : rentalList){
			FilterResponseDTO filter = new FilterResponseDTO(rentTypeFilter.getFilterName(), rentTypeFilter.getFilterType(), rentTypeFilter.getFilterid());
			rentalTypes.add(filter);
		}
		for(Locality locality : localityList){
			FilterResponseDTO filter = new FilterResponseDTO(locality.getDescription(), null, locality.getLocalityId());
			localities.add(filter);
		}
		filterResponseWrapperDTO.setLocalities(localities);
		filterResponseWrapperDTO.setEventType(events);
		filterResponseWrapperDTO.setOthersType(rentalTypes);
		return filterResponseWrapperDTO;
	}

}
