package lepartycious.daos.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import lepartycious.daos.CityDAO;
import lepartycious.models.Amenities;
import lepartycious.models.City;
import lepartycious.models.Locality;
import lepartycious.models.Room;
import lepartycious.models.Service;

@Repository
@CacheConfig(cacheNames = "lpcache")
public class CityDAOImpl extends BaseDAOImpl implements CityDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Cacheable
	public List<City> loadCities(String city) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(City.class);
		List<City> cityList = criteria.list();
		return cityList;
	}

	@Override
	@Cacheable
	public City getCityById(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(City.class);
		criteria.add(Restrictions.eq("cityId", cityId));
		List<City> cities = criteria.list();
		if(!CollectionUtils.isEmpty(cities)){
			return cities.get(0);
		}
		return null;
	}

}
