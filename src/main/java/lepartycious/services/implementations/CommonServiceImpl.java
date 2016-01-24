package lepartycious.services.implementations;

import java.util.List;

import lepartycious.Enums.SearchTypeEnum;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.services.BandService;
import lepartycious.services.CatererService;
import lepartycious.services.CommonService;
import lepartycious.services.DecoratorService;
import lepartycious.services.PhotographerService;
import lepartycious.services.RentalService;
import lepartycious.services.VenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
