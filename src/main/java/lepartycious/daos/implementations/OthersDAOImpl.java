package lepartycious.daos.implementations;

import java.util.List;

import lepartycious.daos.OthersDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Others;

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

@Repository
@CacheConfig(cacheNames = "othersCache")
public class OthersDAOImpl extends BaseDAOImpl implements OthersDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Cacheable
	public List<Others> getOthers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters) {
		Criteria criteria = createOthersSearchCriteria(cityId, searchString, filters);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public List<Others> loadOthersList(Long cityId, String searchString) {
		Criteria criteria = createOthersSearchCriteria(cityId, searchString, null);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public Long getOthersCount(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = createOthersSearchCriteria(cityId, searchString, filters);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createOthersSearchCriteria(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Others.class, "others");
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(filters != null){
			if(!CollectionUtils.isEmpty(filters.getLocalityList())){
				criteria.add(Restrictions.in("locality.localityId", filters.getLocalityList()));
			}
			if(!CollectionUtils.isEmpty(filters.getEventList())){
				criteria.createAlias("others.filters", "cf"); // inner join by default
				criteria.createAlias("cf.filterId", "filter");
				criteria.add(Restrictions.and(Restrictions.in("filter.filterId", filters.getEventList()), Restrictions.eq("filter.filterType", "EVENT")));
			}
			if(!CollectionUtils.isEmpty(filters.getOthersList())){
				criteria.createAlias("others.filters", "cf"); // inner join by default
				criteria.createAlias("cf.filterId", "filter");
				criteria.add(Restrictions.and(Restrictions.in("filter.filterId", filters.getOthersList()), Restrictions.eq("filter.filterType", "OTHERS")));
			}
		}
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", searchString + "%"));
		}
		return criteria;
	}

	@Override
	@Cacheable
	public Others fetchOthersDetails(Long cityId, String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Others.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.eq("name", name));
		List<Others> rentals = criteria.list();
		if(CollectionUtils.isEmpty(rentals)){
			return null;
		}
		else{
			return rentals.get(0);
		}
	}
	
	@Override
	@Cacheable
	public List<Others> fetchRecomendations(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Others.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.isNotNull("priority"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		criteria.addOrder(Order.asc("priority"));
		List<Others> rentalList = criteria.list();
		return rentalList;
	}

}
