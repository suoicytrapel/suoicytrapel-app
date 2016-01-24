package lepartycious.services;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface CityService {
	
	public Map<Long, String> loadCities();

}
