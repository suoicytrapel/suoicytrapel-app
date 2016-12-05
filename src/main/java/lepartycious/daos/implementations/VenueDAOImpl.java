package lepartycious.daos.implementations;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import lepartycious.daos.VenueDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Venue;

@Repository
@CacheConfig(cacheNames = "venueCache")
public class VenueDAOImpl extends BaseDAOImpl implements VenueDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Cacheable
	public List<Venue> getVenues(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder,FilterWrapperDTO filters) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString, filters);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public List<Venue> loadVenueList(Long cityId, String searchString) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString, null);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public Long getVenueCount(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString, filters);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createVenueSearchCriteria(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class, "venue");
		if(filters != null){
			if(!CollectionUtils.isEmpty(filters.getServiceList())){
				criteria.createAlias("venue.venueServices", "vs"); // inner join by default
				criteria.createAlias("vs.serviceId", "service");
				criteria.add(Restrictions.in("service.serviceId", filters.getServiceList()));
			}
			if(!CollectionUtils.isEmpty(filters.getAmenityList())){
				criteria.createAlias("venue.venueamenities", "va"); // inner join by default
				criteria.createAlias("va.amenitiesId", "amenity");
				criteria.add(Restrictions.in("amenity.amenitiesId", filters.getAmenityList()));
			}
			if(!CollectionUtils.isEmpty(filters.getRoomList())){
				criteria.createAlias("venue.venueRooms", "vr"); // inner join by default
				criteria.createAlias("vr.roomId", "room");
				criteria.add(Restrictions.in("room.roomId", filters.getRoomList()));
			}
			if(!CollectionUtils.isEmpty(filters.getLocalityList())){
				criteria.add(Restrictions.in("locality.localityId", filters.getLocalityList()));
			}
			if(!CollectionUtils.isEmpty(filters.getEstList())){
				criteria.createAlias("venue.venueFilters", "vf"); // inner join by default
				criteria.createAlias("vf.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getEstList()), Restrictions.eq("filters.filterType", "ESTABLISHMENT")));
			}
			if(!CollectionUtils.isEmpty(filters.getEventList())){
				criteria.createAlias("venue.venueFilters", "vf"); // inner join by default
				criteria.createAlias("vf.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getEventList()), Restrictions.eq("filters.filterType", "EVENT")));
			}
			if(!CollectionUtils.isEmpty(filters.getPriceRangeList())){
				criteria.createAlias("venue.venueFilters", "vf"); // inner join by default
				criteria.createAlias("vf.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getPriceRangeList()), Restrictions.eq("filters.filterType", "PRICE")));
			}
			if(!CollectionUtils.isEmpty(filters.getCapacityList())){
				criteria.createAlias("venue.venueFilters", "vf"); // inner join by default
				criteria.createAlias("vf.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getCapacityList()), Restrictions.eq("filters.filterType", "CAPACITY")));
			}
		}
			
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", searchString + "%"));
		}
		criteria.add(Restrictions.eq("city.cityId", cityId));
		return criteria;
	}

	@Override
	@Cacheable
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

	@Override
	@Cacheable
	public List<Venue> fetchRecomendations(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.isNotNull("priority"));
		criteria.addOrder(Order.asc("priority"));
		List<Venue> venues = criteria.list();
		return venues;
	}

	@Override
	public Venue loadVenueByUserId(long userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class);
		criteria.add(Restrictions.eq("user", userId));
		List<Venue> venues = criteria.list();
		if(CollectionUtils.isEmpty(venues)){
			return null;
		}
		else {
			return venues.get(0);
		}
	}
}
