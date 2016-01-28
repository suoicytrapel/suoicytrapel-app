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

import lepartycious.daos.DecoratorDAO;
import lepartycious.models.Decorator;
import lepartycious.models.Decorator;

@Repository
public class DecoratorDAOImpl extends BaseDAOImpl implements DecoratorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Decorator> getDecorators(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder) {
		Criteria criteria = createDecoratorSearchCriteria(cityId, searchString);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Decorator> loadDecoratorList(Long cityId, String searchString) {
		Criteria criteria = createDecoratorSearchCriteria(cityId, searchString);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public Long getDecoratorCount(Long cityId, String searchString) {
		Criteria criteria = createDecoratorSearchCriteria(cityId, searchString);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createDecoratorSearchCriteria(Long cityId, String searchString) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Decorator.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		return criteria;
	}

	@Override
	public Decorator fetchDecoratorDetails(Long cityId, String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Decorator.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.eq("name", name));
		List<Decorator> decorators = criteria.list();
		if(CollectionUtils.isEmpty(decorators)){
			return null;
		}
		else{
			return decorators.get(0);
		}
	}

}
