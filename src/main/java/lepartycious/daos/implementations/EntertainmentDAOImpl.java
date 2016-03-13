package lepartycious.daos.implementations;

import java.util.List;

import lepartycious.daos.EntertainmentDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Entertainment;

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
@CacheConfig(cacheNames = "entertainmentCache")
public class EntertainmentDAOImpl extends BaseDAOImpl implements EntertainmentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Cacheable
	public List<Entertainment> getRentals(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters) {
		Criteria criteria = createRentalSearchCriteria(cityId, searchString, filters);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public List<Entertainment> loadRentalList(Long cityId, String searchString) {
		Criteria criteria = createRentalSearchCriteria(cityId, searchString, null);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	@Cacheable
	public Long getRentalCount(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = createRentalSearchCriteria(cityId, searchString, filters);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createRentalSearchCriteria(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Entertainment.class, "rental");
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(filters != null){
			if(!CollectionUtils.isEmpty(filters.getLocalityList())){
				criteria.add(Restrictions.in("locality.localityId", filters.getLocalityList()));
			}
			if(!CollectionUtils.isEmpty(filters.getEventList())){
				criteria.createAlias("rental.filters", "cf"); // inner join by default
				criteria.createAlias("cf.filterId", "filter");
				criteria.add(Restrictions.and(Restrictions.in("filter.filterId", filters.getEventList()), Restrictions.eq("filter.filterType", "EVENT")));
			}
			if(!CollectionUtils.isEmpty(filters.getRentalList())){
				criteria.createAlias("rental.filters", "cf"); // inner join by default
				criteria.createAlias("cf.filterId", "filter");
				criteria.add(Restrictions.and(Restrictions.in("filter.filterId", filters.getRentalList()), Restrictions.eq("filter.filterType", "ENTERTAINMENT")));
			}
		}
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", searchString + "%"));
		}
		return criteria;
	}

	@Override
	@Cacheable
	public Entertainment fetchRentalDetails(Long cityId, String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Entertainment.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.eq("name", name));
		List<Entertainment> rentals = criteria.list();
		if(CollectionUtils.isEmpty(rentals)){
			return null;
		}
		else{
			return rentals.get(0);
		}
	}
	
	@Override
	@Cacheable
	public List<Entertainment> fetchRecomendations(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Entertainment.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.isNotNull("priority"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		criteria.addOrder(Order.asc("priority"));
		List<Entertainment> rentalList = criteria.list();
		return rentalList;
	}

}
