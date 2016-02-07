package lepartycious.daos.implementations;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import lepartycious.daos.VenueDAO;
import lepartycious.models.Venue;

@Repository
public class VenueDAOImpl extends BaseDAOImpl implements VenueDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Venue> getVenues(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder,List<Long> serviceIds, List<Long> amenityIds, List<Long> roomIds, List<Long> localityIds, List<String> types) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString, serviceIds, amenityIds, roomIds, localityIds, types);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Venue> loadVenueList(Long cityId, String searchString) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString, null, null, null, null, null);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public Long getVenueCount(Long cityId, String searchString, List<Long> serviceIds, List<Long> amenityIds, List<Long> roomIds, List<Long> localityIds, List<String> types) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString, serviceIds, amenityIds, roomIds, localityIds, types);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createVenueSearchCriteria(Long cityId, String searchString, List<Long> serviceIds, List<Long> amenityIds, List<Long> roomIds, List<Long> localityIds, List<String> types) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class, "venue");
		if(!CollectionUtils.isEmpty(serviceIds)){
			criteria.createAlias("venue.venueServices", "vs"); // inner join by default
			criteria.createAlias("vs.serviceId", "service");
			criteria.add(Restrictions.in("service.serviceId", serviceIds));
		}
		if(!CollectionUtils.isEmpty(amenityIds)){
			criteria.createAlias("venue.venueamenities", "va"); // inner join by default
			criteria.createAlias("va.amenitiesId", "amenity");
			criteria.add(Restrictions.in("amenity.amenitiesId", amenityIds));
		}
		if(!CollectionUtils.isEmpty(roomIds)){
			criteria.createAlias("venue.venueRooms", "vr"); // inner join by default
			criteria.createAlias("vr.roomId", "room");
			criteria.add(Restrictions.in("room.roomId", roomIds));
		}
		if(!CollectionUtils.isEmpty(localityIds)){
			criteria.add(Restrictions.in("locality.localityId", localityIds));
		}
		if(!CollectionUtils.isEmpty(types)){
			criteria.add(Restrictions.in("type", types));
		}
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		criteria.add(Restrictions.eq("city.cityId", cityId));
		return criteria;
	}

	@Override
	public Venue fetchVenueDetails(Long cityId, String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.eq("name", name));
		List<Venue> venues = criteria.list();
		if(CollectionUtils.isEmpty(venues)){
			return null;
		}
		else{
			return venues.get(0);
		}
	}


}
