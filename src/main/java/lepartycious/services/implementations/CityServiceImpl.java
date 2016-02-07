package lepartycious.services.implementations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lepartycious.daos.CityDAO;
import lepartycious.models.City;
import lepartycious.services.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityDAO cityDAO;

	@Override
	public Map<Long, String> loadCities() {
		Map<Long, String> cityMap = new HashMap<Long, String>();
		List<City> cities = cityDAO.loadCities("city");
		for(City city : cities){
			cityMap.put(city.getCityId(), city.getName());
		}
		return cityMap;
	}

}
