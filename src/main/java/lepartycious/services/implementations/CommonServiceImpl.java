package lepartycious.services.implementations;

import java.util.ArrayList;
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
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Photographer;
import lepartycious.models.Rental;
import lepartycious.models.Venue;
import lepartycious.services.CatererService;
import lepartycious.services.CommonService;
import lepartycious.services.DecoratorService;
import lepartycious.services.PhotographerService;
import lepartycious.services.RentalService;
import lepartycious.services.VenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private VenueService venueService;

	@Autowired
	private RentalService rentalService;

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
		else if(SearchTypeEnum.RENTAL.toString().equals(searchDTO.getSearchType())){
			return rentalService.getRentals(searchDTO);
		}
		else{
			return null;
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
		else if(SearchTypeEnum.RENTAL.toString().equals(searchDTO.getSearchType())){
			return rentalService.loadRentalList(searchDTO);
		}
		else{
			return null;
		}
	}

	@Override
	public DetailResponseDTO fetchDetails(DataRequestDTO dataRequestDTO) {
		String entityName = dataRequestDTO.getName();
		if(entityName.contains("&type=")){
			entityName = entityName.replaceAll("&type=", "+");
			StringTokenizer strTokens = new StringTokenizer(entityName, "+");
			dataRequestDTO.setName(strTokens.nextToken());
			dataRequestDTO.setSearchType(strTokens.nextToken());
		}
		if(SearchTypeEnum.VENUE.toString().equals(dataRequestDTO.getSearchType())){
			return venueService.fetchVenueDetails(dataRequestDTO);
		}
		else if(SearchTypeEnum.CATERER.toString().equals(dataRequestDTO.getSearchType())){
			return catererService.fetchCatererDetails(dataRequestDTO);
		}
		else if(SearchTypeEnum.RENTAL.toString().equals(dataRequestDTO.getSearchType())){
			return rentalService.fetchRentalDetails(dataRequestDTO);
		}
		else if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(dataRequestDTO.getSearchType())){
			return photographerService.fetchPhotographerDetails(dataRequestDTO);
		}
		else if(SearchTypeEnum.DECORATOR.toString().equals(dataRequestDTO.getSearchType())){
			return decoratorService.fetchDecoratorDetails(dataRequestDTO);
		}
		else{
			return null;
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
		else if(SearchTypeEnum.RENTAL.toString().equals(searchType)){
			return rentalService.loadFilters(cityId);
		}
		else if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(searchType)){
			return photographerService.loadFilters(cityId);
		}
		else if(SearchTypeEnum.DECORATOR.toString().equals(searchType)){
			return decoratorService.loadFilters(cityId);
		}
		else{
			return null;
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
		if(SearchTypeEnum.RENTAL.toString().equals(searchType)){
			return rentalService.fetchRecomendations(cityId);
		}
		if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(searchType)){
			return photographerService.fetchRecomendations(cityId);
		}
		if(SearchTypeEnum.DECORATOR.toString().equals(searchType)){
			return decoratorService.fetchRecomendations(cityId);
		}
		else{
			return null;
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

		//Fetching Recent Rentals
		List<AddedDTO> rentalAdditions = new ArrayList<AddedDTO>();
		List<Rental> rentalList =  commonDAO.getRecentlyAddedRentals(cityId);
		for(Rental rental : rentalList){
			AddedDTO details = new AddedDTO(rental.getName(), rental.getLocality().getName(), rental.getCity().getName(), SearchTypeEnum.RENTAL.toString()); 
			rentalAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(rentalAdditions)){
			recentAdditions.put("Rentals", rentalAdditions);
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
		return recentAdditions;
	}

	@Override
	public boolean pushDataToDatabase(String query) {
		return commonDAO.pushDataToDatabase(query);
	}

}
