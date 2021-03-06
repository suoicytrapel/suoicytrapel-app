package lepartycious.daos;

import java.util.List;
import java.util.Map;

import lepartycious.models.Amenities;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Filter;
import lepartycious.models.Locality;
import lepartycious.models.Others;
import lepartycious.models.Photographer;
import lepartycious.models.Entertainment;
import lepartycious.models.Room;
import lepartycious.models.Service;
import lepartycious.models.Venue;

public interface CommonDAO {
	
	public List<Venue> getRecentlyAddedVenues(Long cityId);
	
	public List<Caterer> getRecentlyAddedCaterers(Long cityId);
	
	public List<Photographer> getRecentlyAddedPhotographers(Long cityId);
	
	public List<Entertainment> getRecentlyAddedEntertainers(Long cityId);
	
	public List<Decorator> getRecentlyAddedDecorators(Long cityId);
	
	public Map<String, String> pushDataToDatabase(String query);
	
	public List<Service> getServiceFilters(String forEntity, String ofType, boolean isFilter);
	
	public List<Amenities> getAmenities(boolean isFilter);
	
	public List<Room> getRooms(String room);
	
	public List<Filter> getRequiredFilters(String forEntity, String ofType);
	
	public List<Others> getRecentlyAddedOthers(Long cityId);
	
	void createEntity(Object entity);
	
	Long getVendorIdByName(String vendorName, Class dataClass, String primaryKey) throws Exception;
	
	List<Locality> getLocalities(Long cityId);
}
