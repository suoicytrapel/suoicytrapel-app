package lepartycious.dtos.responseDTOs;

import java.util.List;

public class SearchResponseDTO {
	
	private String name;
	private List<String> attachmentURL;
	
	public SearchResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchResponseDTO(String name, List<String> attachmentURL) {
		super();
		this.name = name;
		this.attachmentURL = attachmentURL;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAttachmentURL() {
		return attachmentURL;
	}
	public void setAttachmentURL(List<String> attachmentURL) {
		this.attachmentURL = attachmentURL;
	}
	
}
