package lepartycious.daos;

import java.util.List;

import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Photographer;
import lepartycious.models.Rental;
import lepartycious.models.Venue;

public interface CommonDAO {
	
	public List<Venue> getRecentlyAddedVenues(Long cityId);
	public List<Caterer> getRecentlyAddedCaterers(Long cityId);
	public List<Photographer> getRecentlyAddedPhotographers(Long cityId);
	public List<Rental> getRecentlyAddedRentals(Long cityId);
	public List<Decorator> getRecentlyAddedDecorators(Long cityId);
	public boolean pushDataToDatabase(String query);
}
