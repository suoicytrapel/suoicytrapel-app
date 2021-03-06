package lepartycious.wizard.venue;

import java.util.ArrayList;
import java.util.List;

import lepartycious.Enums.AmenityCategoryEnum;
import lepartycious.Enums.ServiceCategoryEnum;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.VenueDAO;
import lepartycious.daos.WizardDAO;
import lepartycious.dtos.requestDTOs.AddressDTO;
import lepartycious.dtos.requestDTOs.AttachmentDTO;
import lepartycious.dtos.requestDTOs.EntityFiltersDTO;
import lepartycious.dtos.requestDTOs.EntityServicesDTO;
import lepartycious.dtos.requestDTOs.VenueAmenitiesDTO;
import lepartycious.dtos.requestDTOs.VenueDTO;
import lepartycious.dtos.requestDTOs.VenueRoomsDTO;
import lepartycious.dtos.responseDTOs.FilterResponseDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;
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
import lepartycious.services.EmailService;
import lepartycious.services.SecurityUserDetailsService;
import lepartycious.util.EmailTemplateUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Service
public class VenueWizardServiceImpl implements VenueWizardService {

	@Autowired
	private WizardDAO wizardDAO;

	@Autowired
	private CommonDAO commonDAO;

	@Autowired
	private SecurityUserDetailsService userService;

	@Autowired
	private VenueDAO venueDAO;
	
	@Autowired
    private EmailService emailService;
	
	@Override
	public void saveVenueDetails(VenueDTO venueDTO) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long venueId = venueDTO.getVenueId();
		Venue venue = null;
		if(venueId <= 0){
			venue = new Venue();
		}
		else{
			venue = (Venue) wizardDAO.getObjectById(venueId, Venue.class);
		}
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
		List<Long> entityFilterList = new ArrayList<Long>();
		entityFilterList.addAll(venueDTO.getVenueEstTypeFilters());

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
		venue.setUser(user.getUserId());
		if(venueId <= 0){
			venueId = wizardDAO.save(venue);
			venue = (Venue) wizardDAO.getObjectById(venueId, Venue.class);
		}
		for(AttachmentDTO attachmentDTO : venueDTO.getVendorAttachments()){
			Attachment attachment = new Attachment(attachmentDTO.getName(), attachmentDTO.getImageURL(), 
					attachmentDTO.getHelpText(), "VENDOR", attachmentDTO.getImageData());
			attachment.setEntityId(venueId);
			attachments.add(attachment);
		}

		for(AttachmentDTO attachmentDTO : venueDTO.getMenuAttachments()){
			Attachment attachment = new Attachment(attachmentDTO.getName(), attachmentDTO.getImageURL(), 
					attachmentDTO.getHelpText(), "MENU", attachmentDTO.getImageData());
			attachment.setEntityId(venueId);
			attachments.add(attachment);
		}

		for(AddressDTO addressDTO : venueDTO.getAddresses()){
			Address address = new Address(addressDTO.getAddressLine1(), addressDTO.getAddressLine2(), addressDTO.getCity(), 
					addressDTO.getEmail(), addressDTO.getPincode(), addressDTO.getPrimaryPhone(), addressDTO.getSecondaryPhone());
			address.setLatitude(addressDTO.getLatitude());
			address.setLongitude(addressDTO.getLongitude());
			address.setEntityId(venueId);
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
			venueService.setEntityId(venueId);
			venueServices.add(venueService);
		}

		for(Long serviceId : venueDTO.getBasicVenueServices()){
			lepartycious.models.Service service = (lepartycious.models.Service) wizardDAO.getObjectById(serviceId, lepartycious.models.Service.class);
			EntityServices venueService = new EntityServices();
			venueService.setServiceId(service);
			venueService.setEntityId(venueId);
			venueServices.add(venueService);
		}

		for(Long serviceId : venueDTO.getPolicyVenueServices()){
			lepartycious.models.Service service = (lepartycious.models.Service) wizardDAO.getObjectById(serviceId, lepartycious.models.Service.class);
			EntityServices venueService = new EntityServices();
			venueService.setServiceId(service);
			venueService.setEntityId(venueId);
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

		for(Long entityFilterId : entityFilterList){
			Filter filter = (Filter) wizardDAO.getObjectById(entityFilterId, Filter.class);
			EntityFilters entityFilter = new EntityFilters();
			entityFilter.setFilterId(filter);
			entityFilter.setEntityId(venueId);
			venueFilters.add(entityFilter);
			venue.setType(filter.getFilterName());
		}
		venue.setAddresses(addresses);
		venue.setAttachments(attachments);
		venue.setVenueamenities(venueAmenities);
		venue.setVenueRooms(venueRooms);
		venue.setVenueServices(venueServices);
		venue.setVenueFilters(venueFilters);
		wizardDAO.update(venue);
		sendNotificationMailToUser(venue, user);
		sendApprovalMailToAdmin(venue, user);
	}

	@Override
	public LookUpDTO getVenuelookUp() {
		LookUpDTO data = new LookUpDTO();
		List<VenueRoomsDTO> roomLookUpMap = new ArrayList<>();
		List<EntityFiltersDTO> estTypeLookUp = new ArrayList<EntityFiltersDTO>();
		List<VenueAmenitiesDTO> eventAreaAmenityLookUpMap = new ArrayList<>();
		List<EntityServicesDTO> basicServiceLookUpMap = new ArrayList<>();
		List<EntityServicesDTO> additionalServiceLookUpMap = new ArrayList<>();
		List<EntityServicesDTO> policyServicelookUp = new ArrayList<>();
		List<VenueAmenitiesDTO> cateringDecorAmenityLookUp = new ArrayList<>();
		List<EntityServicesDTO> cuisinesLookUp = new ArrayList<>();
		List<EntityFiltersDTO> eventTypeLookUp = new ArrayList<EntityFiltersDTO>();

		List<Room> roomList = commonDAO.getRooms("room");;
		List<Filter> estFilterList = commonDAO.getRequiredFilters("VENUE", "ESTABLISHMENT");
		List<Service> serviceList = commonDAO.getServiceFilters("VENUE", "SERVICE", false);
		List<Amenities> amenityList = commonDAO.getAmenities(false);
		List<Filter> eventList = commonDAO.getRequiredFilters("ALL", "EVENT");

		for(Room room : roomList){
			VenueRoomsDTO venueRoomsDTO = new VenueRoomsDTO();
			venueRoomsDTO.setRoomId(room.getRoomId());
			venueRoomsDTO.setRoomType(room.getRoomType());
			roomLookUpMap.add(venueRoomsDTO);
		}

		for(Filter filter : estFilterList){
			EntityFiltersDTO entityFiltersDTO = new EntityFiltersDTO();
			entityFiltersDTO.setFilterId(filter.getFilterid());
			entityFiltersDTO.setFilterName(filter.getFilterName());
			estTypeLookUp.add(entityFiltersDTO);
		}
		
		for(Filter filter : eventList){
			EntityFiltersDTO entityFiltersDTO = new EntityFiltersDTO();
			entityFiltersDTO.setFilterId(filter.getFilterid());
			entityFiltersDTO.setFilterName(filter.getFilterName());
			eventTypeLookUp.add(entityFiltersDTO);
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
		data.setEstTypeLookUp(estTypeLookUp);
		data.setEventTypeLookUp(eventTypeLookUp);
		return data;
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
		List<Long> venueEstTypeFilters = new ArrayList<Long>();
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
				venueEstTypeFilters.add(filter.getFilterid());
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
		venueDTO.setVenueId(venue.getVenueId());
		return venueDTO;
	}
	
	private void sendApprovalMailToAdmin(Venue savedVenue, User user) {
		String mailFrom = "no-reply@gmail.com";
		String mailSubject = "Approval Request:New Vendor Registeration";
		String mailContent = EmailTemplateUtil.generateMailContentForAdmin(savedVenue.getName(), "Venue");
		emailService.sendMail("mohitsingla2256@gmail.com", mailFrom, mailSubject, mailContent);
	}

	private void sendNotificationMailToUser(Venue savedVenue, User user) {
		String mailFrom = "no-reply@gmail.com";
		String mailSubject = "Vendor Registeration Request";
		String mailContent = EmailTemplateUtil.generateMailContentForUser(user);
		emailService.sendMail(user.getEmail(), mailFrom, mailSubject, mailContent);
	}
	
	@Override
	public void updateVenueActivationStatus(String action, Long venueId,
			String actionComments) throws Exception {
		Venue venue = (Venue) wizardDAO.getObjectById(venueId, Venue.class);
		if(venue != null){
			if("APPROVED".equalsIgnoreCase(action)){
				venue.setIsActive(true);
				wizardDAO.update(venue);
			}
			else if("REJECTED".equalsIgnoreCase(action)){
				//Just Send Notification
			}
			else{
				throw new Exception("Invalid Action Description");
			}
			sendActionNotificationToUser(venue.getUser(), action, actionComments, venue.getName());
		}
		else{
			throw new Exception("No Such Venue Exists");
		}
	}

	private void sendActionNotificationToUser(Long userId, String action,
			String actionComments, String name) {
		User user = (User) wizardDAO.getObjectById(userId, User.class);
		String mailFrom = "no-reply@gmail.com";
		String mailSubject = null;
		if("APPROVED".equalsIgnoreCase(action)){
			mailSubject = "Congratulations! Your Vendor Registration has been approved";
		}
		else{
			mailSubject = "Alas! Venor Registeration Request has been Rejected";
		}
		String mailContent = EmailTemplateUtil.generateMailContentForAction(actionComments, user, action);
		emailService.sendMail(user.getEmail(), mailFrom, mailSubject, mailContent);
		
	}

}
