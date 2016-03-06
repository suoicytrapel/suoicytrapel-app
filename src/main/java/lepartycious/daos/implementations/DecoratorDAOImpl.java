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

import lepartycious.daos.DecoratorDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Caterer;
import lepartycious.models.Decorator;
import lepartycious.models.Decorator;

@Repository
@CacheConfig(cacheNames = "decoratorCache")
public class DecoratorDAOImpl extends BaseDAOImpl implements DecoratorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Cacheable
	public List<Decorator> getDecorators(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters) {
		Criteria criteria = createDecoratorSearchCriteria(cityId, searchString, filters);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List<Decorator> ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public List<Decorator> loadDecoratorList(Long cityId, String searchString) {
		Criteria criteria = createDecoratorSearchCriteria(cityId, searchString, null);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public Long getDecoratorCount(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = createDecoratorSearchCriteria(cityId, searchString, filters);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createDecoratorSearchCriteria(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Decorator.class, "decorator");
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(filters != null){
			if(!CollectionUtils.isEmpty(filters.getServiceList())){
				criteria.createAlias("decorator.decoratorServices", "ds"); // inner join by default
				criteria.createAlias("ds.serviceId", "service");
				criteria.add(Restrictions.in("service.serviceId", filters.getServiceList()));
			}
			if(!CollectionUtils.isEmpty(filters.getLocalityList())){
				criteria.add(Restrictions.in("locality.localityId", filters.getLocalityList()));
			}
			if(!CollectionUtils.isEmpty(filters.getEventList())){
				criteria.createAlias("decorator.decoratorFilters", "df"); // inner join by default
				criteria.createAlias("df.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getEventList()), Restrictions.eq("filters.filterType", "EVENT")));
			}
		}
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", searchString + "%"));
		}
		return criteria;
	}

	@Override
	@Cacheable
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

	@Override
	@Cacheable
	public List<Decorator> fetchRecomendations(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Decorator.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.isNotNull("priority"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		criteria.addOrder(Order.asc("priority"));
		List<Decorator> decoratorList = criteria.list();
		return decoratorList;
	}

}
