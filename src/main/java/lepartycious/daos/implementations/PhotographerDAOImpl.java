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

import lepartycious.daos.PhotographerDAO;
import lepartycious.models.Photographer;
import lepartycious.models.Photographer;

@Repository
public class PhotographerDAOImpl extends BaseDAOImpl implements PhotographerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Photographer> getPhotographers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder) {
		Criteria criteria = createPhotographerSearchCriteria(cityId, searchString);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Photographer> loadPhotographerList(Long cityId, String searchString) {
		Criteria criteria = createPhotographerSearchCriteria(cityId, searchString);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public Long getPhotographerCount(Long cityId, String searchString) {
		Criteria criteria = createPhotographerSearchCriteria(cityId, searchString);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createPhotographerSearchCriteria(Long cityId, String searchString) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Photographer.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		return criteria;
	}

	@Override
	public Photographer fetchPhotographerDetails(Long cityId, String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Photographer.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.eq("name", name));
		List<Photographer> photographers = criteria.list();
		if(CollectionUtils.isEmpty(photographers)){
			return null;
		}
		else{
			return photographers.get(0);
		}
	}
}
