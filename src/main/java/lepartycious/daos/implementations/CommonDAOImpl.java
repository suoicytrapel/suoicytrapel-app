package lepartycious.daos.implementations;

import java.util.Calendar;
import java.util.List;

import lepartycious.daos.CommonDAO;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Photographer;
import lepartycious.models.Rental;
import lepartycious.models.Venue;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAOImpl extends BaseDAOImpl implements CommonDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Venue> getRecentlyAddedVenues(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class);
		criteria = createRecentAddedCriteria(criteria, cityId);
		List<Venue> venueList = criteria.list();
		return venueList;
	}

	@Override
	public List<Caterer> getRecentlyAddedCaterers(Long cityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photographer> getRecentlyAddedPhotographers(Long cityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rental> getRecentlyAddedRentals(Long cityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Decorator> getRecentlyAddedDecorators(Long cityId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Criteria createRecentAddedCriteria(Criteria criteria, Long cityId){
		Criteria additionCriteria = criteria;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		additionCriteria.add(Restrictions.eq("city.cityId", cityId));
		additionCriteria.add(Restrictions.ge("addedOn", cal.getTime()));
		additionCriteria.addOrder(Order.desc("addedOn"));
		additionCriteria.setFirstResult(0);
		additionCriteria.setMaxResults(3);
		return additionCriteria;
	}

	@Override
	public boolean pushDataToDatabase(String query) {
		boolean isQuerySaved = false;
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
		int rowsAffected = sqlQuery.executeUpdate();
		if(rowsAffected > 0){
			isQuerySaved = true;
		}
		return isQuerySaved;
	}

}
