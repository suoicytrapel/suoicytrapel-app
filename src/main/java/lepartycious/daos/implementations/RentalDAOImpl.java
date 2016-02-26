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

import lepartycious.daos.RentalDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Caterer;
import lepartycious.models.Rental;
import lepartycious.models.Rental;

@Repository
public class RentalDAOImpl extends BaseDAOImpl implements RentalDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Rental> getRentals(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters) {
		Criteria criteria = createRentalSearchCriteria(cityId, searchString, filters);
		criteria.setFirstResult(offset.intValue());
		criteria.setMaxResults(limit.intValue());
		criteria.addOrder(Order.asc("name"));
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Rental> loadRentalList(Long cityId, String searchString) {
		Criteria criteria = createRentalSearchCriteria(cityId, searchString, null);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public Long getRentalCount(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = createRentalSearchCriteria(cityId, searchString, filters);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria createRentalSearchCriteria(Long cityId, String searchString, FilterWrapperDTO filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rental.class, "rental");
		criteria.add(Restrictions.eq("city.cityId", cityId));
		if(filters != null){
			if(!CollectionUtils.isEmpty(filters.getLocalityList())){
				criteria.add(Restrictions.in("locality.localityId", filters.getLocalityList()));
			}
			if(!CollectionUtils.isEmpty(filters.getEventList())){
				criteria.createAlias("rental.rentalFilters", "cf"); // inner join by default
				criteria.createAlias("cf.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getEventList()), Restrictions.eq("filters.filterType", "EVENT")));
			}
			if(!CollectionUtils.isEmpty(filters.getRentalList())){
				criteria.createAlias("rental.rentalFilters", "cf"); // inner join by default
				criteria.createAlias("cf.filterId", "filters");
				criteria.add(Restrictions.and(Restrictions.in("filters.filterId", filters.getRentalList()), Restrictions.eq("filters.filterType", "RENTAL")));
			}
		}
		if(StringUtils.isNotBlank(searchString)){
			criteria.add(Restrictions.ilike("name", "%" + searchString + "%"));
		}
		return criteria;
	}

	@Override
	public Rental fetchRentalDetails(Long cityId, String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rental.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.eq("name", name));
		List<Rental> rentals = criteria.list();
		if(CollectionUtils.isEmpty(rentals)){
			return null;
		}
		else{
			return rentals.get(0);
		}
	}
	
	@Override
	public List<Rental> fetchRecomendations(Long cityId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rental.class);
		criteria.add(Restrictions.eq("city.cityId", cityId));
		criteria.add(Restrictions.isNotNull("priority"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		criteria.addOrder(Order.asc("priority"));
		List<Rental> rentalList = criteria.list();
		return rentalList;
	}

}
