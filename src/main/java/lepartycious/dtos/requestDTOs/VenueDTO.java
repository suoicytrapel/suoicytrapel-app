package lepartycious.dtos.requestDTOs;

import java.io.Serializable;
import java.util.List;

public class VenueDTO implements Serializable{
	
	private String name;
	private String description;
	private String type;
	private Long cityId;
	private Long localityId;
	private Long priority;
	private String startingPrice;
	private String maxCapacity;
	private String policies;
	private String servingSince;
	private String minCapacity;
	private Long establishmentFilterid;
	private List<AttachmentDTO> attachments;
	private List<AddressDTO> addresses;
	private List<VenueAmenitiesDTO> venueamenities;
	private List<EntityServicesDTO> venueServices;
	private List<VenueRoomsDTO> venueRooms;
	private List<VenuePackagesDTO> venuePackages;
	private List<AdditionalEntityServicesDTO> additionalVenueServices;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<AttachmentDTO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<AttachmentDTO> attachments) {
		this.attachments = attachments;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getLocalityId() {
		return localityId;
	}
	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
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
	public List<EntityFiltersDTO> getVenueFilters() {
		return venueFilters;
	}
	public void setVenueFilters(List<EntityFiltersDTO> venueFilters) {
		this.venueFilters = venueFilters;
	}
	public List<VenueRoomsDTO> getVenueRooms() {
		return venueRooms;
	}
	public void setVenueRooms(List<VenueRoomsDTO> venueRooms) {
		this.venueRooms = venueRooms;
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
	public String getPolicies() {
		return policies;
	}
	public void setPolicies(String policies) {
		this.policies = policies;
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
	public List<VenuePackagesDTO> getVenuePackages() {
		return venuePackages;
	}
	public void setVenuePackages(List<VenuePackagesDTO> venuePackages) {
		this.venuePackages = venuePackages;
	}
	public List<AdditionalEntityServicesDTO> getAdditionalVenueServices() {
		return additionalVenueServices;
	}
	public void setAdditionalVenueServices(
			List<AdditionalEntityServicesDTO> additionalVenueServices) {
		this.additionalVenueServices = additionalVenueServices;
	}
	public Long getEstablishmentFilterid() {
		return establishmentFilterid;
	}
	public void setEstablishmentFilterid(Long establishmentFilterid) {
		this.establishmentFilterid = establishmentFilterid;
	}
}
