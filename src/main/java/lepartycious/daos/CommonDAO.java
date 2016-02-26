package lepartycious.daos;

import java.util.List;

import lepartycious.models.Amenities;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Filter;
import lepartycious.models.Photographer;
import lepartycious.models.Rental;
import lepartycious.models.Room;
import lepartycious.models.Service;
import lepartycious.models.Venue;

public interface CommonDAO {
	
	public List<Venue> getRecentlyAddedVenues(Long cityId);
	public List<Caterer> getRecentlyAddedCaterers(Long cityId);
	public List<Photographer> getRecentlyAddedPhotographers(Long cityId);
	public List<Rental> getRecentlyAddedRentals(Long cityId);
	public List<Decorator> getRecentlyAddedDecorators(Long cityId);
	public boolean pushDataToDatabase(String query);
	public List<Service> getServiceFilters(String forEntity, String ofType);
	public List<Amenities> getAmenities(String amenity);
	public List<Room> getRooms(String room);
	public List<Filter> getRequiredFilters(String forEntity, String ofType);
}