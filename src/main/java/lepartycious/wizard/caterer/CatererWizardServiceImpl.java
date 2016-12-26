package lepartycious.wizard.caterer;

import java.util.ArrayList;
import java.util.List;

import lepartycious.Enums.ServiceCategoryEnum;
import lepartycious.daos.CatererDAO;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.WizardDAO;
import lepartycious.dtos.requestDTOs.AddressDTO;
import lepartycious.dtos.requestDTOs.AttachmentDTO;
import lepartycious.dtos.requestDTOs.CatererDTO;
import lepartycious.dtos.requestDTOs.EntityFiltersDTO;
import lepartycious.dtos.requestDTOs.EntityServicesDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;
import lepartycious.models.Address;
import lepartycious.models.Attachment;
import lepartycious.models.Caterer;
import lepartycious.models.City;
import lepartycious.models.EntityFilters;
import lepartycious.models.EntityServices;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.models.Service;
import lepartycious.models.User;
import lepartycious.services.EmailService;
import lepartycious.services.SecurityUserDetailsService;
import lepartycious.util.EmailTemplateUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Service
public class CatererWizardServiceImpl implements CatererWizardService {

	@Autowired
	private WizardDAO wizardDAO;

	@Autowired
	private CommonDAO commonDAO;

	@Autowired
	private SecurityUserDetailsService userService;

	@Autowired
	private CatererDAO catererDAO;
	
	@Autowired
    private EmailService emailService;
	
	@Override
	public void saveCatererDetails(CatererDTO catererDTO) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long catererId = catererDTO.getCatererId();
		Caterer caterer = null;
		if(catererId <= 0){
			caterer = new Caterer();
		}
		else{
			caterer = (Caterer) wizardDAO.getObjectById(catererId, Caterer.class);
		}
		List<Address> addresses = new ArrayList<Address>();
		List<Attachment> attachments = new ArrayList<Attachment>();
		List<EntityServices> catererServices = new ArrayList<EntityServices>();
		List<EntityFilters> catererFilters = new ArrayList<EntityFilters>();
		List<Long> entityFilterList = new ArrayList<Long>();
		
		//Populate Filters
		entityFilterList.addAll(catererDTO.getCatererCapacity());
		entityFilterList.addAll(catererDTO.getCatererEvent());
		entityFilterList.addAll(catererDTO.getCatererPrice());
		entityFilterList.addAll(catererDTO.getCatererType());
		
		City city = (City) wizardDAO.getObjectById(catererDTO.getCity(), City.class);
		Locality locality = (Locality) wizardDAO.getObjectById(catererDTO.getLocality(), Locality.class);
		
		caterer.setName(catererDTO.getName());
		caterer.setDescription(catererDTO.getDescription());
		caterer.setBookingPolicy(catererDTO.getBookingPolicy());
		caterer.setPolicies(catererDTO.getRefundPolicy());
		caterer.setServingSince(catererDTO.getServingSince());
		caterer.setIsActive(false);
		caterer.setCity(city);
		caterer.setLocality(locality);
		caterer.setStartingPrice(catererDTO.getStartingPriceVeg());
		caterer.setStartingPriceNonVeg(catererDTO.getStartingPriceNonVeg());
		caterer.setInfoProviderContact(catererDTO.getInformationProviderContactNo());
		caterer.setInfoProviderName(catererDTO.getInformationProviderName());
		caterer.setWebsiteName(catererDTO.getWebsite());
		caterer.setUser(user.getUserId());
		if(catererId <= 0){
			catererId = wizardDAO.save(caterer);
			caterer = (Caterer) wizardDAO.getObjectById(catererId, Caterer.class);
		}
		for(AttachmentDTO attachmentDTO : catererDTO.getVendorAttachments()){
			Attachment attachment = new Attachment(attachmentDTO.getName(), attachmentDTO.getImageURL(), 
					attachmentDTO.getHelpText(), "VENDOR", attachmentDTO.getImageData());
			attachment.setEntityId(catererId);
			attachments.add(attachment);
		}

		for(AttachmentDTO attachmentDTO : catererDTO.getMenuAttachments()){
			Attachment attachment = new Attachment(attachmentDTO.getName(), attachmentDTO.getImageURL(), 
					attachmentDTO.getHelpText(), "MENU", attachmentDTO.getImageData());
			attachment.setEntityId(catererId);
			attachments.add(attachment);
		}

		for(AddressDTO addressDTO : catererDTO.getAddresses()){
			Address address = new Address(addressDTO.getAddressLine1(), addressDTO.getAddressLine2(), addressDTO.getCity(), 
					addressDTO.getEmail(), addressDTO.getPincode(), addressDTO.getPrimaryPhone(), addressDTO.getSecondaryPhone());
			address.setLatitude(addressDTO.getLatitude());
			address.setLongitude(addressDTO.getLongitude());
			address.setEntityId(catererId);
			addresses.add(address);
		}

		for(Long serviceId : catererDTO.getCatererServices()){
			lepartycious.models.Service service = (lepartycious.models.Service) wizardDAO.getObjectById(serviceId, lepartycious.models.Service.class);
			EntityServices catererService = new EntityServices();
			catererService.setServiceId(service);
			catererService.setEntityId(catererId);
			catererServices.add(catererService);
		}
		
		for(Long serviceId : catererDTO.getCuisines()){
			lepartycious.models.Service service = (lepartycious.models.Service) wizardDAO.getObjectById(serviceId, lepartycious.models.Service.class);
			EntityServices catererService = new EntityServices();
			catererService.setServiceId(service);
			catererService.setEntityId(catererId);
			catererServices.add(catererService);
		}

		for(Long entityFilterDTO : entityFilterList){
			Filter filter = (Filter) wizardDAO.getObjectById(entityFilterDTO, Filter.class);
			EntityFilters entityFilter = new EntityFilters();
			entityFilter.setFilterId(filter);
			entityFilter.setEntityId(catererId);
			catererFilters.add(entityFilter);
		}
		caterer.setAddresses(addresses);
		caterer.setAttachments(attachments);
		caterer.setCatererServices(catererServices);
		caterer.setCatererFilters(catererFilters);
		wizardDAO.update(caterer);
		sendNotificationMailToUser(caterer, user);
		sendApprovalMailToAdmin(caterer, user);
	}

	@Override
	public LookUpDTO getCatererlookUp() {
		
		LookUpDTO data = new LookUpDTO();
		
		List<EntityServicesDTO> catererServicesLookUp = new ArrayList<EntityServicesDTO>();
		List<EntityFiltersDTO> catererPriceLookUp = new ArrayList<EntityFiltersDTO>();
		List<EntityFiltersDTO> catererCapacitylookUp = new ArrayList<EntityFiltersDTO>();
		List<EntityFiltersDTO> catererTypeLookUp = new ArrayList<EntityFiltersDTO>();
		List<EntityFiltersDTO> eventTypeLookUp = new ArrayList<EntityFiltersDTO>();
		List<EntityServicesDTO> cuisinesLookUp = new ArrayList<EntityServicesDTO>();

		List<Service> serviceList = commonDAO.getServiceFilters("CATERER", "SERVICE", true);
		List<Filter> eventList = commonDAO.getRequiredFilters("ALL", "EVENT");
		List<Filter> priceRangeList = commonDAO.getRequiredFilters("CATERER", "PRICE");
		List<Filter> capacityFilters = commonDAO.getRequiredFilters("CATERER", "CAPACITY");
		List<Filter> typeFilters = commonDAO.getRequiredFilters("CATERER", "TYPE");

		for(Filter filter : typeFilters){
			EntityFiltersDTO entityFiltersDTO = new EntityFiltersDTO();
			entityFiltersDTO.setFilterId(filter.getFilterid());
			entityFiltersDTO.setFilterName(filter.getFilterName());
			catererTypeLookUp.add(entityFiltersDTO);
		}
		
		for(Filter filter : eventList){
			EntityFiltersDTO entityFiltersDTO = new EntityFiltersDTO();
			entityFiltersDTO.setFilterId(filter.getFilterid());
			entityFiltersDTO.setFilterName(filter.getFilterName());
			eventTypeLookUp.add(entityFiltersDTO);
		}
		
		for(Filter filter : priceRangeList){
			EntityFiltersDTO entityFiltersDTO = new EntityFiltersDTO();
			entityFiltersDTO.setFilterId(filter.getFilterid());
			entityFiltersDTO.setFilterName(filter.getFilterName());
			catererPriceLookUp.add(entityFiltersDTO);
		}
		
		for(Filter filter : capacityFilters){
			EntityFiltersDTO entityFiltersDTO = new EntityFiltersDTO();
			entityFiltersDTO.setFilterId(filter.getFilterid());
			entityFiltersDTO.setFilterName(filter.getFilterName());
			catererCapacitylookUp.add(entityFiltersDTO);
		}

		for(Service service : serviceList){
			EntityServicesDTO entityServicesDTO = new EntityServicesDTO();
			entityServicesDTO.setServiceId(service.getServiceId());
			entityServicesDTO.setTabDataName(service.getTabDataName());
			if(ServiceCategoryEnum.CUISINE.toString().equalsIgnoreCase(service.getCategory())){
				cuisinesLookUp.add(entityServicesDTO);
			}
			else{
				catererServicesLookUp.add(entityServicesDTO);
			}
		}
		data.setCatererCapacitylookUp(catererCapacitylookUp);
		data.setCatererPriceLookUp(catererPriceLookUp);
		data.setCatererServicesLookUp(catererServicesLookUp);
		data.setCatererTypeLookUp(catererTypeLookUp);
		data.setEventTypeLookUp(eventTypeLookUp);
		data.setCuisinesLookUp(cuisinesLookUp);
		return data;
	}

	@Override
	public CatererDTO loadCatererWizard(String wizardName)
			throws Exception {
		CatererDTO catererDTO = null;
		Caterer caterer = catererDAO.loadCatererByName(wizardName);
		if(caterer != null){
			catererDTO = populateCatererDetails(caterer);
		}
		else {
			if(StringUtils.isNotBlank(wizardName)){
				catererDTO = new CatererDTO();
				catererDTO.setName(wizardName);
			}
			else{
				throw new Exception("No Caterer is associated with User");
			}

		}
		return catererDTO;
	}

	private CatererDTO populateCatererDetails(Caterer caterer) {

		List<AddressDTO> addressesDTO = new ArrayList<AddressDTO>();
		List<AttachmentDTO> vendorAttachments = new ArrayList<AttachmentDTO>();
		List<AttachmentDTO> menuAttachments = new ArrayList<AttachmentDTO>();
		List<Long> cuisines = new ArrayList<Long>();
		List<Long> catererType = new ArrayList<Long>();
		List<Long> catererPrice = new ArrayList<Long>();
		List<Long> catererEvent = new ArrayList<Long>();
		List<Long> catererCapacity = new ArrayList<Long>();
		List<Long> catererServices = new ArrayList<Long>();

		CatererDTO catererDTO = new CatererDTO(caterer.getName(), caterer.getDescription(), caterer.getCity().getCityId(), caterer.getLocality().getLocalityId(),
				caterer.getPriority(), caterer.getBookingPolicy(), caterer.getPolicies(), caterer.getServingSince(), caterer.getStartingPrice(),
				caterer.getMaxCapacity(), caterer.getMinCapacity(), caterer.getStartingPriceNonVeg(), caterer.getInfoProviderContact(), 
				caterer.getInfoProviderName(), caterer.getWebsiteName(), caterer.getCaterer_id());
		List<Address> addresses = caterer.getAddresses();
		for(Address address : addresses){
			AddressDTO addressDTO = new AddressDTO(address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getState(), address.getEmail(),
					address.getPincode(), address.getPrimaryPhone(), address.getSecondaryPhone(), address.getLatitude(), address.getLongitude());
			addressesDTO.add(addressDTO);
		}
		for(Attachment attachment : caterer.getAttachments()){
			AttachmentDTO attach = new AttachmentDTO(attachment.getName(), attachment.getImageURL(), attachment.getHelpText(), attachment.getAttachmentType(), 
					attachment.getImageData());
			if("VENDOR".equalsIgnoreCase(attachment.getAttachmentType())){
				vendorAttachments.add(attach);
			}
			else{
				menuAttachments.add(attach);
			}
		}
		for(EntityServices entityService : caterer.getCatererServices()){
			Service service = entityService.getServiceId();
			if(ServiceCategoryEnum.CUISINE.toString().equalsIgnoreCase(service.getCategory())){
				cuisines.add(service.getServiceId());
			}
			else{
				catererServices.add(service.getServiceId());
			}
		}
		for(EntityFilters entityFilter : caterer.getCatererFilters()){
			Filter filter = entityFilter.getFilterId();
			if(filter.getFilterType().equalsIgnoreCase("PRICE")){
				catererPrice.add(filter.getFilterid());
			}
			else if(filter.getFilterType().equalsIgnoreCase("CAPACITY")){
				catererCapacity.add(filter.getFilterid());
			}
			if(filter.getFilterType().equalsIgnoreCase("TYPE")){
				catererType.add(filter.getFilterid());
			}
			if(filter.getFilterType().equalsIgnoreCase("EVENT")){
				catererEvent.add(filter.getFilterid());
			}
		}

		catererDTO.setAddresses(addressesDTO);
		catererDTO.setVendorAttachments(vendorAttachments);
		catererDTO.setMenuAttachments(menuAttachments);
		catererDTO.setCuisines(cuisines);
		catererDTO.setCatererId(caterer.getCaterer_id());
		catererDTO.setCatererCapacity(catererCapacity);
		catererDTO.setCatererEvent(catererEvent);
		catererDTO.setCatererPrice(catererPrice);
		catererDTO.setCatererType(catererType);
		catererDTO.setCatererServices(catererServices);
		return catererDTO;
	}
	
	private void sendApprovalMailToAdmin(Caterer savedCaterer, User user) {
		String mailFrom = "no-reply@gmail.com";
		String mailSubject = "Approval Request:New Vendor Registeration";
		String mailContent = EmailTemplateUtil.generateMailContentForAdmin(savedCaterer.getName(), "Caterer");
		emailService.sendMail("mohitsingla2256@gmail.com", mailFrom, mailSubject, mailContent);
	}

	private void sendNotificationMailToUser(Caterer savedCaterer, User user) {
		String mailFrom = "no-reply@gmail.com";
		String mailSubject = "Vendor Registeration Request";
		String mailContent = EmailTemplateUtil.generateMailContentForUser(user);
		emailService.sendMail(user.getEmail(), mailFrom, mailSubject, mailContent);
	}
	
	@Override
	public void updateCatererActivationStatus(String action, Long catererId,
			String actionComments) throws Exception {
		Caterer caterer = (Caterer) wizardDAO.getObjectById(catererId, Caterer.class);
		if(caterer != null){
			if("APPROVED".equalsIgnoreCase(action)){
				caterer.setIsActive(true);
				wizardDAO.update(caterer);
			}
			else if("REJECTED".equalsIgnoreCase(action)){
				//Just Send Notification
			}
			else{
				throw new Exception("Invalid Action Description");
			}
			sendActionNotificationToUser(caterer.getUser(), action, actionComments, caterer.getName());
		}
		else{
			throw new Exception("No Such Caterer Exists");
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
			mailSubject = "Alas! Vendor Registeration Request has been Rejected";
		}
		String mailContent = EmailTemplateUtil.generateMailContentForAction(actionComments, user, action);
		emailService.sendMail(user.getEmail(), mailFrom, mailSubject, mailContent);
		
	}

}
