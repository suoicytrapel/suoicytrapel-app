package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.VenueDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.FilterRequestDTO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.AttachmentResponseDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.TabResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Address;
import lepartycious.models.Amenities;
import lepartycious.models.Attachment;
import lepartycious.models.City;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.models.Room;
import lepartycious.models.Venue;
import lepartycious.models.VenueAmenities;
import lepartycious.models.VenueRooms;
import lepartycious.models.EntityServices;
import lepartycious.services.VenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueDAO venueDAO;

	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private CommonDAO commonDAO;

	@Override
	public SearchResponseDTOWrapper getVenues(SearchRequestDTO searchDTO) {
		
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		FilterWrapperDTO filters = searchDTO.getFilters();
		Long totalVenueCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalVenueCount = venueDAO.getVenueCount(searchDTO.getCityId(), searchDTO.getSearchString(), filters);
			if(totalVenueCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populateVenueResults(searchResponseDTOList, searchDTO, filters);
		searchResponseDTOWrapper.setResultCount(totalVenueCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadVenueList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		List<Venue> venues = venueDAO.loadVenueList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
		for(Venue venue : venues){
			list.add(venue.getName());
		}
		return list;
	}

	private void populateVenueResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, FilterWrapperDTO filters) {
		List<Venue> venues = venueDAO.getVenues(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), filters);
		for(Venue venue : venues){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(venue.getName());
			searchResponseDTO.setMainImagerURL(venue.getAttachments().get(0).getImageURL());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchVenueDetails(DataRequestDTO dataRequestDTO) {
		Venue venue = venueDAO.fetchVenueDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		List<TabResponseDTO> amenitiesList = new ArrayList<TabResponseDTO>();
		List<TabResponseDTO> roomList = new ArrayList<TabResponseDTO>();
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> tabMap = new HashMap<String, List<TabResponseDTO>>();

		Address address = venue.getAddresses().get(0);
		for(EntityServices venueService : venue.getVenueServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			serviceDTO.setName(venueService.getServiceId().getTabDataName());
			serviceList.add(serviceDTO);
		}
		for(VenueAmenities venueAmenities : venue.getVenueamenities()){
			TabResponseDTO amenityDTO = new TabResponseDTO();
			amenityDTO.setName(venueAmenities.getAmenitiesId().getDescription());
			amenitiesList.add(amenityDTO);
		}
		for(VenueRooms room : venue.getVenueRooms()){
			TabResponseDTO roomDTO = new TabResponseDTO(room.getRoomId().getDescription(), room.getAcAvailability(), room.getFridgeAvailability(), room.getLockerAvailability(), room.getLedAvailability(), room.getAttachedBathroomAvailability(), room.getHotWaterAvailability(), room.getMinCost());
			roomList.add(roomDTO);
		}
		for(Attachment attachment : venue.getAttachments()){
			AttachmentResponseDTO attachmentDTO = new AttachmentResponseDTO(attachment.getImageURL(), attachment.getHelpText());
			attachmentList.add(attachmentDTO);
		}
		if(!CollectionUtils.isEmpty(serviceList)){
			tabMap.put("Services", serviceList);
		}
		if(!CollectionUtils.isEmpty(amenitiesList)){
			tabMap.put("Amenities", amenitiesList);
		}
		if(!CollectionUtils.isEmpty(roomList)){
			tabMap.put("Rooms", roomList);
		}
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(venue.getName());
		detailResponseDTO.setDescription(venue.getDescription());
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
		List<FilterResponseDTO> amenities = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> rooms = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> localities = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> establishments = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> prices = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> events = new ArrayList<FilterResponseDTO>();

		List<lepartycious.models.Service> serviceList = commonDAO.getServiceFilters("VENUE", "SERVICE");
		List<Amenities> amenityList = commonDAO.getAmenities("amenity");
		List<Room> roomList = commonDAO.getRooms("room");
		City city = cityDAO.getCityById(cityId);
		List<Locality> localityList = city.getLocalities();
		List<Filter> establishmentList = commonDAO.getRequiredFilters("VENUE", "ESTABLISHMENT");
		List<Filter> eventList = commonDAO.getRequiredFilters("ALL", "EVENT");
		List<Filter> priceRangeList = commonDAO.getRequiredFilters("VENUE", "PRICE");

		for(lepartycious.models.Service service : serviceList){
			FilterResponseDTO filter = new FilterResponseDTO(service.getFilterDataName(), service.getServiceType(), service.getServiceId());
			services.add(filter);
		}
		for(Amenities amenity : amenityList){
			FilterResponseDTO filter = new FilterResponseDTO(amenity.getDescription(), amenity.getAmenityType(), amenity.getAmenitiesId());
			amenities.add(filter);
		}
		for(Room room : roomList){
			FilterResponseDTO filter = new FilterResponseDTO(room.getDescription(), room.getRoomType(), room.getRoomId());
			rooms.add(filter);
		}
		for(Locality locality : localityList){
			FilterResponseDTO filter = new FilterResponseDTO(locality.getDescription(), null, locality.getLocalityId());
			localities.add(filter);
		}
		for(Filter estFilter : establishmentList){
			FilterResponseDTO filter = new FilterResponseDTO(estFilter.getFilterName(), estFilter.getFilterType(), estFilter.getFilterid());
			establishments.add(filter);
		}
		for(Filter priceFilter : priceRangeList){
			FilterResponseDTO filter = new FilterResponseDTO(priceFilter.getFilterName(), priceFilter.getFilterType(), priceFilter.getFilterid());
			prices.add(filter);
		}
		for(Filter eventFilter : eventList){
			FilterResponseDTO filter = new FilterResponseDTO(eventFilter.getFilterName(), eventFilter.getFilterType(), eventFilter.getFilterid());
			events.add(filter);
		}
		
		filterResponseWrapperDTO.setServices(services);
		filterResponseWrapperDTO.setAmenities(amenities);
		filterResponseWrapperDTO.setRooms(rooms);
		filterResponseWrapperDTO.setLocalities(localities);
		filterResponseWrapperDTO.setEstablishments(establishments);
		filterResponseWrapperDTO.setPriceRange(prices);
		filterResponseWrapperDTO.setEventType(events);
		return filterResponseWrapperDTO;
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(Long cityId) {
		List<SearchResponseDTO> recommendationList = new ArrayList<SearchResponseDTO>();
		List<Venue> venueList = venueDAO.fetchRecomendations(cityId);
		for(Venue venue : venueList){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(venue.getName());
			searchResponseDTO.setMainImagerURL(venue.getAttachments().get(0).getImageURL());
			recommendationList.add(searchResponseDTO);
		}
		return recommendationList;
	}

}
