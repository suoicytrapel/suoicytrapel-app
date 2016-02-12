package lepartycious.dtos.responseDTOs;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DetailResponseDTO implements Serializable{
	
	private String name;
	private String description;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String primaryPhoneNumber;
	private String secondaryPhoneNumber;
	private Float latitude;
	private Float longitude;
	private Map<String, List<TabResponseDTO>> tabMap;
	private List<AttachmentResponseDTO> attachments;
	private List<SearchResponseDTO> recommendationList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}
	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}
	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}
	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<SearchResponseDTO> getRecommendationList() {
		return recommendationList;
	}
	public void setRecommendationList(List<SearchResponseDTO> recommendationList) {
		this.recommendationList = recommendationList;
	}
	public Map<String, List<TabResponseDTO>> getTabMap() {
		return tabMap;
	}
	public void setTabMap(Map<String, List<TabResponseDTO>> tabMap) {
		this.tabMap = tabMap;
	}
	public List<AttachmentResponseDTO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<AttachmentResponseDTO> attachments) {
		this.attachments = attachments;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
}
