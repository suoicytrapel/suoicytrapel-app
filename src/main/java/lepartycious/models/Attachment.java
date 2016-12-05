package lepartycious.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="attachment")
public class Attachment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14623547681309090L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long attachment_id;
	
	@Column(name="entity_id")
	private Long entityId;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="image_url")
	private String imageURL;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Column(name = "help_text")
	private String helpText;
	
	@Column(name = "attachment_type")
	private String attachmentType;
	
	@Column(name = "image_data")
	private byte[] imageData;

	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachment(String name, String imageURL, String helpText,
			String attachmentType, byte[] imageData) {
		super();
		this.name = name;
		this.imageURL = imageURL;
		this.helpText = helpText;
		this.attachmentType = attachmentType;
		this.imageData = imageData;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(long attachment_id) {
		this.attachment_id = attachment_id;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
}
