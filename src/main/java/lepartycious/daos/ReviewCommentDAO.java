package lepartycious.daos;

import java.util.List;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.models.EntityReview;

public interface ReviewCommentDAO {
	
	public void submitReview(EntityReview entityReview);
	
	public List<EntityReview> getReviewsByVendor(SearchRequestDTO searchRequestDTO);

	Double getAverageRatingOfVendor(SearchRequestDTO searchRequestDTO);

	Long getReviewCount(SearchRequestDTO searchRequestDTO);

}
