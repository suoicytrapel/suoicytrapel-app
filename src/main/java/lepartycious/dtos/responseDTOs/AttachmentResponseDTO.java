package lepartycious.dtos.responseDTOs;

import java.io.Serializable;

public class AttachmentResponseDTO implements Serializable{
	
	private String imageURL;
	private String helpText;
	
	public AttachmentResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttachmentResponseDTO(String imageURL, String helpText) {
		super();
		this.imageURL = imageURL;
		this.helpText = helpText;
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
}
