package lepartycious.dtos.requestDTOs;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import lepartycious.models.EntityServices;

public class DecoratorDTO implements Serializable{
	
	private String name;
	private String description;
	private Long city;
	private Long locality;
	private Long priority;
	private String bookingPolicy;
	private String refundPolicy;
	private String servingSince;
	private String startingPrice;
	private List<AddressDTO> addresses;
	private Long userId;
	private List<AttachmentDTO> vendorAttachments;
	private List<Long> decoratorServices;
	private String informationProviderContactNo;
	private String informationProviderName;
	private String website;
	
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
	public String getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
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
	public List<Long> getDecoratorServices() {
		return decoratorServices;
	}
	public void setDecoratorServices(List<Long> decoratorServices) {
		this.decoratorServices = decoratorServices;
	}
}
