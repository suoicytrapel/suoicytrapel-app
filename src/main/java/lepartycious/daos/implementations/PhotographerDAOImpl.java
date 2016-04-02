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

import lepartycious.daos.PhotographerDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Caterer;
import lepartycious.models.Photographer;
import lepartycious.models.Photographer;

@Repository
@CacheConfig(cacheNames = "photographerCache")
public class PhotographerDAOImpl extends BaseDAOImpl implements PhotographerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Cacheable
	public List<Photographer> getPhotographers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters) {
		Criteria criteria = createPhotographerSearchCriteria(cityId, searchString, filters);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public List<Photographer> loadPhotographerList(Long cityId, String searchString) {
		Criteria criteria = createPhotographerSearchCriteria(cityId, searchString, null);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public Long getPhotographerCount(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = createPhotographerSearchCriteria(cityId, searchString, filters);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createPhotographerSearchCriteria(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Photographer.class, "photographer");
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(filters != null){
			if(!CollectionUtils.isEmpty(filters.getLocalityList())){
				criteria.add(Restrictions.in("locality.localityId", filters.getLocalityList()));
			}
			if(!CollectionUtils.isEmpty(filters.getEventList())){
				criteria.createAlias("photographer.photographerFilters", "ff"); // inner join by default
				criteria.createAlias("ff.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getEventList()), Restrictions.eq("filters.filterType", "EVENT")));
			}
			if(!CollectionUtils.isEmpty(filters.getPriceRangeList())){
				criteria.createAlias("photographer.photographerFilters", "ff"); // inner join by default
				criteria.createAlias("ff.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getPriceRangeList()), Restrictions.eq("filters.filterType", "PRICE")));
			}
		}
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", searchString + "%"));
		}
		return criteria;
	}

	@Override
	@Cacheable
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

	@Override
	@Cacheable
	public List<Photographer> fetchRecomendations(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Photographer.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.isNotNull("priority"));
		criteria.addOrder(Order.asc("priority"));
		List<Photographer> photographerList = criteria.list();
		return photographerList;
	}
}
