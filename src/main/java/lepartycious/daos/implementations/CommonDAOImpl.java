package lepartycious.daos.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lepartycious.daos.CommonDAO;
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

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
@CacheConfig(cacheNames = "commonCache")
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
	public List<Entertainment> getRecentlyAddedEntertainers(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Entertainment.class);
		criteria = createRecentAddedCriteria(criteria, cityId);
		List<Entertainment> rentalList = criteria.list();
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
		additionCriteria.add(Restrictions.eq("city.cityId", cityId));
		additionCriteria.addOrder(Order.desc("addedOn"));
		additionCriteria.setFirstResult(0);
		additionCriteria.setMaxResults(3);
		return additionCriteria;
	}

	@Override
	@CacheEvict(value = {"commonCache", "cityCache", "decoratorCache", "venueCache", "photographerCache", "catererCache", "entertainmentCache", "othersCache"}, allEntries = true)
	public Map<String, String> pushDataToDatabase(String query) {
		StringTokenizer strTokens = new StringTokenizer(query, ";");
		Map<String, String> data = new HashMap<String, String>();
		int success = 0;
		int failure = 0;
		while(strTokens.hasMoreElements()){
			String queryToExecute = (String) strTokens.nextElement();
			try{
				SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(queryToExecute.trim());
				sqlQuery.executeUpdate();
				success++;
			}
			catch(Exception exception){
				data.put(queryToExecute, exception.getMessage());
				failure++;
			}
		}
		data.put("Processed Queries",Integer.toString(success));
		data.put("Failed Queries",Integer.toString(failure));
		return data;
	}

	@Override
	@Cacheable
	public List<Service> getServiceFilters(String forEntity, String ofType, boolean isFilter) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Service.class);
		criteria.add(Restrictions.eq("isFilter", isFilter));
		criteria.add(Restrictions.eq("serviceForEntity", forEntity));
		criteria.add(Restrictions.eq("serviceType", ofType));
		List<Service> serviceList = criteria.list();
		return serviceList;
	}
	
	@Override
	@Cacheable
	public List<Amenities> getAmenities(boolean isFilter) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Amenities.class);
		criteria.add(Restrictions.eq("isFilter", isFilter));
		List<Amenities> amenities = criteria.list();
		return amenities;
	}
	
	@Override
	@Cacheable
	public List<Room> getRooms(String room) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Room.class);
		List<Room> rooms = criteria.list();
		return rooms;
	}
	
	@Override
	@Cacheable
	public List<Filter> getRequiredFilters(String forEntity, String ofType) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Filter.class);
		criteria.add(Restrictions.eq("filterForEntity", forEntity));
		criteria.add(Restrictions.eq("filterType", ofType));
		List<Filter> filterList = criteria.list();
		return filterList;
	}

	@Override
	public List<Others> getRecentlyAddedOthers(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Others.class);
		criteria = createRecentAddedCriteria(criteria, cityId);
		List<Others> rentalList = criteria.list();
		return rentalList;
	}
	
	@Override
	public Long getVendorIdByName(String vendorName, Class dataClass, String primaryKey) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(dataClass);
		criteria.add(Restrictions.eq("name", vendorName));
		criteria.setProjection(Projections.property(primaryKey));
		List result = criteria.list();
		if(!CollectionUtils.isEmpty(result)){
			return (Long) result.get(0);
		}
		else{
			throw new Exception("Vendor with vendor name " + vendorName + "does not exist");
		}
	}

	@Override
	public void createEntity(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	@Override
	public List<Locality> getLocalities(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Locality.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		return criteria.list();
	}
}
