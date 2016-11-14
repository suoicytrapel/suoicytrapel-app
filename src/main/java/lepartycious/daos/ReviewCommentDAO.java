package lepartycious.daos;

import java.util.List;

import lepartycious.models.EntityReview;

public interface ReviewCommentDAO {
	
	public void submitReview(EntityReview entityReview);
	
	public List<EntityReview> getReviewsByVendor(Long vendorId);

	Double getAverageRatingOfVendor(Long vendorId);

	Long getReviewCount(Long vendorId);

}
