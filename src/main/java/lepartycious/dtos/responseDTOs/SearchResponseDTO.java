package lepartycious.dtos.responseDTOs;

import java.util.List;

public class SearchResponseDTO {
	
	private String name;
	private List<String> attachmentURL;
	private String mainImagerURL;
	private String startingPrice;
	private String locality;
	private String maxCapacity;
	private Boolean isPureVeg;
	private String minCapacity;
	private Double averageRating;
	
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
	public String getMainImagerURL() {
		return mainImagerURL;
	}
	public void setMainImagerURL(String mainImagerURL) {
		this.mainImagerURL = mainImagerURL;
	}
	public String getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public Boolean getIsPureVeg() {
		return isPureVeg;
	}
	public void setIsPureVeg(Boolean isPureVeg) {
		this.isPureVeg = isPureVeg;
	}
	public String getMinCapacity() {
		return minCapacity;
	}
	public void setMinCapacity(String minCapacity) {
		this.minCapacity = minCapacity;
	}
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
}
