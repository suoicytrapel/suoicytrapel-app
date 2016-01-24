package lepartycious.daos.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lepartycious.daos.CityDAO;
import lepartycious.models.City;

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

}
