package lepartycious.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="entity_review")
public class EntityReview implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14623547632769090L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="entity_id")
	private Long entityId;
	
	@Column(name="reviewed_by", nullable=false)
	private String ratedBy;
	
	@Column(name="user_image_url")
	private String imageURL;
	
	@Column(name = "reviewed_on")
	private Date addedOn;
	
	@Column(name = "review_comment")
	private String reviewComment;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@Column(name = "star_rating")
	private Float starRating;

	
	public EntityReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityReview(Long entityId, String ratedBy, String imageURL,
			String reviewComment, Float starRating) {
		super();
		this.entityId = entityId;
		this.ratedBy = ratedBy;
		this.imageURL = imageURL;
		this.reviewComment = reviewComment;
		this.starRating = starRating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getRatedBy() {
		return ratedBy;
	}

	public void setRatedBy(String ratedBy) {
		this.ratedBy = ratedBy;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Float getStarRating() {
		return starRating;
	}

	public void setStarRating(Float starRating) {
		this.starRating = starRating;
	}
}
