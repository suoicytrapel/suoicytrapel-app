package lepartycious.services;

import lepartycious.dtos.requestDTOs.ReviewCommentRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.ReviewCommentWrapperDTO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface ReviewCommentService {

	@Transactional(readOnly=false)
	public void submitReview(ReviewCommentRequestDTO reviewCommentRequestDTO) throws Exception;

	public ReviewCommentWrapperDTO getReviewsByVendor(SearchRequestDTO searchRequestDTO) throws Exception;

}
