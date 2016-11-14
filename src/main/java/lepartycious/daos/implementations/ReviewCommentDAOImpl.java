package lepartycious.daos.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import lepartycious.daos.ReviewCommentDAO;
import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
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
	public List<EntityReview> getReviewsByVendor(Long vendorId) {
		Criteria criteria = createCriteria(vendorId);
		return criteria.list();
	}
	
	@Override
	public Double getAverageRatingOfVendor(Long vendorId) {
		Criteria criteria = createCriteria(vendorId);
		criteria.setProjection(Projections.avg("starRating"));
        Double averageRating = (Double) criteria.uniqueResult();
        return averageRating;
	}
	
	@Override
	public Long getReviewCount(Long vendorId) {
		Criteria criteria = createCriteria(vendorId);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private Criteria createCriteria(Long vendorId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EntityReview.class);
		criteria.add(Restrictions.eq("entityId", vendorId));
		return criteria;
	}

}
