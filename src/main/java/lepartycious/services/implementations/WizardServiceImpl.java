package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lepartycious.Enums.AmenityCategoryEnum;
import lepartycious.Enums.ServiceCategoryEnum;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.VenueDAO;
import lepartycious.daos.WizardDAO;
import lepartycious.dtos.requestDTOs.AdditionalEntityServicesDTO;
import lepartycious.dtos.requestDTOs.AddressDTO;
import lepartycious.dtos.requestDTOs.AttachmentDTO;
import lepartycious.dtos.requestDTOs.EntityFiltersDTO;
import lepartycious.dtos.requestDTOs.EntityServicesDTO;
import lepartycious.dtos.requestDTOs.VenueAmenitiesDTO;
import lepartycious.dtos.requestDTOs.VenueDTO;
import lepartycious.dtos.requestDTOs.VenueRoomsDTO;
import lepartycious.dtos.responseDTOs.FilterResponseDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;
import lepartycious.dtos.responseDTOs.TabResponseDTO;
import lepartycious.dtos.responseDTOs.WizardDTO;
import lepartycious.models.AdditionalEntityServices;
import lepartycious.models.AdditionalService;
import lepartycious.models.Address;
import lepartycious.models.Amenities;
import lepartycious.models.Attachment;
import lepartycious.models.City;
import lepartycious.models.EntityFilters;
import lepartycious.models.EntityServices;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.models.Room;
import lepartycious.models.Service;
import lepartycious.models.User;
import lepartycious.models.Venue;
import lepartycious.models.VenueAmenities;
import lepartycious.models.VenueRooms;
import lepartycious.services.SecurityUserDetailsService;
import lepartycious.services.WizardService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class WizardServiceImpl implements WizardService {

	@Autowired
	private WizardDAO wizardDAO;

	@Autowired
	private CommonDAO commonDAO;

	@Autowired
	private SecurityUserDetailsService userService;

	@Autowired
	private VenueDAO venueDAO;

	@Override
	public void saveVenueDetails(VenueDTO venueDTO) {
		Venue venue = new Venue();
		List<Address> addresses = new ArrayList<Address>();
		List<VenueAmenities> venueAmenities = new ArrayList<>();
		List<Attachment> attachments = new ArrayList<Attachment>();
		List<EntityServices> venueServices = new ArrayList<EntityServices>();
		List<VenueRooms> venueRooms = new ArrayList<VenueRooms>();
		List<EntityFilters> venueFilters = new ArrayList<EntityFilters>();

		List<VenueAmenitiesDTO> venueAenitiesDTOList = new ArrayList<VenueAmenitiesDTO>();
		venueAenitiesDTOList.addAll(venueDTO.getEventAreaAmenities());
		venueAenitiesDTOList.addAll(venueDTO.getCaterDecorAmenities());
		List<EntityServicesDTO> venueServiceDTOList = new ArrayList<EntityServicesDTO>();
		venueServiceDTOList.addAll(venueDTO.getAdditionalVenueServices());
		List<EntityFiltersDTO> entityFilterDTOList = new ArrayList<EntityFiltersDTO>();
		entityFilterDTOList.addAll(venueDTO.getVenueEstTypeFilters());

		City city = (City) wizardDAO.getObjectById(venueDTO.getCity(), City.class);
		Locality locality = (Locality) wizardDAO.getObjectById(venueDTO.getLocality(), Locality.class);
		
		venue.setName(venueDTO.getVenueName());
		venue.setDescription(venueDTO.getVenueDescription());
		venue.setBookingPolicy(venueDTO.getBookingPolicy());
		venue.setPolicies(venueDTO.getRefundPolicy());
		venue.setType(venueDTO.getVenueType());
		venue.setServingSince(venueDTO.getServingSince());
		venue.setIsActive(false);
		venue.setCity(city);
		venue.setLocality(locality);
		venue.setInfoProviderContact(venueDTO.getInformationProviderContactNo());
		venue.setInfoProviderName(venueDTO.getInformationProviderName());
		venue.setWebsiteName(venueDTO.getWebsite());
		venue.setUser(venueDTO.getUserId());
		Long entityId = wizardDAO.save(venue);

		for(AttachmentDTO attachmentDTO : venueDTO.getVendorAttachments()){
			Attachment attachment = new Attachment(attachmentDTO.getName(), attachmentDTO.getImageURL(), 
					attachmentDTO.getHelpText(), "VENDOR", attachmentDTO.getImageData());
			attachment.setEntityId(entityId);
			attachments.add(attachment);
		}

		for(AttachmentDTO attachmentDTO : venueDTO.getMenuAttachments()){
			Attachment attachment = new Attachment(attachmentDTO.getName(), attachmentDTO.getImageURL(), 
					attachmentDTO.getHelpText(), "MENU", attachmentDTO.getImageData());
			attachment.setEntityId(entityId);
			attachments.add(attachment);
		}

		for(AddressDTO addressDTO : venueDTO.getAddresses()){
			Address address = new Address(addressDTO.getAddressLine1(), addressDTO.getAddressLine2(), addressDTO.getCity(), 
					addressDTO.getEmail(), addressDTO.getPincode(), addressDTO.getPrimaryPhone(), addressDTO.getSecondaryPhone());
			address.setLatitude(addressDTO.getLatitude());
			address.setLongitude(addressDTO.getLongitude());
			address.setEntityId(entityId);
			addresses.add(address);
		}

		for(VenueAmenitiesDTO venueAmenitiesDTO : venueAenitiesDTOList){
			Amenities amenity = (Amenities) wizardDAO.getObjectById(venueAmenitiesDTO.getAmenitiesId(), Amenities.class);
			VenueAmenities venueAmenity = new VenueAmenities(amenity, venueAmenitiesDTO.getMinCost(), 
					venueAmenitiesDTO.getMaxCost(), venueAmenitiesDTO.getAmenityName(), venueAmenitiesDTO.getMaxAccomodationCapacity(),
					venueAmenitiesDTO.getMinAccomodation(), venueAmenitiesDTO.getIsFullyAc(), venueAmenitiesDTO.getAdditionalInfo(), 
					venueAmenitiesDTO.getMinVegCost(), venueAmenitiesDTO.getMaxVegCost(), venueAmenitiesDTO.getMinNonVegCost(), venueAmenitiesDTO.getMaxNonVegCost());
			venueAmenity.setVenueId(venue);
			venueAmenities.add(venueAmenity);
		}

		for(EntityServicesDTO entityServicesDTO : venueServiceDTOList){
			lepartycious.models.Service service = (lepartycious.models.Service) wizardDAO.getObjectById(entityServicesDTO.getServiceId(), lepartycious.models.Service.class);
			EntityServices venueService = new EntityServices(service, entityServicesDTO.getMinCost(), entityServicesDTO.getMaxCost(), entityServicesDTO.getMaxCapacity());
			venueService.setEntityId(entityId);
			venueServices.add(venueService);
		}

		for(Long serviceId : venueDTO.getBasicVenueServices()){
			lepartycious.models.Service service = (lepartycious.models.Service) wizardDAO.getObjectById(serviceId, lepartycious.models.Service.class);
			EntityServices venueService = new EntityServices();
			venueService.setServiceId(service);
			venueService.setEntityId(entityId);
			venueServices.add(venueService);
		}

		for(Long serviceId : venueDTO.getPolicyVenueServices()){
			lepartycious.models.Service service = (lepartycious.models.Service) wizardDAO.getObjectById(serviceId, lepartycious.models.Service.class);
			EntityServices venueService = new EntityServices();
			venueService.setServiceId(service);
			venueService.setEntityId(entityId);
			venueServices.add(venueService);
		}

		for(VenueRoomsDTO venueRoomsDTO : venueDTO.getVenueRooms()){
			Room room = (Room) wizardDAO.getObjectById(venueRoomsDTO.getRoomId(), Room.class);
			VenueRooms venueRoom = new VenueRooms(room, venueRoomsDTO.getMinCost(), venueRoomsDTO.getAcAvailability(), venueRoomsDTO.getFridgeAvailability(),
					venueRoomsDTO.getLockerAvailability(), venueRoomsDTO.getLedAvailability(), venueRoomsDTO.getAttachedBathroomAvailability(),
					venueRoomsDTO.getHotWaterAvailability(), venueRoomsDTO.getNumberOfRooms());
			venueRoom.setVenueId(venue);
			venueRooms.add(venueRoom);
		}

		for(EntityFiltersDTO entityFilterDTO : entityFilterDTOList){
			Filter filter = (Filter) wizardDAO.getObjectById(entityFilterDTO.getFilterId(), Filter.class);
			EntityFilters entityFilter = new EntityFilters();
			entityFilter.setFilterId(filter);
			entityFilter.setEntityId(entityId);
			venueFilters.add(entityFilter);
			venue.setType(filter.getFilterName());
		}
		Venue savedVenue = (Venue) wizardDAO.getObjectById(entityId, Venue.class);
		savedVenue.setAddresses(addresses);
		savedVenue.setAttachments(attachments);
		savedVenue.setVenueamenities(venueAmenities);
		savedVenue.setVenueRooms(venueRooms);
		savedVenue.setVenueServices(venueServices);
		savedVenue.setVenueFilters(venueFilters);
		wizardDAO.update(savedVenue);
	}

	@Override
	public LookUpDTO getVenuelookUp() {
		LookUpDTO data = new LookUpDTO();
		List<VenueRoomsDTO> roomLookUpMap = new ArrayList<>();
		List<FilterResponseDTO> typeLookUp = new ArrayList<>();
		List<VenueAmenitiesDTO> eventAreaAmenityLookUpMap = new ArrayList<>();
		List<EntityServicesDTO> basicServiceLookUpMap = new ArrayList<>();
		List<EntityServicesDTO> additionalServiceLookUpMap = new ArrayList<>();
		List<EntityServicesDTO> policyServicelookUp = new ArrayList<>();
		List<VenueAmenitiesDTO> cateringDecorAmenityLookUp = new ArrayList<>();
		List<EntityServicesDTO> cuisinesLookUp = new ArrayList<>();

		List<Room> roomList = commonDAO.getRooms("room");;
		List<Filter> estFilterList = commonDAO.getRequiredFilters("VENUE", "ESTABLISHMENT");
		List<Service> serviceList = commonDAO.getServiceFilters("VENUE", "SERVICE", false);
		List<Amenities> amenityList = commonDAO.getAmenities(false);

		for(Room room : roomList){
			VenueRoomsDTO venueRoomsDTO = new VenueRoomsDTO();
			venueRoomsDTO.setRoomId(room.getRoomId());
			venueRoomsDTO.setRoomType(room.getRoomType());
			roomLookUpMap.add(venueRoomsDTO);
		}

		for(Filter filter : estFilterList){
			FilterResponseDTO filterResponseDTO = new FilterResponseDTO();
			filterResponseDTO.setId(filter.getFilterid());
			filterResponseDTO.setName(filter.getFilterName());
			typeLookUp.add(filterResponseDTO);
		}

		for(Service service : serviceList){
			EntityServicesDTO entityServicesDTO = new EntityServicesDTO();
			entityServicesDTO.setServiceId(service.getServiceId());
			entityServicesDTO.setTabDataName(service.getTabDataName());
			if(ServiceCategoryEnum.BASIC.toString().equalsIgnoreCase(service.getCategory())){
				basicServiceLookUpMap.add(entityServicesDTO);
			}
			else if(ServiceCategoryEnum.ADITIONAL.toString().equalsIgnoreCase(service.getCategory())){
				additionalServiceLookUpMap.add(entityServicesDTO);
			}
			else if(ServiceCategoryEnum.POLICY.toString().equalsIgnoreCase(service.getCategory())){
				policyServicelookUp.add(entityServicesDTO);
			}
			else if(ServiceCategoryEnum.CUISINE.toString().equalsIgnoreCase(service.getCategory())){
				cuisinesLookUp.add(entityServicesDTO);
			}
		}

		for(Amenities amenity : amenityList){
			VenueAmenitiesDTO venueAmenitiesDTO = new VenueAmenitiesDTO();
			venueAmenitiesDTO.setAmenitiesId(amenity.getAmenitiesId());
			venueAmenitiesDTO.setAmenityType(amenity.getAmenityType());
			if(AmenityCategoryEnum.EVENT.toString().equalsIgnoreCase(amenity.getCategory())){
				eventAreaAmenityLookUpMap.add(venueAmenitiesDTO);
			}
			else if(AmenityCategoryEnum.CATERDECOR.toString().equalsIgnoreCase(amenity.getCategory())){
				cateringDecorAmenityLookUp.add(venueAmenitiesDTO);
			}
		}
		data.setAdditionalServiceLookUpMap(additionalServiceLookUpMap);
		data.setBasicServiceLookUpMap(basicServiceLookUpMap);
		data.setCateringDecorAmenityLookUp(cateringDecorAmenityLookUp);
		data.setEventAreaAmenityLookUpMap(eventAreaAmenityLookUpMap);
		data.setPolicyServiceLookUp(policyServicelookUp);
		data.setCuisinesLookUp(cuisinesLookUp);
		data.setRoomLookUpMap(roomLookUpMap);
		data.setTypeLookUp(typeLookUp);
		return data;
	}

	@Override
	public Map<Long, String> getLocalities(Long cityId) throws Exception{
		Map<Long, String> localityMap = new HashMap<Long, String>();
		List<Locality> localities = commonDAO.getLocalities(cityId);
		for(Locality locality : localities){
			localityMap.put(locality.getLocalityId(), locality.getName());
		}
		return localityMap;
	}

	@Override
	public VenueDTO loadVenueWizard(String wizardName)
			throws Exception {
		VenueDTO venueDTO = null;
		Venue venue = venueDAO.loadVenueByName(wizardName);
		if(venue != null){
			venueDTO = populateVenueDetails(venue);
		}
		else {
			if(StringUtils.isNotBlank(wizardName)){
				venueDTO = new VenueDTO();
				venueDTO.setVenueName(wizardName);
			}
			else{
				throw new Exception("No Venue is associated with User");
			}

		}
		return venueDTO;
	}

	private VenueDTO populateVenueDetails(Venue venue) {

		List<AddressDTO> addressesDTO = new ArrayList<AddressDTO>();
		List<VenueRoomsDTO> venueRoomsDTO = new ArrayList<VenueRoomsDTO>();
		List<AttachmentDTO> vendorAttachments = new ArrayList<AttachmentDTO>();
		List<AttachmentDTO> menuAttachments = new ArrayList<AttachmentDTO>();
		List<EntityFiltersDTO> venueEstTypeFilters = new ArrayList<EntityFiltersDTO>();
		List<Long> basicVenueServices = new ArrayList<Long>();
		List<Long> basicCateringServices = new ArrayList<Long>();
		List<Long> cuisines = new ArrayList<Long>();
		List<Long> policyVenueServices = new ArrayList<Long>();
		List<EntityServicesDTO> additionalVenueServices = new ArrayList<EntityServicesDTO>();
		List<VenueAmenitiesDTO> caterDecorAmenities = new ArrayList<VenueAmenitiesDTO>();
		List<VenueAmenitiesDTO> eventAreaAmenities = new ArrayList<VenueAmenitiesDTO>();

		VenueDTO venueDTO = new VenueDTO(venue.getName(), venue.getDescription(), venue.getType(), venue.getCity().getCityId(), venue.getLocality().getLocalityId(),
				venue.getPriority(), venue.getStartingPrice(), venue.getMaxCapacity(), venue.getBookingPolicy(), venue.getPolicies(), venue.getServingSince(),
				venue.getMinCapacity(), venue.getInfoProviderContact(), venue.getInfoProviderName(), venue.getWebsiteName());
		List<Address> addresses = venue.getAddresses();
		for(Address address : addresses){
			AddressDTO addressDTO = new AddressDTO(address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getState(), address.getEmail(),
					address.getPincode(), address.getPrimaryPhone(), address.getSecondaryPhone(), address.getLatitude(), address.getLongitude());
			addressesDTO.add(addressDTO);
		}
		for(VenueRooms venueRoom : venue.getVenueRooms()){
			VenueRoomsDTO venueRoomDTO = new VenueRoomsDTO(venueRoom.getRoomId().getRoomId(), venueRoom.getMinCost(), venueRoom.getAcAvailability(), venueRoom.getFridgeAvailability(), 
					venueRoom.getLockerAvailability(), venueRoom.getLedAvailability(), venueRoom.getAttachedBathroomAvailability(), venueRoom.getHotWaterAvailability(), 
					venueRoom.getNumberOfRooms(), venueRoom.getRoomId().getRoomType());
			venueRoomsDTO.add(venueRoomDTO);
		}
		for(Attachment attachment : venue.getAttachments()){
			AttachmentDTO attach = new AttachmentDTO(attachment.getName(), attachment.getImageURL(), attachment.getHelpText(), attachment.getAttachmentType(), 
					attachment.getImageData());
			if("VENDOR".equalsIgnoreCase(attachment.getAttachmentType())){
				vendorAttachments.add(attach);
			}
			else{
				menuAttachments.add(attach);
			}
		}
		for(EntityFilters entityFilter : venue.getVenueFilters()){
			Filter filter = entityFilter.getFilterId();
			if("ESTABLISHMENT".equalsIgnoreCase(filter.getFilterType())){
				EntityFiltersDTO entityFilterDTO = new EntityFiltersDTO();
				entityFilterDTO.setFilterId(filter.getFilterid());
				venueEstTypeFilters.add(entityFilterDTO);
			}
		}
		for(EntityServices entityService : venue.getVenueServices()){
			Service service = entityService.getServiceId();
			if(ServiceCategoryEnum.BASIC.toString().equalsIgnoreCase(service.getCategory())){
				basicVenueServices.add(service.getServiceId());
			}
			else if(ServiceCategoryEnum.ADITIONAL.toString().equalsIgnoreCase(service.getCategory())){
				EntityServicesDTO entityServicesDTO = new EntityServicesDTO(service.getServiceId(), entityService.getMinCost(), entityService.getMaxCost(),
						entityService.getMaxCapacity(), service.getTabDataName());
				additionalVenueServices.add(entityServicesDTO);
			}
			else if(ServiceCategoryEnum.POLICY.toString().equalsIgnoreCase(service.getCategory())){
				policyVenueServices.add(service.getServiceId());
			}
			else if(ServiceCategoryEnum.CATERINGSERVICE.toString().equalsIgnoreCase(service.getCategory())){
				basicCateringServices.add(service.getServiceId());
			}
			else if(ServiceCategoryEnum.CUISINE.toString().equalsIgnoreCase(service.getCategory())){
				cuisines.add(service.getServiceId());
			}
		}
		for(VenueAmenities venueAmenity : venue.getVenueamenities()){
			Amenities amenity = venueAmenity.getAmenitiesId();
			VenueAmenitiesDTO venueAmenitiesDTO = new VenueAmenitiesDTO(amenity.getAmenitiesId(), venueAmenity.getMinCost(), venueAmenity.getMaxCost(),
					venueAmenity.getAmenityName(), venueAmenity.getMaxAccomodationCapacity(), venueAmenity.getMinAccomodation(), venueAmenity.getIsFullyAc(),
					venueAmenity.getAdditionalInfo(), venueAmenity.getMinVegCost(), venueAmenity.getMaxVegCost(), venueAmenity.getMinNonVegCost(),
					venueAmenity.getMaxNonVegCost(), amenity.getAmenityType());
			if(AmenityCategoryEnum.EVENT.toString().equalsIgnoreCase(amenity.getCategory())){
				eventAreaAmenities.add(venueAmenitiesDTO);
			}
			else if(AmenityCategoryEnum.CATERDECOR.toString().equalsIgnoreCase(amenity.getCategory())){
				caterDecorAmenities.add(venueAmenitiesDTO);
			}
		}

		venueDTO.setVenueRooms(venueRoomsDTO);
		venueDTO.setAddresses(addressesDTO);
		venueDTO.setVendorAttachments(vendorAttachments);
		venueDTO.setMenuAttachments(menuAttachments);
		venueDTO.setVenueEstTypeFilters(venueEstTypeFilters);
		venueDTO.setBasicVenueServices(basicVenueServices);
		venueDTO.setBasicCateringServices(basicCateringServices);
		venueDTO.setPolicyVenueServices(policyVenueServices);
		venueDTO.setCuisines(cuisines);
		venueDTO.setAdditionalVenueServices(additionalVenueServices);
		venueDTO.setEventAreaAmenities(eventAreaAmenities);
		venueDTO.setCaterDecorAmenities(caterDecorAmenities);
		return venueDTO;
	}

}
