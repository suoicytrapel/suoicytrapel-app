package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.RentalDAO;
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
import lepartycious.models.Rental;
import lepartycious.services.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalDAO rentalDAO;

	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private CommonDAO commonDAO;

	@Override
	public SearchResponseDTOWrapper getRentals(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		FilterWrapperDTO filters = searchDTO.getFilters();
		Long totalRentalCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalRentalCount = rentalDAO.getRentalCount(searchDTO.getCityId(), searchDTO.getSearchString(), filters);
			if(totalRentalCount < 1){
				throw new IllegalArgumentException("No Records Found");
			}
		}
		populateRentalResults(searchResponseDTOList, searchDTO, filters);
		searchResponseDTOWrapper.setResultCount(totalRentalCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadRentalList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		List<Rental> rentals = rentalDAO.loadRentalList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
		if(CollectionUtils.isEmpty(rentals)){
			throw new IllegalArgumentException("No Records Found");
		}
		for(Rental Rental : rentals){
			list.add(Rental.getName());
		}
		return list;
	}

	private void populateRentalResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, FilterWrapperDTO filters) {
		List<Rental> Rentals = rentalDAO.getRentals(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), filters);
		for(Rental Rental : Rentals){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(Rental.getName());
			searchResponseDTO.setMainImagerURL(Rental.getAttachments().get(0).getImageURL());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchRentalDetails(DataRequestDTO dataRequestDTO) {
		Rental rental = rentalDAO.fetchRentalDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> tabMap = new HashMap<String, List<TabResponseDTO>>();
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		Address address = rental.getAddresses().get(0);
		for(Attachment attachment : rental.getAttachments()){
			AttachmentResponseDTO attachmentDTO = new AttachmentResponseDTO(attachment.getImageURL(), attachment.getHelpText());
			attachmentList.add(attachmentDTO);
		}
		for(EntityServices rentalService : rental.getRentalServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			serviceDTO.setName(rentalService.getServiceId().getTabDataName());
			serviceList.add(serviceDTO);
		}
		if(!CollectionUtils.isEmpty(serviceList)){
			tabMap.put("Services", serviceList);
		}
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
		detailResponseDTO.setTabMap(tabMap);
		detailResponseDTO.setAttachments(attachmentList);
		//detailResponseDTO.setServices(serviceList);
		return detailResponseDTO;
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(Long cityId) {
		List<SearchResponseDTO> recommendationList = new ArrayList<SearchResponseDTO>();
		List<Rental> rentalList = rentalDAO.fetchRecomendations(cityId);
		for(Rental rental : rentalList){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(rental.getName());
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
		List<Filter> rentalList = commonDAO.getRequiredFilters("RENTAL", "RENTAL");
		
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
		filterResponseWrapperDTO.setRentalType(rentalTypes);
		return filterResponseWrapperDTO;
	}

}
