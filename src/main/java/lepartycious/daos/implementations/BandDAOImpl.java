package lepartycious.daos.implementations;

import java.util.List;

import lepartycious.daos.BandDAO;
import lepartycious.models.Band;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class BandDAOImpl extends BaseDAOImpl implements BandDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Band> getBands(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder) {
		Criteria criteria = createBandSearchCriteria(cityId, searchString);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Band> loadBandList(Long cityId, String searchString) {
		Criteria criteria = createBandSearchCriteria(cityId, searchString);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public Long getBandCount(Long cityId, String searchString) {
		Criteria criteria = createBandSearchCriteria(cityId, searchString);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createBandSearchCriteria(Long cityId, String searchString) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Band.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		return criteria;
	}

	@Override
	public Band fetchBandDetails(Long cityId, String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Band.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.eq("name", name));
		List<Band> bands = criteria.list();
		if(CollectionUtils.isEmpty(bands)){
			return null;
		}
		else{
			return bands.get(0);
		}
	}

}
