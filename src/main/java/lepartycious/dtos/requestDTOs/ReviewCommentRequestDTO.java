package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class ReviewCommentRequestDTO implements Serializable{
	
	private String reviewedBy;
	private String userImageURL;
	private String reviewComment;
	private Float starRating;
	private Long entityId;
	private String reviewMoney;
	
	public ReviewCommentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReviewCommentRequestDTO(String reviewedBy, String userImageURL,
			String reviewComment, Float starRating, Long entityId) {
		super();
		this.reviewedBy = reviewedBy;
		this.userImageURL = userImageURL;
		this.reviewComment = reviewComment;
		this.starRating = starRating;
		this.entityId = entityId;
	}
	public String getReviewedBy() {
		return reviewedBy;
	}
	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}
	public String getUserImageURL() {
		return userImageURL;
	}
	public void setUserImageURL(String userImageURL) {
		this.userImageURL = userImageURL;
	}
	public String getReviewComment() {
		return reviewComment;
	}
	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
	public Float getStarRating() {
		return starRating;
	}
	public void setStarRating(Float starRating) {
		this.starRating = starRating;
	}
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getReviewMoney() {
		return reviewMoney;
	}

	public void setReviewMoney(String reviewMoney) {
		this.reviewMoney = reviewMoney;
	}
	
}
