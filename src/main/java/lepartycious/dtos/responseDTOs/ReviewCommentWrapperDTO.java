package lepartycious.dtos.responseDTOs;

import java.util.List;

import lepartycious.dtos.requestDTOs.ReviewCommentRequestDTO;

public class ReviewCommentWrapperDTO {
	
	private Long totalCount;
	private Double averagetRating;
	private List<ReviewCommentRequestDTO> reviewComments;
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Double getAveragetRating() {
		return averagetRating;
	}
	public void setAveragetRating(Double averagetRating) {
		this.averagetRating = averagetRating;
	}
	public List<ReviewCommentRequestDTO> getReviewComments() {
		return reviewComments;
	}
	public void setReviewComments(List<ReviewCommentRequestDTO> reviewComments) {
		this.reviewComments = reviewComments;
	}
}
