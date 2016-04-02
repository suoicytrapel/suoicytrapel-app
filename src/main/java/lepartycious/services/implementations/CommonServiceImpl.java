package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lepartycious.Enums.SearchTypeEnum;
import lepartycious.daos.CommonDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.AddedDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Attachment;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Entertainment;
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
		if(SearchTypeEnum.VENUE.toString().equals(searchDTO.getSearchType())){
			return venueService.getVenues(searchDTO);
		}
		else if(SearchTypeEnum.CATERER.toString().equals(searchDTO.getSearchType())){
			return catererService.getCaterers(searchDTO);
		}
		else if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(searchDTO.getSearchType())){
			return photographerService.getPhotographers(searchDTO);
		}
		else if(SearchTypeEnum.DECORATOR.toString().equals(searchDTO.getSearchType())){
			return decoratorService.getDecorators(searchDTO);
		}
		else if(SearchTypeEnum.ENTERTAINMENT.toString().equals(searchDTO.getSearchType())){
			return entertainmentService.getRentals(searchDTO);
		}
		else if(SearchTypeEnum.OTHERS.toString().equals(searchDTO.getSearchType())){
			return othersService.getOthers(searchDTO);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}

	}

	@Override
	public List<String> loadList(SearchRequestDTO searchDTO) {
		if(SearchTypeEnum.VENUE.toString().equals(searchDTO.getSearchType())){
			return venueService.loadVenueList(searchDTO);
		}
		else if(SearchTypeEnum.CATERER.toString().equals(searchDTO.getSearchType())){
			return catererService.loadCatererList(searchDTO);
		}
		else if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(searchDTO.getSearchType())){
			return photographerService.loadPhotographerList(searchDTO);
		}
		else if(SearchTypeEnum.DECORATOR.toString().equals(searchDTO.getSearchType())){
			return decoratorService.loadDecoratorList(searchDTO);
		}
		else if(SearchTypeEnum.ENTERTAINMENT.toString().equals(searchDTO.getSearchType())){
			return entertainmentService.loadRentalList(searchDTO);
		}
		else if(SearchTypeEnum.OTHERS.toString().equals(searchDTO.getSearchType())){
			return othersService.loadOthersList(searchDTO);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}
	}

	@Override
	public DetailResponseDTO fetchDetails(DataRequestDTO dataRequestDTO) {
		if(SearchTypeEnum.VENUE.toString().equals(dataRequestDTO.getSearchType())){
			return venueService.fetchVenueDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		}
		else if(SearchTypeEnum.CATERER.toString().equals(dataRequestDTO.getSearchType())){
			return catererService.fetchCatererDetails(dataRequestDTO);
		}
		else if(SearchTypeEnum.ENTERTAINMENT.toString().equals(dataRequestDTO.getSearchType())){
			return entertainmentService.fetchRentalDetails(dataRequestDTO);
		}
		else if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(dataRequestDTO.getSearchType())){
			return photographerService.fetchPhotographerDetails(dataRequestDTO);
		}
		else if(SearchTypeEnum.DECORATOR.toString().equals(dataRequestDTO.getSearchType())){
			return decoratorService.fetchDecoratorDetails(dataRequestDTO);
		}
		else if(SearchTypeEnum.OTHERS.toString().equals(dataRequestDTO.getSearchType())){
			return othersService.fetchOthersDetails(dataRequestDTO);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}
	}

	@Override
	public FilterResponseWrapperDTO loadFilters(String searchType, Long cityId) {
		if(SearchTypeEnum.VENUE.toString().equals(searchType)){
			return venueService.loadFilters(cityId);
		}
		else if(SearchTypeEnum.CATERER.toString().equals(searchType)){
			return catererService.loadFilters(cityId);
		}
		else if(SearchTypeEnum.ENTERTAINMENT.toString().equals(searchType)){
			return entertainmentService.loadFilters(cityId);
		}
		else if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(searchType)){
			return photographerService.loadFilters(cityId);
		}
		else if(SearchTypeEnum.DECORATOR.toString().equals(searchType)){
			return decoratorService.loadFilters(cityId);
		}
		else if(SearchTypeEnum.OTHERS.toString().equals(searchType)){
			return othersService.loadFilters(cityId);
		}
		else{
			throw new IllegalArgumentException("Invalid Category");
		}
	}

	@Override
	public List<SearchResponseDTO> fetchRecomendations(String searchType,
			Long cityId) {
		if(SearchTypeEnum.VENUE.toString().equals(searchType)){
			return venueService.fetchRecomendations(cityId);
		}
		if(SearchTypeEnum.CATERER.toString().equals(searchType)){
			return catererService.fetchRecomendations(cityId);
		}
		if(SearchTypeEnum.ENTERTAINMENT.toString().equals(searchType)){
			return entertainmentService.fetchRecomendations(cityId);
		}
		if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(searchType)){
			return photographerService.fetchRecomendations(cityId);
		}
		if(SearchTypeEnum.DECORATOR.toString().equals(searchType)){
			return decoratorService.fetchRecomendations(cityId);
		}
		if(SearchTypeEnum.OTHERS.toString().equals(searchType)){
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
			AddedDTO details = new AddedDTO(venue.getName(), venue.getLocality().getName(), venue.getCity().getName(), SearchTypeEnum.VENUE.toString()); 
			venueAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(venueAdditions)){
			recentAdditions.put("Venues", venueAdditions);
		}

		//Fetching Recent Caterers
		List<AddedDTO> catererAdditions = new ArrayList<AddedDTO>();
		List<Caterer> catererList =  commonDAO.getRecentlyAddedCaterers(cityId);
		for(Caterer caterer : catererList){
			AddedDTO details = new AddedDTO(caterer.getName(), caterer.getLocality().getName(), caterer.getCity().getName(), SearchTypeEnum.CATERER.toString()); 
			catererAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(catererAdditions)){
			recentAdditions.put("Caterers", catererAdditions);
		}

		//Fetching Entertainment Rentals
		List<AddedDTO> rentalAdditions = new ArrayList<AddedDTO>();
		List<Entertainment> rentalList =  commonDAO.getRecentlyAddedEntertainers(cityId);
		for(Entertainment rental : rentalList){
			AddedDTO details = new AddedDTO(rental.getName(), rental.getLocality().getName(), rental.getCity().getName(), SearchTypeEnum.ENTERTAINMENT.toString()); 
			rentalAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(rentalAdditions)){
			recentAdditions.put("Entertainers", rentalAdditions);
		}

		//Fetching Recent Decorators
		List<AddedDTO> decoratorAdditions = new ArrayList<AddedDTO>();
		List<Decorator> decoratorList =  commonDAO.getRecentlyAddedDecorators(cityId);
		for(Decorator decorator : decoratorList){
			AddedDTO details = new AddedDTO(decorator.getName(), decorator.getLocality().getName(), decorator.getCity().getName(), SearchTypeEnum.DECORATOR.toString()); 
			decoratorAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(decoratorAdditions)){
			recentAdditions.put("Decorators", decoratorAdditions);
		}

		//Fetching Recent Rentals
		List<AddedDTO> photographerAdditions = new ArrayList<AddedDTO>();
		List<Photographer> photographerList =  commonDAO.getRecentlyAddedPhotographers(cityId);
		for(Photographer photographer : photographerList){
			AddedDTO details = new AddedDTO(photographer.getName(), photographer.getLocality().getName(), photographer.getCity().getName(), SearchTypeEnum.PHOTOGRAPHER.toString()); 
			photographerAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(photographerAdditions)){
			recentAdditions.put("Photographers", photographerAdditions);
		}

		//Fetching Recent Rentals
		List<AddedDTO> othersAdditions = new ArrayList<AddedDTO>();
		List<Others> othersList =  commonDAO.getRecentlyAddedOthers(cityId);
		for(Others other : othersList){
			AddedDTO details = new AddedDTO(other.getName(), other.getLocality().getName(), other.getCity().getName(), SearchTypeEnum.OTHERS.toString()); 
			othersAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(othersAdditions)){
			recentAdditions.put("Others", othersAdditions);
		}
		return recentAdditions;
	}

	@Override
	public boolean pushDataToDatabase(String query) {
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

}
