package lepartycious.daos.implementations;

import java.util.Calendar;
import java.util.List;

import lepartycious.daos.CommonDAO;
import lepartycious.models.Amenities;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Filter;
import lepartycious.models.Photographer;
import lepartycious.models.Rental;
import lepartycious.models.Room;
import lepartycious.models.Service;
import lepartycious.models.Venue;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Caterer.class);
		criteria = createRecentAddedCriteria(criteria, cityId);
		List<Caterer> catererList = criteria.list();
		return catererList;
	}

	@Override
	public List<Photographer> getRecentlyAddedPhotographers(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Photographer.class);
		criteria = createRecentAddedCriteria(criteria, cityId);
		List<Photographer> photographerList = criteria.list();
		return photographerList;
	}

	@Override
	public List<Rental> getRecentlyAddedRentals(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rental.class);
		criteria = createRecentAddedCriteria(criteria, cityId);
		List<Rental> rentalList = criteria.list();
		return rentalList;
	}

	@Override
	public List<Decorator> getRecentlyAddedDecorators(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Decorator.class);
		criteria = createRecentAddedCriteria(criteria, cityId);
		List<Decorator> decoratorList = criteria.list();
		return decoratorList;
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

	@Override
	public List<Service> getServiceFilters(String forEntity, String ofType) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Service.class);
		criteria.add(Restrictions.eq("isFilter", true));
		criteria.add(Restrictions.eq("serviceForEntity", forEntity));
		criteria.add(Restrictions.eq("serviceType", ofType));
		List<Service> serviceList = criteria.list();
		return serviceList;
	}
	
	@Override
	public List<Amenities> getAmenities(String amenity) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Amenities.class);
		List<Amenities> amenities = criteria.list();
		return amenities;
	}
	
	@Override
	public List<Room> getRooms(String room) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Room.class);
		List<Room> rooms = criteria.list();
		return rooms;
	}
	
	@Override
	public List<Filter> getRequiredFilters(String forEntity, String ofType) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Filter.class);
		criteria.add(Restrictions.eq("filterForEntity", forEntity));
		criteria.add(Restrictions.eq("filterType", ofType));
		List<Filter> filterList = criteria.list();
		return filterList;
	}
}
