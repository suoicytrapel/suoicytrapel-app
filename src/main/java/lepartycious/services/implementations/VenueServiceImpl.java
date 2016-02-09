package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lepartycious.daos.CityDAO;
import lepartycious.daos.VenueDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.FilterRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Address;
import lepartycious.models.Amenities;
import lepartycious.models.Attachment;
import lepartycious.models.City;
import lepartycious.models.Locality;
import lepartycious.models.Room;
import lepartycious.models.Venue;
import lepartycious.models.VenueAmenities;
import lepartycious.models.VenueRooms;
import lepartycious.models.VenueServices;
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

	@Override
	public SearchResponseDTOWrapper getVenues(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		List<lepartycious.models.Service> serviceList = cityDAO.getServices("service");
		List<Amenities> amenitiesList = cityDAO.getAmenities("amenity");
		List<Room> roomList = cityDAO.getRooms("room");
		City city = cityDAO.getCityById(searchDTO.getCityId());
		List<Locality> localityList = city.getLocalities();
		
		List<Long> serviceIds = new ArrayList<Long>();
		List<Long> amenityIds = new ArrayList<Long>();
		List<Long> roomIds = new ArrayList<Long>();;
		List<Long> localityIds = new ArrayList<Long>();
		List<String> types = new ArrayList<String>();
		
		Map<String, String> establishmentType = getEstablishmentTypes();
		for(Entry<String, String> entry : establishmentType.entrySet()){
			if(!CollectionUtils.isEmpty(searchDTO.getFilters())){
				for(String filter : searchDTO.getFilters()){
					if(filter.equalsIgnoreCase(entry.getKey())){
						types.add(entry.getKey());
					}
				}
			}
		}
		
		for(lepartycious.models.Service service : serviceList){
			if(!CollectionUtils.isEmpty(searchDTO.getFilters())){
				for(String filter : searchDTO.getFilters()){
					if(filter.equalsIgnoreCase(service.getServiceType())){
						serviceIds.add(service.getServiceId());
					}
				}
			}
		}
		for(Amenities amenity : amenitiesList){
			if(!CollectionUtils.isEmpty(searchDTO.getFilters())){
				for(String filter : searchDTO.getFilters()){
					if(filter.equalsIgnoreCase(amenity.getAmenityType())){
						amenityIds.add(amenity.getAmenitiesId());
					}
				}
			}
		}
		for(Room room : roomList){
			if(!CollectionUtils.isEmpty(searchDTO.getFilters())){
				for(String filter : searchDTO.getFilters()){
					if(filter.equalsIgnoreCase(room.getRoomType())){
						roomIds.add(room.getRoomId());
					}
				}
			}
		}
		for(Locality locality : localityList){
			if(!CollectionUtils.isEmpty(searchDTO.getFilters())){
				for(String filter : searchDTO.getFilters()){
					if(filter.equalsIgnoreCase(locality.getDescription())){
						localityIds.add(locality.getLocalityId());
					}
				}
			}
		}
		
		
		Long totalVenueCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalVenueCount = venueDAO.getVenueCount(searchDTO.getCityId(), searchDTO.getSearchString(), serviceIds, amenityIds, roomIds, localityIds, types);
			if(totalVenueCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populateVenueResults(searchResponseDTOList, searchDTO, serviceIds, amenityIds, roomIds, localityIds, types);
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
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, List<Long> serviceIds, List<Long> amenityIds, List<Long> roomIds, List<Long> localityIds, List<String> types) {
		List<Venue> venues = venueDAO.getVenues(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), serviceIds, amenityIds, roomIds, localityIds, types);
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
		List<String> serviceList = new ArrayList<String>();
		List<String> amenitiesList = new ArrayList<String>();
		List<String> roomList = new ArrayList<String>();
		List<String> attachmentList = new ArrayList<String>();
		
		Address address = venue.getAddresses().get(0);
		for(VenueServices venueService : venue.getVenueServices()){
			serviceList.add(venueService.getServiceId().getDescription());
		}
		for(VenueAmenities venueAmenities : venue.getVenueamenities()){
			amenitiesList.add(venueAmenities.getAmenitiesId().getDescription());
		}
		for(VenueRooms room : venue.getVenueRooms()){
			roomList.add(room.getRoomId().getDescription());
		}
		for(Attachment attachment : venue.getAttachments()){
			attachmentList.add(attachment.getImageURL());
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
		detailResponseDTO.setServices(serviceList);
		detailResponseDTO.setAmenities(amenitiesList);
		detailResponseDTO.setRooms(roomList);
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
		
		List<lepartycious.models.Service> serviceList = cityDAO.getServices("service");
		List<Amenities> amenityList = cityDAO.getAmenities("amenity");
		List<Room> roomList = cityDAO.getRooms("room");
		City city = cityDAO.getCityById(cityId);
		List<Locality> localityList = city.getLocalities();
		Map<String, String> establishmentType = getEstablishmentTypes();
		
		for(lepartycious.models.Service service : serviceList){
			FilterResponseDTO filter = new FilterResponseDTO(service.getDescription(), service.getServiceType(), service.getServiceId());
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
		for(Entry<String, String> entry : establishmentType.entrySet()){
			FilterResponseDTO filter = new FilterResponseDTO(entry.getValue(), entry.getKey(), null);
			establishments.add(filter);
		}
		filterResponseWrapperDTO.setServices(services);
		filterResponseWrapperDTO.setAmenities(amenities);
		filterResponseWrapperDTO.setRooms(rooms);
		filterResponseWrapperDTO.setLocalities(localities);
		filterResponseWrapperDTO.setEstablishments(establishments);
		return filterResponseWrapperDTO;
	}

	private Map<String, String> getEstablishmentTypes() {
		Map<String, String> establishmentType = new HashMap<String, String>();
		establishmentType.put("PALACE", "Palace");
		establishmentType.put("RESTAURANT", "Restaurant");
		establishmentType.put("HOTEL", "Hotel");
		establishmentType.put("CASUALDINE", "Casual Dining");
		return establishmentType;
	}

}
