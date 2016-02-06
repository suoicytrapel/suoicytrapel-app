package lepartycious.daos.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lepartycious.daos.CityDAO;
import lepartycious.models.Amenities;
import lepartycious.models.City;
import lepartycious.models.Service;

@Repository
public class CityDAOImpl extends BaseDAOImpl implements CityDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<City> loadCities() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(City.class);
		List<City> cityList = criteria.list();
		return cityList;
	}

	@Override
	public List<Service> getServices() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Service.class);
		List<Service> services = criteria.list();
		return services;
	}

	@Override
	public List<Amenities> getAmenities() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Amenities.class);
		List<Amenities> amenities = criteria.list();
		return amenities;
	}

}
