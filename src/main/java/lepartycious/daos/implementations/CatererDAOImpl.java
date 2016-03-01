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

import lepartycious.daos.CatererDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.models.Caterer;
import lepartycious.models.Caterer;
import lepartycious.models.Venue;

@Repository
@CacheConfig(cacheNames = "catererCache")
public class CatererDAOImpl extends BaseDAOImpl implements CatererDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Cacheable
	public List<Caterer> getCaterers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters) {
		Criteria criteria = createCatererSearchCriteria(cityId, searchString, filters);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public List<Caterer> loadCatererList(Long cityId, String searchString) {
		Criteria criteria = createCatererSearchCriteria(cityId, searchString, null);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public Long getCatererCount(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = createCatererSearchCriteria(cityId, searchString, filters);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private Criteria createCatererSearchCriteria(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Caterer.class, "caterer");
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(filters != null){
			if(!CollectionUtils.isEmpty(filters.getServiceList())){
				criteria.createAlias("caterer.catererServices", "cs"); // inner join by default
				criteria.createAlias("cs.serviceId", "service");
				criteria.add(Restrictions.in("service.serviceId", filters.getServiceList()));
			}
			if(!CollectionUtils.isEmpty(filters.getLocalityList())){
				criteria.add(Restrictions.in("locality.localityId", filters.getLocalityList()));
			}
			if(!CollectionUtils.isEmpty(filters.getEventList())){
				criteria.createAlias("caterer.catererFilters", "cf"); // inner join by default
				criteria.createAlias("cf.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getEventList()), Restrictions.eq("filters.filterType", "EVENT")));
			}
			if(!CollectionUtils.isEmpty(filters.getPriceRangeList())){
				criteria.createAlias("caterer.catererFilters", "cf"); // inner join by default
				criteria.createAlias("cf.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getPriceRangeList()), Restrictions.eq("filters.filterType", "PRICE")));
			}
		}
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		return criteria;
	}

	@Override
	@Cacheable
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

	@Override
	@Cacheable
	public List<Caterer> fetchRecomendations(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Caterer.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.isNotNull("priority"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		criteria.addOrder(Order.asc("priority"));
		List<Caterer> catererList = criteria.list();
		return catererList;
	}

}
