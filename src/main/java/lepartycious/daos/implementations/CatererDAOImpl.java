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

import lepartycious.daos.CatererDAO;
import lepartycious.models.Caterer;
import lepartycious.models.Caterer;

@Repository
public class CatererDAOImpl extends BaseDAOImpl implements CatererDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Caterer> getCaterers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder) {
		Criteria criteria = createCatererSearchCriteria(cityId, searchString);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Caterer> loadCatererList(Long cityId, String searchString) {
		Criteria criteria = createCatererSearchCriteria(cityId, searchString);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public Long getCatererCount(Long cityId, String searchString) {
		Criteria criteria = createCatererSearchCriteria(cityId, searchString);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createCatererSearchCriteria(Long cityId, String searchString) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Caterer.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		return criteria;
	}

	@Override
	public Caterer fetchCatererDetails(Long cityId, String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Caterer.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.eq("name", name));
		List<Caterer> caterers = criteria.list();
		if(CollectionUtils.isEmpty(caterers)){
			return null;
		}
		else{
			return caterers.get(0);
		}
	}

}
