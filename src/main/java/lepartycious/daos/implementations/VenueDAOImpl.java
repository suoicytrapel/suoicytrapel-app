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

import lepartycious.daos.VenueDAO;
import lepartycious.models.Venue;

@Repository
public class VenueDAOImpl extends BaseDAOImpl implements VenueDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Venue> getVenues(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Venue> loadVenueList(Long cityId, String searchString) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public Long getVenueCount(Long cityId, String searchString) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createVenueSearchCriteria(Long cityId, String searchString) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		return criteria;
	}


}
