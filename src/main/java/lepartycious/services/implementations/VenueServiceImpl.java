package lepartycious.services.implementations;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lepartycious.daos.CityDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.ReviewCommentDAO;
import lepartycious.daos.VenueDAO;
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
import lepartycious.dtos.responseDTOs.VenuePackageDTO;
import lepartycious.models.AdditionalEntityServices;
import lepartycious.models.Address;
import lepartycious.models.Amenities;
import lepartycious.models.Attachment;
import lepartycious.models.City;
import lepartycious.models.EntityServices;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.models.Room;
import lepartycious.models.Venue;
import lepartycious.models.VenueAmenities;
import lepartycious.models.VenuePackages;
import lepartycious.models.VenueRooms;
import lepartycious.services.CommonService;
import lepartycious.services.VenueService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ReviewCommentDAO reviewCommentDAO;

	@Override
	public SearchResponseDTOWrapper getVenues(SearchRequestDTO searchDTO) {
		
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		FilterWrapperDTO filters = searchDTO.getFilters();
		Long totalVenueCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalVenueCount = venueDAO.getVenueCount(searchDTO.getCityId(), searchDTO.getSearchString(), filters);
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
		if(CollectionUtils.isEmpty(venues)){
			return list;
		}
		for(Venue venue : venues){
			list.add(venue.getName());
		}
		return list;
	}

	private void populateVenueResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO, FilterWrapperDTO filters) {
		List<Venue> venues = venueDAO.getVenues(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder(), filters);
		for(Venue venue : venues){
			if(CollectionUtils.isEmpty(venue.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				venue.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(venue.getName());
			
			//Code to get average vendor rating
			SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
			searchRequestDTO.setVendorId(venue.getVenueId());
			Double rating = reviewCommentDAO.getAverageRatingOfVendor(searchRequestDTO);
			searchResponseDTO.setAverageRating(rating);
			//code ends here 
			
			searchResponseDTO.setLocality(venue.getLocality().getDescription());
			searchResponseDTO.setMainImagerURL(venue.getAttachments().get(0).getImageURL());
			searchResponseDTO.setMaxCapacity(venue.getMaxCapacity());
			searchResponseDTO.setMinCapacity(venue.getMinCapacity());
			for(VenueAmenities venueAmenities : venue.getVenueamenities()){
				if(StringUtils.isNotEmpty(venueAmenities.getMinVegCost())){
					searchResponseDTO.setStartingPrice(venueAmenities.getMinVegCost());
				}
			}
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchVenueDetails(Long cityId, String name) {
		Venue venue = venueDAO.fetchVenueDetails(cityId, name);
		List<TabResponseDTO> serviceList = new ArrayList<TabResponseDTO>();
		List<TabResponseDTO> amenitiesList = new ArrayList<TabResponseDTO>();
		List<TabResponseDTO> roomList = new ArrayList<TabResponseDTO>();
		List<AttachmentResponseDTO> attachmentList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> amenityDetailTabMap = new HashMap<String, List<TabResponseDTO>>();
		Map<String, TabResponseDTO> inHouseOfferingsTabMap = new HashMap<String, TabResponseDTO>();
		List<AttachmentResponseDTO> menuImageList = new ArrayList<AttachmentResponseDTO>();
		Map<String, List<TabResponseDTO>> serviceAmenityTabMap = new HashMap<String, List<TabResponseDTO>>();
		List<VenuePackageDTO> venuePackageDTOs = new ArrayList<VenuePackageDTO>();
		List<String> policiList  = new ArrayList<String>();
		Map<String, String> keyHighlighs = new LinkedHashMap<String, String>();
		Map<String, String> additionalServices = new HashMap<String, String>();

		Address address = venue.getAddresses().get(0);
		for(Attachment attachment : venue.getAttachments()){
			AttachmentResponseDTO attachmentDTO = new AttachmentResponseDTO(attachment.getImageURL(), attachment.getHelpText());
			if(StringUtils.isNotBlank(attachment.getAttachmentType()) && attachment.getAttachmentType().equalsIgnoreCase("MENU")){
				menuImageList.add(attachmentDTO);
			}
			else{
				attachmentList.add(attachmentDTO);
			}
		}
		for(VenuePackages venuePackage : venue.getVenuePackages()){
			VenuePackageDTO venuePackageDTO = new VenuePackageDTO();
			Map<String, String> packageItems = new LinkedHashMap<String, String>();
			packageItems.put("welcome drinks",venuePackage.getWelcomeDrinks());
			packageItems.put("soups",venuePackage.getSoups());
			packageItems.put("salads",venuePackage.getSalads());
			packageItems.put("veg starters",venuePackage.getVegStarters());
			packageItems.put("non-veg starters",venuePackage.getNonVegStarters());
			packageItems.put("veg main course",venuePackage.getVegMainCourse());
			packageItems.put("non-veg main course",venuePackage.getNonvegMainCourse());
			packageItems.put("raita",venuePackage.getRaita());
			packageItems.put("dal",venuePackage.getDal());
			packageItems.put("rice/biryani",venuePackage.getRiceBiryani());
			packageItems.put("assorted breads",venuePackage.getAssortedBreads());
			packageItems.put("deserts",venuePackage.getDeserts());
			venuePackageDTO.setPackagePrice(venuePackage.getPackagePrice());
			venuePackageDTO.setPackageItems(packageItems);
			venuePackageDTO.setPackageType(venuePackage.getPackageType());
			venuePackageDTOs.add(venuePackageDTO);
		}
		for(VenueAmenities venueAmenities : venue.getVenueamenities()){
			TabResponseDTO amenityDTO = new TabResponseDTO();
			amenityDTO.setName(venueAmenities.getAmenitiesId().getDescription());
			if(StringUtils.isNotEmpty(venueAmenities.getMinVegCost())){
				keyHighlighs.put("Veg", venueAmenities.getMinVegCost());
				if(StringUtils.isNotEmpty(venueAmenities.getMinNonVegCost())){
					keyHighlighs.put("Non-Veg", venueAmenities.getMinNonVegCost());
				}
				else{
					keyHighlighs.put("Non-Veg", "-");
				}
					
			}
			amenitiesList.add(amenityDTO);
			if("INHOUSECATERING".equalsIgnoreCase(venueAmenities.getAmenitiesId().getAmenityType())){
				TabResponseDTO houseCaterDTO = new TabResponseDTO();
				houseCaterDTO.setMinVegPrice(venueAmenities.getMinVegCost());
				houseCaterDTO.setMinNonVegPrice(venueAmenities.getMinNonVegCost());
				houseCaterDTO.setMinCapacity(venueAmenities.getMinAccomodation());
				houseCaterDTO.setMaxCapacity(venueAmenities.getMaxAccomodationCapacity());
				houseCaterDTO.setAttachments(menuImageList);
				inHouseOfferingsTabMap.put(venueAmenities.getAmenitiesId().getDescription(), houseCaterDTO);
			}
			else if("INHOUSEDECOR".equalsIgnoreCase(venueAmenities.getAmenitiesId().getAmenityType())){
				TabResponseDTO houseDecorDTO = new TabResponseDTO();
				houseDecorDTO.setMinCost(venueAmenities.getMinCost());
				houseDecorDTO.setTentingAvlbl(venueAmenities.getTentingAvlbl());
				houseDecorDTO.setFloralAvlbl(venueAmenities.getFloralAvlbl());
				houseDecorDTO.setLightingAvlbl(venueAmenities.getLightingAvlbl());
				houseDecorDTO.setBalloonsAvlbl(venueAmenities.getBalloonsAvlbl());
				houseDecorDTO.setCandlesAvlbl(venueAmenities.getCandlesAvlbl());
				inHouseOfferingsTabMap.put(venueAmenities.getAmenitiesId().getDescription(), houseDecorDTO);
			}else if(venueAmenities.getAmenitiesId().getIsDetailedAmenity()){
				if(amenityDetailTabMap.containsKey(venueAmenities.getAmenitiesId().getDescription())){
					List<TabResponseDTO> tabList = amenityDetailTabMap.get(venueAmenities.getAmenitiesId().getDescription());
					TabResponseDTO venueAmenityDTO = new TabResponseDTO();
					venueAmenityDTO.setAmenityName(venueAmenities.getAmenityName());
					venueAmenityDTO.setMinCapacity(venueAmenities.getMinAccomodation());
					venueAmenityDTO.setMaxCapacity(venueAmenities.getMaxAccomodationCapacity());
					venueAmenityDTO.setMinCost(venueAmenities.getMinCost());
					tabList.add(venueAmenityDTO);
				}
				else{
					List<TabResponseDTO> tabList = new ArrayList<TabResponseDTO>();
					TabResponseDTO venueAmenityDTO = new TabResponseDTO();
					venueAmenityDTO.setAmenityName(venueAmenities.getAmenityName());
					venueAmenityDTO.setMinCapacity(venueAmenities.getMinAccomodation());
					venueAmenityDTO.setMaxCapacity(venueAmenities.getMaxAccomodationCapacity());
					venueAmenityDTO.setMinCost(venueAmenities.getMinCost());
					tabList.add(venueAmenityDTO);
					amenityDetailTabMap.put(venueAmenities.getAmenitiesId().getDescription(), tabList);
				}
			}
			
		}
		for(EntityServices venueService : venue.getVenueServices()){
			TabResponseDTO serviceDTO = new TabResponseDTO();
			String serviceName = venueService.getServiceId().getTabDataName();
			String mapValue = venueService.getMinCost() != null ? venueService.getMinCost().toString():"";
			if(venueService.getServiceId().getIsKeyHighlight()){
				keyHighlighs.put(serviceName, mapValue);
			}
			serviceDTO.setName(serviceName);
			serviceList.add(serviceDTO);
		}
		for(AdditionalEntityServices additionalVenueService : venue.getAdditionalVenueServices()){
			String serviceName = additionalVenueService.getAdditionalServiceId().getTabDataName();
			String mapValue = additionalVenueService.getMinCost() != null ? additionalVenueService.getMinCost().toString():"";
			additionalServices.put(serviceName, mapValue);
		}
		for(VenueRooms room : venue.getVenueRooms()){
			TabResponseDTO roomDTO = new TabResponseDTO(room.getRoomId().getDescription(), room.getAcAvailability(), room.getFridgeAvailability(), room.getLockerAvailability(), room.getLedAvailability(), room.getAttachedBathroomAvailability(), room.getHotWaterAvailability(), room.getMinCost());
			roomList.add(roomDTO);
		}
		
		if(!CollectionUtils.isEmpty(serviceList)){
			serviceAmenityTabMap.put("Services", serviceList);
		}
		if(!CollectionUtils.isEmpty(amenitiesList)){
			serviceAmenityTabMap.put("Amenities", amenitiesList);
		}
		if(!CollectionUtils.isEmpty(roomList)){
			amenityDetailTabMap.put("Rooms", roomList);
		}
		if(StringUtils.isNotBlank(venue.getPolicies())){
			StringTokenizer strTokenizer = new StringTokenizer(venue.getPolicies(), "<br>");
			while(strTokenizer.hasMoreElements()){
				policiList.add((String) strTokenizer.nextElement());
			}
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
		detailResponseDTO.setEmail(address.getEmail());
		if(!CollectionUtils.isEmpty(serviceAmenityTabMap)){
			detailResponseDTO.setServiceAmenityTabMap(serviceAmenityTabMap);
		}
		if(!CollectionUtils.isEmpty(inHouseOfferingsTabMap)){
			detailResponseDTO.setInHouseOfferingsTabMap(inHouseOfferingsTabMap);
		}
		if(!CollectionUtils.isEmpty(amenityDetailTabMap)){
			detailResponseDTO.setAmenityDetailsTabMap(amenityDetailTabMap);
		}
		if(!CollectionUtils.isEmpty(keyHighlighs)){
			detailResponseDTO.setKeyHighlighs(keyHighlighs);
		}
		if(!CollectionUtils.isEmpty(additionalServices)){
			detailResponseDTO.setAdditionalServices(additionalServices);
		}
		detailResponseDTO.setPolicies(policiList);
		detailResponseDTO.setAttachments(attachmentList);
		detailResponseDTO.setServingSince(venue.getServingSince());
		detailResponseDTO.setStartingFrom(venue.getStartingPrice());
		if(!CollectionUtils.isEmpty(venuePackageDTOs)){
			detailResponseDTO.setVenuePackages(venuePackageDTOs);
		}
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
		List<FilterResponseDTO> capacity = new ArrayList<FilterResponseDTO>();

		List<lepartycious.models.Service> serviceList = commonDAO.getServiceFilters("VENUE", "SERVICE");
		List<Amenities> amenityList = commonDAO.getAmenities("amenity");
		List<Room> roomList = commonDAO.getRooms("room");
		City city = cityDAO.getCityById(cityId);
		List<Locality> localityList = city.getLocalities();
		List<Filter> establishmentList = commonDAO.getRequiredFilters("VENUE", "ESTABLISHMENT");
		List<Filter> eventList = commonDAO.getRequiredFilters("ALL", "EVENT");
		List<Filter> priceRangeList = commonDAO.getRequiredFilters("VENUE", "PRICE");
		List<Filter> capacityFilters = commonDAO.getRequiredFilters("VENUE", "CAPACITY");

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
		for(Filter capFilter : capacityFilters){
			FilterResponseDTO filter = new FilterResponseDTO(capFilter.getFilterName(), capFilter.getFilterType(), capFilter.getFilterid());
			capacity.add(filter);
		}
		
		filterResponseWrapperDTO.setServices(services);
		filterResponseWrapperDTO.setAmenities(amenities);
		filterResponseWrapperDTO.setRooms(rooms);
		filterResponseWrapperDTO.setLocalities(localities);
		filterResponseWrapperDTO.setEstablishments(establishments);
		filterResponseWrapperDTO.setPriceRange(prices);
		filterResponseWrapperDTO.setEventType(events);
		filterResponseWrapperDTO.setCapacity(capacity);
		return filterResponseWrapperDTO;
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(Long cityId) {
		List<SearchResponseDTO> recommendationList = new ArrayList<SearchResponseDTO>();
		List<Venue> venueList = venueDAO.fetchRecomendations(cityId);
		for(Venue venue : venueList){
			if(CollectionUtils.isEmpty(venue.getAttachments())){
				List<Attachment> defaultImageList = commonService.getDefaultImageList();
				venue.setAttachments(defaultImageList);;
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(venue.getName());
			searchResponseDTO.setLocality(venue.getLocality().getDescription());
			searchResponseDTO.setStartingPrice(venue.getStartingPrice());
			searchResponseDTO.setMainImagerURL(venue.getAttachments().get(0).getImageURL());
			recommendationList.add(searchResponseDTO);
		}
		return recommendationList;
	}

}
