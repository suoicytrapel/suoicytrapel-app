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
	private List<AttachmentResponseDTO> attachments;
	private List<SearchResponseDTO> recommendationList;
	private List<AttachmentResponseDTO> menuImages;
	private String maxCapacity;
	private String servingSince;
	private String genre;
	private Boolean isGroup;
	private String startingFrom;
	private String minNonVegPrice;
	private String minCapacity;
	private String policies;
	private Map<String, List<TabResponseDTO>> serviceAmenityTabMap;
	private Map<String, List<TabResponseDTO>> amenityDetailsTabMap;
	private TabResponseDTO additionalInfo;
	private Map<String, TabResponseDTO> inHouseOfferingsTabMap;
	private Boolean isPureVeg;
	
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
	public List<AttachmentResponseDTO> getMenuImages() {
		return menuImages;
	}
	public void setMenuImages(List<AttachmentResponseDTO> menuImages) {
		this.menuImages = menuImages;
	}
	public String getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public String getServingSince() {
		return servingSince;
	}
	public void setServingSince(String servingSince) {
		this.servingSince = servingSince;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Boolean isGroup() {
		return isGroup;
	}
	public void setGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}
	public String getStartingFrom() {
		return startingFrom;
	}
	public void setStartingFrom(String startingFrom) {
		this.startingFrom = startingFrom;
	}
	public String getMinNonVegPrice() {
		return minNonVegPrice;
	}
	public void setMinNonVegPrice(String minNonVegPrice) {
		this.minNonVegPrice = minNonVegPrice;
	}
	public String getMinCapacity() {
		return minCapacity;
	}
	public void setMinCapacity(String minCapacity) {
		this.minCapacity = minCapacity;
	}
	public String getPolicies() {
		return policies;
	}
	public void setPolicies(String policies) {
		this.policies = policies;
	}
	public Map<String, List<TabResponseDTO>> getServiceAmenityTabMap() {
		return serviceAmenityTabMap;
	}
	public void setServiceAmenityTabMap(
			Map<String, List<TabResponseDTO>> serviceAmenityTabMap) {
		this.serviceAmenityTabMap = serviceAmenityTabMap;
	}
	public Boolean getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}
	public Map<String, List<TabResponseDTO>> getAmenityDetailsTabMap() {
		return amenityDetailsTabMap;
	}
	public void setAmenityDetailsTabMap(
			Map<String, List<TabResponseDTO>> amenityDetailsTabMap) {
		this.amenityDetailsTabMap = amenityDetailsTabMap;
	}
	public Map<String, TabResponseDTO> getInHouseOfferingsTabMap() {
		return inHouseOfferingsTabMap;
	}
	public void setInHouseOfferingsTabMap(
			Map<String, TabResponseDTO> inHouseOfferingsTabMap) {
		this.inHouseOfferingsTabMap = inHouseOfferingsTabMap;
	}
	public Boolean getIsPureVeg() {
		return isPureVeg;
	}
	public void setIsPureVeg(Boolean isPureVeg) {
		this.isPureVeg = isPureVeg;
	}
	public TabResponseDTO getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(TabResponseDTO additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}
