package lepartycious.daos;

import java.util.List;

import lepartycious.models.Amenities;
import lepartycious.models.City;
import lepartycious.models.Service;

public interface CityDAO {
	
	public List<City> loadCities(); 
	
	public List<Service> getServices();
	
	public List<Amenities> getAmenities();

}
