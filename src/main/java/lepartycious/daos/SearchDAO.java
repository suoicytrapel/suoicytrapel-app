/*package lepartycious.daos;

import java.util.List;

import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.models.Band;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Photographer;
import lepartycious.models.Rental;
import lepartycious.models.Venue;

public interface SearchDAO extends BaseDAO{
	
	public List<Venue> getVenues(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder);
	
	public List<Caterer> getCaterers(Long cityId, String searchString, Long offset, Long limit);
	
	public List<Decorator> getDecorators(Long cityId, String searchString, Long offset, Long limit);
	
	public List<Photographer> getPhotographers(Long cityId, String searchString, Long offset, Long limit);
	
	public List<Band> getBands(Long cityId, String searchString, Long offset, Long limit);
	
	public List<Rental> getRentals(Long cityId, String searchString, Long offset, Long limit);
	
	public Long getVenueCount(Long cityid, String searchString);
	
	public Long getCatererCount(Long cityid, String searchString);
	
	public Long getPhotographerCount(Long cityid, String searchString);
	
	public Long getBandCount(Long cityid, String searchString);
	
	public Long getRentalCount(Long cityid, String searchString);
	
	public Long getDecoratorCount(Long cityid, String searchString);

	public List<Venue> loadList(Long cityId, String searchString);
	
}
*/