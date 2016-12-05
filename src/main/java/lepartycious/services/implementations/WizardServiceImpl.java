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
import lepartycious.dtos.requestDTOs.EntityServicesDTO;
import lepartycious.dtos.requestDTOs.VenueAmenitiesDTO;
import lepartycious.dtos.requestDTOs.VenueDTO;
import lepartycious.dtos.requestDTOs.VenueRoomsDTO;
import lepartycious.dtos.responseDTOs.FilterResponseDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;
import lepartycious.dtos.responseDTOs.WizardDTO;
import lepartycious.models.AdditionalEntityServices;
import lepartycious.models.AdditionalService;
import lepartycious.models.Address;
import lepartycious.models.Amenities;
import lepartycious.models.Attachment;
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
		List<AdditionalEntityServices> additionalVenueServices = new ArrayList<AdditionalEntityServices>();
		List<VenueRooms> venueRooms = new ArrayList<VenueRooms>();
		
		venue.setName(venueDTO.getVenueName());
		venue.setDescription(venueDTO.getVenueDescription());
		venue.setBookingPolicy(venueDTO.getBookingPolicy());
		venue.setPolicies(venueDTO.getRefundPolicy());
		venue.setType(venueDTO.getVenueType());
		venue.setServingSince(venueDTO.getServingSince());
		
		for(AttachmentDTO attachmentDTO : venueDTO.getVendorAttachments()){
			Attachment attachment = new Attachment(attachmentDTO.getName(), attachmentDTO.getImageURL(), 
					attachmentDTO.getHelpText(), "VENDOR", attachmentDTO.getImageData());
			attachments.add(attachment);
		}
		
		for(AttachmentDTO attachmentDTO : venueDTO.getMenuAttachments()){
			Attachment attachment = new Attachment(attachmentDTO.getName(), attachmentDTO.getImageURL(), 
					attachmentDTO.getHelpText(), "MENU", attachmentDTO.getImageData());
			attachments.add(attachment);
		}
		
		for(AddressDTO addressDTO : venueDTO.getAddresses()){
			Address address = new Address(addressDTO.getAddressLine1(), addressDTO.getAddressLine2(), addressDTO.getCity(), 
					addressDTO.getEmail(), addressDTO.getPincode(), addressDTO.getPrimaryPhone(), addressDTO.getSecondaryPhone());
			address.setLatitude(addressDTO.getLatitude());
			address.setLongitude(addressDTO.getLongitude());
			addresses.add(address);
		}
		
		for(VenueAmenitiesDTO venueAmenitiesDTO : venueDTO.getVenueamenities()){
			Amenities amenity = (Amenities) wizardDAO.getObjectById(venueAmenitiesDTO.getAmenitiesId(), Amenities.class);
			VenueAmenities venueAmenity = new VenueAmenities(amenity, venueAmenitiesDTO.getMinCost(), 
					venueAmenitiesDTO.getMaxCost(), venueAmenitiesDTO.getAmenityName(), venueAmenitiesDTO.getMaxAccomodationCapacity(),
					venueAmenitiesDTO.getMinAccomodation(), venueAmenitiesDTO.getIsFullyAc(), venueAmenitiesDTO.getAdditionalInfo(), 
					venueAmenitiesDTO.getMinVegCost(), venueAmenitiesDTO.getMaxVegCost(), venueAmenitiesDTO.getMinNonVegCost(), venueAmenitiesDTO.getMaxNonVegCost());
			venueAmenities.add(venueAmenity);
		}
		
		for(EntityServicesDTO entityServicesDTO : venueDTO.getVenueServices()){
			lepartycious.models.Service service = (lepartycious.models.Service) wizardDAO.getObjectById(entityServicesDTO.getServiceId(), lepartycious.models.Service.class);
			EntityServices venueService = new EntityServices(service, entityServicesDTO.getMinCost(), entityServicesDTO.getMaxCost(), entityServicesDTO.getMaxCapacity());
			venueServices.add(venueService);
		}
		
		for(VenueRoomsDTO venueRoomsDTO : venueDTO.getVenueRooms()){
			Room room = (Room) wizardDAO.getObjectById(venueRoomsDTO.getRoomId(), Room.class);
			VenueRooms venueRoom = new VenueRooms(room, venueRoomsDTO.getMinCost(), venueRoomsDTO.getAcAvailability(), venueRoomsDTO.getFridgeAvailability(),
					venueRoomsDTO.getLockerAvailability(), venueRoomsDTO.getLedAvailability(), venueRoomsDTO.getAttachedBathroomAvailability(),
					venueRoomsDTO.getHotWaterAvailability(), venueRoomsDTO.getNumberOfRooms());
			venueRooms.add(venueRoom);
		}
		
		for(AdditionalEntityServicesDTO additionalEntityServicesDTO : venueDTO.getAdditionalVenueServices()){
			AdditionalService additionalService = (AdditionalService) wizardDAO.getObjectById(additionalEntityServicesDTO.getAdditionalServiceId(), AdditionalService.class);
			AdditionalEntityServices additionalEntityServices = new AdditionalEntityServices(additionalService, additionalEntityServicesDTO.getMinCost(),
					additionalEntityServicesDTO.getMaxCost(), additionalEntityServicesDTO.getMaxCapacity());
			additionalVenueServices.add(additionalEntityServices);
		}
		
		venue.setAdditionalVenueServices(additionalVenueServices);
		venue.setAddresses(addresses);
		venue.setAttachments(attachments);
		venue.setIsActive(false);
		venue.setVenueamenities(venueAmenities);
		venue.setVenueRooms(venueRooms);
		venue.setVenueServices(venueServices);
		wizardDAO.save(venue);
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
		data.setPolicyServicelookUp(policyServicelookUp);
		data.setRoomLookUpMap(roomLookUpMap);
		data.setTypeLookUp(typeLookUp);
		return data;
	}

	@Override
	public VenueDTO getVenueDetails(String username) throws Exception{
		User user = userService.loadUserByUsername(username);
		VenueDTO venueDTO = new VenueDTO();
		if(user != null){
			Venue venue = venueDAO.loadVenueByUserId(user.getUserId());
			if(venue != null){
				venueDTO.setVenueName(venue.getName());
				venueDTO.setVenueType(venue.getType());
				//TODO populate all venue related info, based on flag
			}
			else {
				throw new Exception("No Venue is associated with User " + username);
			}
		}
		return venueDTO;
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
	public WizardDTO getWizardDetails(String username) throws Exception {
		User user = userService.loadUserByUsername(username);
		WizardDTO wizardDTO = new WizardDTO();
		if(user != null){
			Venue venue = venueDAO.loadVenueByUserId(user.getUserId());
			if(venue != null){
				wizardDTO.setName(venue.getName());
				wizardDTO.setType(venue.getType());
				//TODO populate all venue related info, based on flag
			}
			else {
				throw new Exception("No Venue is associated with User " + username);
			}
		}
		return wizardDTO;
	}

}
