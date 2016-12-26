package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class AttachmentDTO implements Serializable{
	
	private String name;
	private String imageURL;
	private String helpText;
	private String attachmentType;
	private byte[] imageData;
	
	public AttachmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttachmentDTO(String name, String imageURL, String helpText,
			String attachmentType, byte[] imageData) {
		super();
		this.name = name;
		this.imageURL = imageURL;
		this.helpText = helpText;
		this.attachmentType = attachmentType;
		this.imageData = imageData;
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
