package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lepartycious.Enums.VendorTypeEnum;
import lepartycious.daos.CommonDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.AddedDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Attachment;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Entertainment;
import lepartycious.models.Filter;
import lepartycious.models.Others;
import lepartycious.models.Photographer;
import lepartycious.models.Venue;
import lepartycious.services.CatererService;
import lepartycious.services.CommonService;
import lepartycious.services.DecoratorService;
import lepartycious.services.EntertainmentService;
import lepartycious.services.OthersService;
import lepartycious.services.PhotographerService;
import lepartycious.services.VenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private VenueService venueService;

	@Autowired
	private EntertainmentService entertainmentService;

	@Autowired
	private OthersService othersService;

	@Autowired
	private PhotographerService photographerService;

	@Autowired
	private DecoratorService decoratorService;

	@Autowired
	private CatererService catererService;

	@Autowired
	private CommonDAO commonDAO;

	@Override
	public SearchResponseDTOWrapper getEntities(SearchRequestDTO searchDTO) {
		if(VendorTypeEnum.VENUE.toString().equals(searchDTO.getSearchType())){
			return venueService.getVenues(searchDTO);
		}
		else if(VendorTypeEnum.CATERER.toString().equals(searchDTO.getSearchType())){
			return catererService.getCaterers(searchDTO);
		}
		else if(VendorTypeEnum.PHOTOGRAPHER.toString().equals(searchDTO.getSearchType())){
			return photographerService.getPhotographers(searchDTO);
		}
		else if(VendorTypeEnum.DECORATOR.toString().equals(searchDTO.getSearchType())){
			return decoratorService.getDecorators(searchDTO);
		}
		else if(VendorTypeEnum.ENTERTAINMENT.toString().equals(searchDTO.getSearchType())){
			return entertainmentService.getRentals(searchDTO);
		}
		else if(VendorTypeEnum.OTHERS.toString().equals(searchDTO.getSearchType())){
			return othersService.getOthers(searchDTO);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}

	}

	@Override
	public List<String> loadList(SearchRequestDTO searchDTO) {
		if(VendorTypeEnum.VENUE.toString().equals(searchDTO.getSearchType())){
			return venueService.loadVenueList(searchDTO);
		}
		else if(VendorTypeEnum.CATERER.toString().equals(searchDTO.getSearchType())){
			return catererService.loadCatererList(searchDTO);
		}
		else if(VendorTypeEnum.PHOTOGRAPHER.toString().equals(searchDTO.getSearchType())){
			return photographerService.loadPhotographerList(searchDTO);
		}
		else if(VendorTypeEnum.DECORATOR.toString().equals(searchDTO.getSearchType())){
			return decoratorService.loadDecoratorList(searchDTO);
		}
		else if(VendorTypeEnum.ENTERTAINMENT.toString().equals(searchDTO.getSearchType())){
			return entertainmentService.loadRentalList(searchDTO);
		}
		else if(VendorTypeEnum.OTHERS.toString().equals(searchDTO.getSearchType())){
			return othersService.loadOthersList(searchDTO);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}
	}

	@Override
	public DetailResponseDTO fetchDetails(DataRequestDTO dataRequestDTO) {
		if(VendorTypeEnum.VENUE.toString().equals(dataRequestDTO.getSearchType())){
			return venueService.fetchVenueDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		}
		else if(VendorTypeEnum.CATERER.toString().equals(dataRequestDTO.getSearchType())){
			return catererService.fetchCatererDetails(dataRequestDTO);
		}
		else if(VendorTypeEnum.ENTERTAINMENT.toString().equals(dataRequestDTO.getSearchType())){
			return entertainmentService.fetchRentalDetails(dataRequestDTO);
		}
		else if(VendorTypeEnum.PHOTOGRAPHER.toString().equals(dataRequestDTO.getSearchType())){
			return photographerService.fetchPhotographerDetails(dataRequestDTO);
		}
		else if(VendorTypeEnum.DECORATOR.toString().equals(dataRequestDTO.getSearchType())){
			return decoratorService.fetchDecoratorDetails(dataRequestDTO);
		}
		else if(VendorTypeEnum.OTHERS.toString().equals(dataRequestDTO.getSearchType())){
			return othersService.fetchOthersDetails(dataRequestDTO);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}
	}

	@Override
	public FilterResponseWrapperDTO loadFilters(String searchType, Long cityId) {
		if(VendorTypeEnum.VENUE.toString().equals(searchType)){
			return venueService.loadFilters(cityId);
		}
		else if(VendorTypeEnum.CATERER.toString().equals(searchType)){
			return catererService.loadFilters(cityId);
		}
		else if(VendorTypeEnum.ENTERTAINMENT.toString().equals(searchType)){
			return entertainmentService.loadFilters(cityId);
		}
		else if(VendorTypeEnum.PHOTOGRAPHER.toString().equals(searchType)){
			return photographerService.loadFilters(cityId);
		}
		else if(VendorTypeEnum.DECORATOR.toString().equals(searchType)){
			return decoratorService.loadFilters(cityId);
		}
		else if(VendorTypeEnum.OTHERS.toString().equals(searchType)){
			return othersService.loadFilters(cityId);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(String searchType,
			Long cityId) {
		if(VendorTypeEnum.VENUE.toString().equals(searchType)){
			return venueService.fetchRecomendations(cityId);
		}
		if(VendorTypeEnum.CATERER.toString().equals(searchType)){
			return catererService.fetchRecomendations(cityId);
		}
		if(VendorTypeEnum.ENTERTAINMENT.toString().equals(searchType)){
			return entertainmentService.fetchRecomendations(cityId);
		}
		if(VendorTypeEnum.PHOTOGRAPHER.toString().equals(searchType)){
			return photographerService.fetchRecomendations(cityId);
		}
		if(VendorTypeEnum.DECORATOR.toString().equals(searchType)){
			return decoratorService.fetchRecomendations(cityId);
		}
		if(VendorTypeEnum.OTHERS.toString().equals(searchType)){
			return othersService.fetchRecomendations(cityId);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}
	}

	@Override
	public Map<String, List<AddedDTO>> getRecentAdditions(Long cityId) {
		Map<String, List<AddedDTO>> recentAdditions = new HashMap<String, List<AddedDTO>>();

		//Fetching Recent Venues
		List<AddedDTO> venueAdditions = new ArrayList<AddedDTO>();
		List<Venue> venueList =  commonDAO.getRecentlyAddedVenues(cityId);
		for(Venue venue : venueList){
			AddedDTO details = new AddedDTO(venue.getName(), venue.getLocality().getName(), venue.getCity().getName(), VendorTypeEnum.VENUE.toString()); 
			venueAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(venueAdditions)){
			recentAdditions.put("Venues", venueAdditions);
		}

		//Fetching Recent Caterers
		List<AddedDTO> catererAdditions = new ArrayList<AddedDTO>();
		List<Caterer> catererList =  commonDAO.getRecentlyAddedCaterers(cityId);
		for(Caterer caterer : catererList){
			AddedDTO details = new AddedDTO(caterer.getName(), caterer.getLocality().getName(), caterer.getCity().getName(), VendorTypeEnum.CATERER.toString()); 
			catererAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(catererAdditions)){
			recentAdditions.put("Caterers", catererAdditions);
		}

		//Fetching Entertainment Rentals
		List<AddedDTO> rentalAdditions = new ArrayList<AddedDTO>();
		List<Entertainment> rentalList =  commonDAO.getRecentlyAddedEntertainers(cityId);
		for(Entertainment rental : rentalList){
			AddedDTO details = new AddedDTO(rental.getName(), rental.getLocality().getName(), rental.getCity().getName(), VendorTypeEnum.ENTERTAINMENT.toString()); 
			rentalAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(rentalAdditions)){
			recentAdditions.put("Entertainers", rentalAdditions);
		}

		//Fetching Recent Decorators
		List<AddedDTO> decoratorAdditions = new ArrayList<AddedDTO>();
		List<Decorator> decoratorList =  commonDAO.getRecentlyAddedDecorators(cityId);
		for(Decorator decorator : decoratorList){
			AddedDTO details = new AddedDTO(decorator.getName(), decorator.getLocality().getName(), decorator.getCity().getName(), VendorTypeEnum.DECORATOR.toString()); 
			decoratorAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(decoratorAdditions)){
			recentAdditions.put("Decorators", decoratorAdditions);
		}

		//Fetching Recent Rentals
		List<AddedDTO> photographerAdditions = new ArrayList<AddedDTO>();
		List<Photographer> photographerList =  commonDAO.getRecentlyAddedPhotographers(cityId);
		for(Photographer photographer : photographerList){
			AddedDTO details = new AddedDTO(photographer.getName(), photographer.getLocality().getName(), photographer.getCity().getName(), VendorTypeEnum.PHOTOGRAPHER.toString()); 
			photographerAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(photographerAdditions)){
			recentAdditions.put("Photographers", photographerAdditions);
		}

		//Fetching Recent Rentals
		List<AddedDTO> othersAdditions = new ArrayList<AddedDTO>();
		List<Others> othersList =  commonDAO.getRecentlyAddedOthers(cityId);
		for(Others other : othersList){
			AddedDTO details = new AddedDTO(other.getName(), other.getLocality().getName(), other.getCity().getName(), VendorTypeEnum.OTHERS.toString()); 
			othersAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(othersAdditions)){
			recentAdditions.put("Others", othersAdditions);
		}
		return recentAdditions;
	}

	@Override
	public Map<String, String> pushDataToDatabase(String query) {
		return commonDAO.pushDataToDatabase(query);
	}
	
	public List<Attachment> getDefaultImageList(){
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setImageURL("defaultimage.jpg");
		attachment.setAddedOn(new Date());
		attachment.setAttachmentType("COVER");
		attachment.setHelpText("No Image");
		attachments.add(attachment);
		return attachments;
	}

	@Override
	public Map<String, List<FilterResponseDTO>> fetchSubCategories() {
		
		Map<String, List<FilterResponseDTO>> subCategoriesMap = new LinkedHashMap<String, List<FilterResponseDTO>>();
		
		List<FilterResponseDTO> establishments = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> catererType = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> services = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> entertainmentTypes = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> otherTypes = new ArrayList<FilterResponseDTO>();
		List<FilterResponseDTO> photographerTypes = new ArrayList<FilterResponseDTO>();
		
		List<Filter> establishmentList = commonDAO.getRequiredFilters("VENUE", "ESTABLISHMENT");
		List<Filter> typeFilters = commonDAO.getRequiredFilters("CATERER", "TYPE");
		List<lepartycious.models.Service> serviceList = commonDAO.getServiceFilters("DECORATOR", "SERVICE");
		List<Filter> entertainmentTypeList = commonDAO.getRequiredFilters("ENTERTAINMENT", "ENTERTAINMENT");
		List<Filter> otherTypeList = commonDAO.getRequiredFilters("OTHERS", "OTHERS");
		List<Filter> photographerTypeList = commonDAO.getRequiredFilters("PHOTOGRAPHER", "TYPE");
		
		for(Filter estFilter : establishmentList){
			FilterResponseDTO filter = new FilterResponseDTO(estFilter.getFilterName(), estFilter.getFilterType(), estFilter.getFilterid());
			establishments.add(filter);
		}
		for(Filter typeFilter : typeFilters){
			FilterResponseDTO filter = new FilterResponseDTO(typeFilter.getFilterName(), typeFilter.getFilterType(), typeFilter.getFilterid());
			catererType.add(filter);
		}
		for(lepartycious.models.Service service : serviceList){
			FilterResponseDTO filter = new FilterResponseDTO(service.getFilterDataName(), service.getServiceType(), service.getServiceId());
			services.add(filter);
		}
		for(Filter rentTypeFilter : entertainmentTypeList){
			FilterResponseDTO filter = new FilterResponseDTO(rentTypeFilter.getFilterName(), rentTypeFilter.getFilterType(), rentTypeFilter.getFilterid());
			entertainmentTypes.add(filter);
		}
		for(Filter rentTypeFilter : otherTypeList){
			FilterResponseDTO filter = new FilterResponseDTO(rentTypeFilter.getFilterName(), rentTypeFilter.getFilterType(), rentTypeFilter.getFilterid());
			otherTypes.add(filter);
		}
		for(Filter serviceFilter : photographerTypeList){
			FilterResponseDTO filter = new FilterResponseDTO(serviceFilter.getFilterName(), serviceFilter.getFilterType(), serviceFilter.getFilterid());
			photographerTypes.add(filter);
		}
		
		subCategoriesMap.put("VENUE", establishments);
		subCategoriesMap.put("CATERER", catererType);
		subCategoriesMap.put("DECORATOR", services);
		subCategoriesMap.put("PHOTOGRAPHER", photographerTypes);
		subCategoriesMap.put("ENTERTAINMENT", entertainmentTypes);
		subCategoriesMap.put("OTHERS", otherTypes);
		
		// TODO Auto-generated method stub
		return subCategoriesMap;
	}
	
	private Map getDataclass(String vendorType) {
		Map dataMap = new HashMap<String, Object>();
		if(VendorTypeEnum.VENUE.toString().equals(vendorType)){
			dataMap.put("class", Venue.class);
			dataMap.put("id", "venueId");
			dataMap.put("name", "name");
		}
		else if(VendorTypeEnum.CATERER.toString().equals(vendorType)){
			dataMap.put("class", Caterer.class);
			dataMap.put("id", "catererId");
			dataMap.put("name", "name");
		}
		else if(VendorTypeEnum.PHOTOGRAPHER.toString().equals(vendorType)){
			dataMap.put("class", Photographer.class);
			dataMap.put("id", "photographerId");
			dataMap.put("name", "name");
		}
		else if(VendorTypeEnum.DECORATOR.toString().equals(vendorType)){
			dataMap.put("class", Decorator.class);
			dataMap.put("id", "decoratorId");
			dataMap.put("name", "name");
		}
		else if(VendorTypeEnum.ENTERTAINMENT.toString().equals(vendorType)){
			dataMap.put("class", Entertainment.class);
			dataMap.put("id", "entertainmentId");
			dataMap.put("name", "name");
		}
		else if(VendorTypeEnum.OTHERS.toString().equals(vendorType)){
			dataMap.put("class", Others.class);
			dataMap.put("id", "othersId");
			dataMap.put("name", "name");
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}
		return dataMap;

	}
	
	@Override
	public Long getVendorIdByName(String vendorType, String vendorName) throws Exception{
		Map<String,Object> data = getDataclass(vendorType);
		String primaryKey = (String) data.get("id");
		Class dataClass = (Class) data.get("class");
		Long id = commonDAO.getVendorIdByName(vendorName, dataClass, primaryKey);
		return id;
	}

	@Override
	public void createEntity(String vendorType, String vendorName) {
		if(VendorTypeEnum.VENUE.toString().equals(vendorType)){
			Venue venue = new Venue();
			venue.setName(vendorName);
			venue.setIsActive(false);
			commonDAO.createEntity(venue);
		}
		else if(VendorTypeEnum.CATERER.toString().equals(vendorType)){
			Caterer caterer = new Caterer();
			caterer.setName(vendorName);
			caterer.setIsActive(false);
			commonDAO.createEntity(caterer);
		}
		else if(VendorTypeEnum.PHOTOGRAPHER.toString().equals(vendorType)){
			Photographer photographer = new Photographer();
			photographer.setName(vendorName);
			photographer.setIsActive(false);
			commonDAO.createEntity(photographer);
		}
		else if(VendorTypeEnum.DECORATOR.toString().equals(vendorType)){
			Decorator decorator = new Decorator();
			decorator.setName(vendorName);
			decorator.setIsActive(false);
			commonDAO.createEntity(decorator);
		}
		else if(VendorTypeEnum.ENTERTAINMENT.toString().equals(vendorType)){
			Entertainment entertainment = new Entertainment();
			entertainment.setName(vendorName);
			entertainment.setIsActive(false);
			commonDAO.createEntity(entertainment);
		}
		else if(VendorTypeEnum.OTHERS.toString().equals(vendorType)){
			Others others = new Others();
			others.setName(vendorName);
			others.setIsActive(false);
			commonDAO.createEntity(others);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}

	}
}
