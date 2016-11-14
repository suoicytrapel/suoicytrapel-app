package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lepartycious.daos.ReviewCommentDAO;
import lepartycious.dtos.requestDTOs.ReviewCommentRequestDTO;
import lepartycious.dtos.responseDTOs.ReviewCommentWrapperDTO;
import lepartycious.models.EntityReview;
import lepartycious.services.ReviewCommentService;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {
	
	@Autowired
	private ReviewCommentDAO reviewCommentDAO;

	@Override
	public void submitReview(ReviewCommentRequestDTO reviewCommentRequestDTO) {
		EntityReview entityReview = new EntityReview(reviewCommentRequestDTO.getEntityId(), 
				reviewCommentRequestDTO.getReviewedBy(), reviewCommentRequestDTO.getUserImageURL(), 
				reviewCommentRequestDTO.getReviewComment(), reviewCommentRequestDTO.getStarRating(), reviewCommentRequestDTO.getReviewMoney());
		reviewCommentDAO.submitReview(entityReview);
	}

	@Override
	public ReviewCommentWrapperDTO getReviewsByVendor(Long vendorId) {
		ReviewCommentWrapperDTO reviewCommentWrapperDTO = new ReviewCommentWrapperDTO();
		List<ReviewCommentRequestDTO> reviewCommentRequestList = new ArrayList<ReviewCommentRequestDTO>();
		Long totalCount = reviewCommentDAO.getReviewCount(vendorId);
		Double averagetRating = reviewCommentDAO.getAverageRatingOfVendor(vendorId);
		List<EntityReview> reviewCommentList = reviewCommentDAO.getReviewsByVendor(vendorId);
		for(EntityReview review : reviewCommentList){
			ReviewCommentRequestDTO reviewCommentRequestDTO = new ReviewCommentRequestDTO(review.getRatedBy(), 
					review.getImageURL(), review.getReviewComment(), review.getStarRating(), review.getEntityId());
			reviewCommentRequestDTO.setReviewMoney(review.getReviewMoney());
			reviewCommentRequestList.add(reviewCommentRequestDTO);
		}
		reviewCommentWrapperDTO.setAveragetRating(averagetRating);
		reviewCommentWrapperDTO.setTotalCount(totalCount);
		reviewCommentWrapperDTO.setReviewComments(reviewCommentRequestList);
		return reviewCommentWrapperDTO;
	}

}
