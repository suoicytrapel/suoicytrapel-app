package lepartycious.dtos.requestDTOs;

import java.io.Serializable;
import java.util.Date;

public class ReviewCommentRequestDTO implements Serializable{
	
	private String reviewedBy;
	private String userImageURL;
	private String reviewComment;
	private Float starRating;
	private String reviewMoney;
	private String vendorName;
	private String vendorType;
	private String reviewedOn;
	
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
	public String getReviewMoney() {
		return reviewMoney;
	}

	public void setReviewMoney(String reviewMoney) {
		this.reviewMoney = reviewMoney;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getReviewedOn() {
		return reviewedOn;
	}

	public void setReviewedOn(String reviewedOn) {
		this.reviewedOn = reviewedOn;
	}
}
