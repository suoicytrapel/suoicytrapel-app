package lepartycious.dtos.requestDTOs;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import lepartycious.models.EntityServices;

public class CatererDTO implements Serializable{
	
	private String name;
	private String description;
	private Long city;
	private Long locality;
	private Long priority;
	private String bookingPolicy;
	private String refundPolicy;
	private String servingSince;
	private Boolean isPureVeg;
	private String startingPriceVeg;
	private String maxCapacity;
	private String minCapacity;
	private String startingPriceNonVeg;
	private List<AddressDTO> addresses;
	private Long userId;
	private List<AttachmentDTO> vendorAttachments;
	private List<AttachmentDTO> menuAttachments;
	private List<Long> catererServices;
	private String informationProviderContactNo;
	private String informationProviderName;
	private List<Long> cuisines;
	private List<Long> catererType;
	private List<Long> catererPrice;
	private List<Long> catererEvent;
	private List<Long> catererCapacity;
	private String website;
	private Long catererId;
	
	public CatererDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CatererDTO(String name, String description, Long city,
			Long locality, Long priority, String bookingPolicy,
			String refundPolicy, String servingSince, String startingPriceVeg,
			String maxCapacity, String minCapacity, String startingPriceNonVeg,
			String informationProviderContactNo,
			String informationProviderName, String website, Long catererId) {
		super();
		this.name = name;
		this.description = description;
		this.city = city;
		this.locality = locality;
		this.priority = priority;
		this.bookingPolicy = bookingPolicy;
		this.refundPolicy = refundPolicy;
		this.servingSince = servingSince;
		this.startingPriceVeg = startingPriceVeg;
		this.maxCapacity = maxCapacity;
		this.minCapacity = minCapacity;
		this.startingPriceNonVeg = startingPriceNonVeg;
		this.informationProviderContactNo = informationProviderContactNo;
		this.informationProviderName = informationProviderName;
		this.website = website;
		this.catererId = catererId;
	}
	public Long getCatererId() {
		return catererId;
	}
	public void setCatererId(Long catererId) {
		this.catererId = catererId;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public List<Long> getCuisines() {
		return cuisines;
	}
	public void setCuisines(List<Long> cuisines) {
		this.cuisines = cuisines;
	}
	public String getInformationProviderContactNo() {
		return informationProviderContactNo;
	}
	public void setInformationProviderContactNo(String informationProviderContactNo) {
		this.informationProviderContactNo = informationProviderContactNo;
	}
	public String getInformationProviderName() {
		return informationProviderName;
	}
	public void setInformationProviderName(String informationProviderName) {
		this.informationProviderName = informationProviderName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public Long getLocality() {
		return locality;
	}
	public void setLocality(Long locality) {
		this.locality = locality;
	}
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	public String getBookingPolicy() {
		return bookingPolicy;
	}
	public void setBookingPolicy(String bookingPolicy) {
		this.bookingPolicy = bookingPolicy;
	}
	public String getRefundPolicy() {
		return refundPolicy;
	}
	public void setRefundPolicy(String refundPolicy) {
		this.refundPolicy = refundPolicy;
	}
	public String getServingSince() {
		return servingSince;
	}
	public void setServingSince(String servingSince) {
		this.servingSince = servingSince;
	}
	public Boolean getIsPureVeg() {
		return isPureVeg;
	}
	public void setIsPureVeg(Boolean isPureVeg) {
		this.isPureVeg = isPureVeg;
	}
	public String getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public String getMinCapacity() {
		return minCapacity;
	}
	public void setMinCapacity(String minCapacity) {
		this.minCapacity = minCapacity;
	}
	public String getStartingPriceNonVeg() {
		return startingPriceNonVeg;
	}
	public void setStartingPriceNonVeg(String startingPriceNonVeg) {
		this.startingPriceNonVeg = startingPriceNonVeg;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<AttachmentDTO> getVendorAttachments() {
		return vendorAttachments;
	}
	public void setVendorAttachments(List<AttachmentDTO> vendorAttachments) {
		this.vendorAttachments = vendorAttachments;
	}
	public List<AttachmentDTO> getMenuAttachments() {
		return menuAttachments;
	}
	public void setMenuAttachments(List<AttachmentDTO> menuAttachments) {
		this.menuAttachments = menuAttachments;
	}
	public List<Long> getCatererServices() {
		return catererServices;
	}
	public void setCatererServices(List<Long> catererServices) {
		this.catererServices = catererServices;
	}
	public String getStartingPriceVeg() {
		return startingPriceVeg;
	}
	public void setStartingPriceVeg(String startingPriceVeg) {
		this.startingPriceVeg = startingPriceVeg;
	}
	public List<Long> getCatererType() {
		return catererType;
	}
	public void setCatererType(List<Long> catererType) {
		this.catererType = catererType;
	}
	public List<Long> getCatererPrice() {
		return catererPrice;
	}
	public void setCatererPrice(List<Long> catererPrice) {
		this.catererPrice = catererPrice;
	}
	public List<Long> getCatererEvent() {
		return catererEvent;
	}
	public void setCatererEvent(List<Long> catererEvent) {
		this.catererEvent = catererEvent;
	}
	public List<Long> getCatererCapacity() {
		return catererCapacity;
	}
	public void setCatererCapacity(List<Long> catererCapacity) {
		this.catererCapacity = catererCapacity;
	}
}
