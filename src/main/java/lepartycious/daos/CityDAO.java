package lepartycious.daos;

import java.util.List;

import lepartycious.models.Amenities;
import lepartycious.models.City;
import lepartycious.models.Locality;
import lepartycious.models.Room;
import lepartycious.models.Service;

public interface CityDAO {
	
	public List<City> loadCities(String city); 
	
	public List<Service> getServices(String service);
	
	public List<Amenities> getAmenities(String amenity);
	
	public City getCityById(Long cityId);
	
	public List<Room> getRooms(String room);

}
