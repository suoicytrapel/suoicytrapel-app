/*package lepartycious.daos.implementations;

import java.util.List;

import lepartycious.daos.SearchDAO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.models.Band;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Photographer;
import lepartycious.models.Rental;
import lepartycious.models.Venue;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDAOImpl extends BaseDAOImpl implements SearchDAO {
	
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

	private Criteria createVenueSearchCriteria(Long cityId, String searchString) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		return criteria;
	}

	@Override
	public List<Caterer> getCaterers(Long cityId, String searchString, Long offset, Long limit) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Caterer.class);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Decorator> getDecorators(Long cityId, String searchString, Long offset, Long limit) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Decorator.class);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Photographer> getPhotographers(Long cityId, String searchString, Long offset, Long limit) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Photographer.class);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Band> getBands(Long cityId, String searchString, Long offset, Long limit) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Band.class);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Rental> getRentals(Long cityId, String searchString, Long offset, Long limit) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rental.class);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public Long getVenueCount(Long cityId, String searchString) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public Long getCatererCount(Long cityid, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getPhotographerCount(Long cityid, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getBandCount(Long cityid, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getRentalCount(Long cityid, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getDecoratorCount(Long cityid, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venue> loadList(Long cityId, String searchString) {
		Criteria criteria = createVenueSearchCriteria(cityId, searchString);
		List ls =  criteria.list();
		return ls;
	}

}
*/