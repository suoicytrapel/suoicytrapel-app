package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lepartycious.Enums.SearchTypeEnum;
import lepartycious.daos.CityDAO;
import lepartycious.daos.VenueDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Address;
import lepartycious.models.Amenities;
import lepartycious.models.Attachment;
import lepartycious.models.Venue;
import lepartycious.models.VenueAmenities;
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
		List<String> services = new ArrayList<String>();
		List<String> amenities = new ArrayList<String>();
		List<lepartycious.models.Service> serviceList = cityDAO.getServices();
		List<Amenities> amenitiesList = cityDAO.getAmenities();
		List<Long> serviceIds = new ArrayList<Long>();
		List<Long> amenityIds = new ArrayList<Long>();

		for(lepartycious.models.Service service : serviceList){
			services.add(service.getDescription());
			if(!CollectionUtils.isEmpty(searchDTO.getFilters())){
				for(String filter : searchDTO.getFilters()){
					if(filter.equalsIgnoreCase(service.getDescription())){
						serviceIds.add(service.getServiceId());
					}
				}
			}
		}
		for(Amenities amenity : amenitiesList){
			amenities.add(amenity.getDescription());
			if(!CollectionUtils.isEmpty(searchDTO.getFilters())){
				for(String filter : searchDTO.getFilters()){
					if(filter.equalsIgnoreCase(amenity.getDescription())){
						amenityIds.add(amenity.getAmenitiesId());
					}
				}
			}
		}
		Long totalVenueCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalVenueCount = venueDAO.getVenueCount(searchDTO.getCityId(), searchDTO.getSearchString(), serviceIds, amenityIds);
			if(totalVenueCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populateVenueResults(searchResponseDTOList, searchDTO, serviceIds, amenityIds);
		searchResponseDTOWrapper.setResultCount(totalVenueCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		searchResponseDTOWrapper.setAmenities(amenities);
		searchResponseDTOWrapper.setServices(services);
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
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, List<Long> serviceIds, List<Long> amenityIds) {
		List<Venue> venues = venueDAO.getVenues(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), serviceIds, amenityIds);
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
		Address address = venue.getAddresses().get(0);
		for(VenueServices venueService : venue.getVenueServices()){
			serviceList.add(venueService.getServiceId().getDescription());
		}
		for(VenueAmenities venueAmenities : venue.getVenueamenities()){
			amenitiesList.add(venueAmenities.getAmenitiesId().getDescription());
		}
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(venue.getName());
		detailResponseDTO.setAddressLine1(address.getAddressLine1());
		detailResponseDTO.setAddressLine2(address.getAddressLine2());
		detailResponseDTO.setCity(address.getCity());
		detailResponseDTO.setState(address.getState());
		detailResponseDTO.setServices(serviceList);
		detailResponseDTO.setAmenities(amenitiesList);
		return detailResponseDTO;
	}

}
