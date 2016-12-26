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
	private List<AddressDTO> addresses;
	private List<VenueAmenitiesDTO> eventAreaAmenities;
	private List<Long> basicVenueServices;
	private List<VenueRoomsDTO> venueRooms;
	private List<VenuePackagesDTO> venuePackages;
	private List<EntityFiltersDTO> venueEstTypeFilters;
	private Long userId;
	private long establishmentFilterId;
	private List<AttachmentDTO> vendorAttachments;
	private List<AttachmentDTO> menuAttachments;
	private List<VenueAmenitiesDTO> caterDecorAmenities;
	private List<Long> policyVenueServices;
	private List<EntityServicesDTO> additionalVenueServices;
	private List<Long> basicCateringServices;
	private List<Long> cuisines;
	private String informationProviderContactNo;
	private String informationProviderName;
	private String website;
	private Long venueId;
	
	public VenueDTO(String venueName, String venueDescription,
			String venueType, Long city, Long locality, Long priority,
			String startingPrice, String maxCapacity, String bookingPolicy,
			String refundPolicy, String servingSince, String minCapacity,
			String informationProviderContactNo,
			String informationProviderName, String website) {
		super();
		this.venueName = venueName;
		this.venueDescription = venueDescription;
		this.venueType = venueType;
		this.city = city;
		this.locality = locality;
		this.priority = priority;
		this.startingPrice = startingPrice;
		this.maxCapacity = maxCapacity;
		this.bookingPolicy = bookingPolicy;
		this.refundPolicy = refundPolicy;
		this.servingSince = servingSince;
		this.minCapacity = minCapacity;
		this.informationProviderContactNo = informationProviderContactNo;
		this.informationProviderName = informationProviderName;
		this.website = website;
	}
	public VenueDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
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
	public List<Long> getBasicVenueServices() {
		return basicVenueServices;
	}
	public void setBasicVenueServices(List<Long> basicVenueServices) {
		this.basicVenueServices = basicVenueServices;
	}
	public List<VenueAmenitiesDTO> getCaterDecorAmenities() {
		return caterDecorAmenities;
	}
	public void setCaterDecorAmenities(List<VenueAmenitiesDTO> caterDecorAmenities) {
		this.caterDecorAmenities = caterDecorAmenities;
	}
	public List<Long> getPolicyVenueServices() {
		return policyVenueServices;
	}
	public void setPolicyVenueServices(List<Long> policyVenueServices) {
		this.policyVenueServices = policyVenueServices;
	}
	public List<VenueAmenitiesDTO> getEventAreaAmenities() {
		return eventAreaAmenities;
	}
	public void setEventAreaAmenities(List<VenueAmenitiesDTO> eventAreaAmenities) {
		this.eventAreaAmenities = eventAreaAmenities;
	}
	public List<EntityServicesDTO> getAdditionalVenueServices() {
		return additionalVenueServices;
	}
	public void setAdditionalVenueServices(
			List<EntityServicesDTO> additionalVenueServices) {
		this.additionalVenueServices = additionalVenueServices;
	}
	public List<EntityFiltersDTO> getVenueEstTypeFilters() {
		return venueEstTypeFilters;
	}
	public void setVenueEstTypeFilters(List<EntityFiltersDTO> venueEstTypeFilters) {
		this.venueEstTypeFilters = venueEstTypeFilters;
	}
	public List<Long> getBasicCateringServices() {
		return basicCateringServices;
	}
	public void setBasicCateringServices(List<Long> basicCateringServices) {
		this.basicCateringServices = basicCateringServices;
	}
	public List<Long> getCuisines() {
		return cuisines;
	}
	public void setCuisines(List<Long> cuisines) {
		this.cuisines = cuisines;
	}
	public long getEstablishmentFilterId() {
		return establishmentFilterId;
	}
	public void setEstablishmentFilterId(long establishmentFilterId) {
		this.establishmentFilterId = establishmentFilterId;
	}
	public Long getVenueId() {
		return venueId;
	}
	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}
}
