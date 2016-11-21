package lepartycious.daos.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import lepartycious.daos.ReviewCommentDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.models.EntityReview;

@Repository
public class ReviewCommentDAOImpl implements ReviewCommentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void submitReview(EntityReview entityReview) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entityReview);
	}

	@Override
	public List<EntityReview> getReviewsByVendor(SearchRequestDTO searchRequestDTO) {
		String sortField = "addedOn";
		String sortingOrder = "desc";
		Criteria criteria = createCriteria(searchRequestDTO);
		criteria.setFirstResult(searchRequestDTO.getOffset().intValue());
		criteria.setMaxResults(searchRequestDTO.getLimit().intValue());
		if(searchRequestDTO.getSortField() != null){
			sortField = searchRequestDTO.getSortField();
			sortingOrder = searchRequestDTO.getSortOrder();
		}
		if("desc".equalsIgnoreCase(sortingOrder)){
			criteria.addOrder(Order.desc(sortField));
		}
		else{
			criteria.addOrder(Order.asc(sortField));
		}
		return criteria.list();
	}
	
	@Override
	public Double getAverageRatingOfVendor(SearchRequestDTO searchRequestDTO) {
		Criteria criteria = createCriteria(searchRequestDTO);
		criteria.setProjection(Projections.avg("starRating"));
        Double averageRating = (Double) criteria.uniqueResult();
        return averageRating;
	}
	
	@Override
	public Long getReviewCount(SearchRequestDTO searchRequestDTO) {
		Criteria criteria = createCriteria(searchRequestDTO);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private Criteria createCriteria(SearchRequestDTO searchRequestDTO) {
		Long vendorId = searchRequestDTO.getVendorId();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EntityReview.class);
		criteria.add(Restrictions.eq("entityId", vendorId));
		return criteria;
	}

}
