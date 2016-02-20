package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lepartycious.Enums.SearchTypeEnum;
import lepartycious.daos.CommonDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.FilterRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.AddedDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Venue;
import lepartycious.services.BandService;
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
	private BandService bandService;
	
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
		else if(SearchTypeEnum.BAND.toString().equals(searchDTO.getSearchType())){
			return bandService.getBands(searchDTO);
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
		else if(SearchTypeEnum.BAND.toString().equals(searchDTO.getSearchType())){
			return bandService.loadBandList(searchDTO);
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
		if(SearchTypeEnum.VENUE.toString().equals(dataRequestDTO.getSearchType())){
			return venueService.fetchVenueDetails(dataRequestDTO);
		}
		/*else if(SearchTypeEnum.CATERER.toString().equals(dataRequestDTO.getSearchType())){
			//return catererService.getCaterers(searchDTO);
		}
		else if(SearchTypeEnum.BAND.toString().equals(dataRequestDTO.getSearchType())){
			//return bandService.getBands(searchDTO);
		}
		else if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(dataRequestDTO.getSearchType())){
			//return photographerService.getPhotographers(searchDTO);
		}
		else if(SearchTypeEnum.DECORATOR.toString().equals(dataRequestDTO.getSearchType())){
			//return decoratorService.getDecorators(searchDTO);
		}
		else if(SearchTypeEnum.RENTAL.toString().equals(dataRequestDTO.getSearchType())){
			//return rentalService.getRentals(searchDTO);
		}*/
		else{
			return null;
		}
	}

	@Override
	public FilterResponseWrapperDTO loadFilters(String searchType, Long cityId) {
		if(SearchTypeEnum.VENUE.toString().equals(searchType)){
			return venueService.loadFilters(cityId);
		}
		/*else if(SearchTypeEnum.CATERER.toString().equals(filterRequestDTO.getSearchType())){
			return catererService.getCaterers(searchDTO);
		}
		else if(SearchTypeEnum.BAND.toString().equals(filterRequestDTO.getSearchType())){
			return bandService.getBands(searchDTO);
		}
		else if(SearchTypeEnum.PHOTOGRAPHER.toString().equals(filterRequestDTO.getSearchType())){
			return photographerService.getPhotographers(searchDTO);
		}
		else if(SearchTypeEnum.DECORATOR.toString().equals(filterRequestDTO.getSearchType())){
			return decoratorService.getDecorators(searchDTO);
		}
		else if(SearchTypeEnum.RENTAL.toString().equals(filterRequestDTO.getSearchType())){
			return rentalService.getRentals(searchDTO);
		}*/
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
		else{
			return null;
		}
	}

	@Override
	public Map<String, List<AddedDTO>> getRecentAdditions(Long cityId) {
		Map<String, List<AddedDTO>> recentlyAddedVenues = new HashMap<String, List<AddedDTO>>();
		List<AddedDTO> venueAdditions = new ArrayList<AddedDTO>();
		List<Venue> venueList =  commonDAO.getRecentlyAddedVenues(cityId);
		for(Venue venue : venueList){
			AddedDTO details = new AddedDTO(venue.getName(), venue.getLocality().getName(), venue.getCity().getName()); 
			venueAdditions.add(details);
		}
		if(!CollectionUtils.isEmpty(venueAdditions)){
			recentlyAddedVenues.put("Venues", venueAdditions);
		}
		return recentlyAddedVenues;
	}

	@Override
	public boolean pushDataToDatabase(String query) {
		return commonDAO.pushDataToDatabase(query);
	}

}
