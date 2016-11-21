package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.EntertainmentDAO;
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
import lepartycious.models.EntityServices;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.models.Entertainment;
import lepartycious.services.CommonService;
import lepartycious.services.EntertainmentService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class EntertainmentServiceImpl implements EntertainmentService {

	@Autowired
	private EntertainmentDAO entertainmentDAO;

	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ReviewCommentDAO reviewCommentDAO;

	@Override
	public SearchResponseDTOWrapper getRentals(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		FilterWrapperDTO filters = searchDTO.getFilters();
		Long totalRentalCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalRentalCount = entertainmentDAO.getRentalCount(searchDTO.getCityId(), searchDTO.getSearchString(), filters);
		}
		populateRentalResults(searchResponseDTOList, searchDTO, filters);
		searchResponseDTOWrapper.setResultCount(totalRentalCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadRentalList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		List<Entertainment> rentals = entertainmentDAO.loadRentalList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
		if(CollectionUtils.isEmpty(rentals)){
			return list;
		}
		for(Entertainment Rental : rentals){
			list.add(Rental.getName());
		}
		return list;
	}

	private void populateRentalResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, FilterWrapperDTO filters) {
		List<Entertainment> Rentals = entertainmentDAO.getRentals(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), filters);
		for(Entertainment rental : Rentals){
			if(CollectionUtils.isEmpty(rental.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				rental.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(rental.getName());

			//Code to get average vendor rating
			SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
			searchRequestDTO.setVendorId(rental.getRentalId());
			Double rating = reviewCommentDAO.getAverageRatingOfVendor(searchRequestDTO);
			searchResponseDTO.setAverageRating(rating);
			//code ends here 
			
			searchResponseDTO.setLocality(rental.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(rental.getStartingPrice());
			searchResponseDTO.setMainImagerURL(rental.getAttachments().get(0).getImageURL());
			searchResponseDTO.setStartingPrice(rental.getStartingPrice());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchRentalDetails(DataRequestDTO dataRequestDTO) {
		Entertainment rental = entertainmentDAO.fetchRentalDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> tabMap = new HashMap<String, List<TabResponseDTO>>();
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		Map<String, String> policiesTabMap = new HashMap<String, String>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int servingSinceYear = Integer.parseInt(rental.getServingSince());
		int experienceInYears = currentYear - servingSinceYear;
		List<String> policiList = new ArrayList<String>();
		Map<String, String> keyHighlighs = new HashMap<String, String>();
		Map<String, String> additionalServices = new HashMap<String, String>();
		Address address = rental.getAddresses().get(0);
		
		keyHighlighs.put("Package Starts From", rental.getStartingPrice());
		for(Attachment attachment : rental.getAttachments()){
			AttachmentResponseDTO attachmentDTO = new AttachmentResponseDTO(attachment.getImageURL(), attachment.getHelpText());
			attachmentList.add(attachmentDTO);
		}
		for(EntityServices rentalService : rental.getServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			String serviceName = rentalService.getServiceId().getTabDataName();
			String mapValue = rentalService.getMinCost() != null ? rentalService.getMinCost().toString():"";
			if(rentalService.getServiceId().getIsKeyHighlight()){
				keyHighlighs.put(serviceName, mapValue);
			}
			else if(rentalService.getServiceId().getIsAdditionalService()){
				additionalServices.put(serviceName, mapValue);
			}
			serviceDTO.setName(rentalService.getServiceId().getTabDataName());
			serviceList.add(serviceDTO);
		}
		if(!CollectionUtils.isEmpty(serviceList)){
			tabMap.put("Services", serviceList);
		}
		if(StringUtils.isNotBlank(rental.getPolicies())){
			StringTokenizer strTokenizer = new StringTokenizer(rental.getPolicies(), "<br>");
			while(strTokenizer.hasMoreElements()){
				policiList.add((String) strTokenizer.nextElement());
			}
		}
		TabResponseDTO additionalInfoTabData = new TabResponseDTO();
		additionalInfoTabData.setOutstationExpenses(rental.getTravelStayExpenses());
		additionalInfoTabData.setGenre(rental.getGenre());
		additionalInfoTabData.setExperience(Integer.toString(experienceInYears));
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(rental.getName());
		detailResponseDTO.setDescription(rental.getDescription());
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
		detailResponseDTO.setAttachments(attachmentList);
		detailResponseDTO.setServingSince(rental.getServingSince());
		detailResponseDTO.setStartingFrom(rental.getStartingPrice());
		detailResponseDTO.setPolicies(policiList);
		detailResponseDTO.setAdditionalInfo(additionalInfoTabData);
		detailResponseDTO.setGroup(rental.isGroup());
		return detailResponseDTO;
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(Long cityId) {
		List<SearchResponseDTO> recommendationList = new ArrayList<SearchResponseDTO>();
		List<Entertainment> rentalList = entertainmentDAO.fetchRecomendations(cityId);
		for(Entertainment rental : rentalList){
			if(CollectionUtils.isEmpty(rental.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				rental.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(rental.getName());
			searchResponseDTO.setLocality(rental.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(rental.getStartingPrice());
			searchResponseDTO.setMainImagerURL(rental.getAttachments().get(0).getImageURL());
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
		List<Filter> rentalList = commonDAO.getRequiredFilters("ENTERTAINMENT", "ENTERTAINMENT");
		
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
		filterResponseWrapperDTO.setEntertainmentType(rentalTypes);
		return filterResponseWrapperDTO;
	}

}
