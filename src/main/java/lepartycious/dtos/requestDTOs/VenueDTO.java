package lepartycious.dtos.requestDTOs;

import java.io.Serializable;
import java.util.List;

public class VenueDTO implements Serializable{
	
	private String venueName;
	private String venueDescription;
	private String venueType;
	private Long city;
	private Long locality;
	private Long priority;
	private String startingPrice;
	private String maxCapacity;
	private String bookingPolicy;
	private String refundPolicy;
	private String servingSince;
	private String minCapacity;
	private Long establishmentFilterid;
	private List<AddressDTO> addresses;
	private List<VenueAmenitiesDTO> venueamenities;
	private List<EntityServicesDTO> venueServices;
	private List<VenueRoomsDTO> venueRooms;
	private List<VenuePackagesDTO> venuePackages;
	private List<EntityFiltersDTO> venueFilters;
	private List<AdditionalEntityServicesDTO> additionalVenueServices;
	private Long userId;
	private List<AttachmentDTO> vendorAttachments;
	private List<AttachmentDTO> menuAttachments;
	
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	public String getVenueDescription() {
		return venueDescription;
	}
	public void setVenueDescription(String venueDescription) {
		this.venueDescription = venueDescription;
	}
	public String getVenueType() {
		return venueType;
	}
	public void setVenueType(String venueType) {
		this.venueType = venueType;
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
	public String getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}
	public String getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
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
	public String getMinCapacity() {
		return minCapacity;
	}
	public void setMinCapacity(String minCapacity) {
		this.minCapacity = minCapacity;
	}
	public Long getEstablishmentFilterid() {
		return establishmentFilterid;
	}
	public void setEstablishmentFilterid(Long establishmentFilterid) {
		this.establishmentFilterid = establishmentFilterid;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	public List<VenueAmenitiesDTO> getVenueamenities() {
		return venueamenities;
	}
	public void setVenueamenities(List<VenueAmenitiesDTO> venueamenities) {
		this.venueamenities = venueamenities;
	}
	public List<EntityServicesDTO> getVenueServices() {
		return venueServices;
	}
	public void setVenueServices(List<EntityServicesDTO> venueServices) {
		this.venueServices = venueServices;
	}
	public List<VenueRoomsDTO> getVenueRooms() {
		return venueRooms;
	}
	public void setVenueRooms(List<VenueRoomsDTO> venueRooms) {
		this.venueRooms = venueRooms;
	}
	public List<VenuePackagesDTO> getVenuePackages() {
		return venuePackages;
	}
	public void setVenuePackages(List<VenuePackagesDTO> venuePackages) {
		this.venuePackages = venuePackages;
	}
	public List<EntityFiltersDTO> getVenueFilters() {
		return venueFilters;
	}
	public void setVenueFilters(List<EntityFiltersDTO> venueFilters) {
		this.venueFilters = venueFilters;
	}
	public List<AdditionalEntityServicesDTO> getAdditionalVenueServices() {
		return additionalVenueServices;
	}
	public void setAdditionalVenueServices(
			List<AdditionalEntityServicesDTO> additionalVenueServices) {
		this.additionalVenueServices = additionalVenueServices;
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
}
