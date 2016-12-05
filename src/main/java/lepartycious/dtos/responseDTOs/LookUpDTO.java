package lepartycious.dtos.responseDTOs;

import java.io.Serializable;
import java.util.List;

import lepartycious.dtos.requestDTOs.AdditionalEntityServicesDTO;
import lepartycious.dtos.requestDTOs.EntityServicesDTO;
import lepartycious.dtos.requestDTOs.VenueAmenitiesDTO;
import lepartycious.dtos.requestDTOs.VenueRoomsDTO;

public class LookUpDTO implements Serializable{
	
	private List<VenueRoomsDTO> roomLookUpMap;
	private List<FilterResponseDTO> typeLookUp;
	
	private List<VenueAmenitiesDTO> eventAreaAmenityLookUpMap;
	private List<EntityServicesDTO> basicServiceLookUpMap;
	private List<EntityServicesDTO> additionalServiceLookUpMap;
	private List<EntityServicesDTO> policyServicelookUp;
	private List<VenueAmenitiesDTO> cateringDecorAmenityLookUp;
	
	public List<EntityServicesDTO> getBasicServiceLookUpMap() {
		return basicServiceLookUpMap;
	}
	public void setBasicServiceLookUpMap(
			List<EntityServicesDTO> basicServiceLookUpMap) {
		this.basicServiceLookUpMap = basicServiceLookUpMap;
	}
	public List<EntityServicesDTO> getAdditionalServiceLookUpMap() {
		return additionalServiceLookUpMap;
	}
	public void setAdditionalServiceLookUpMap(
			List<EntityServicesDTO> additionalServiceLookUpMap) {
		this.additionalServiceLookUpMap = additionalServiceLookUpMap;
	}
	public List<EntityServicesDTO> getPolicyServicelookUp() {
		return policyServicelookUp;
	}
	public void setPolicyServicelookUp(List<EntityServicesDTO> policyServicelookUp) {
		this.policyServicelookUp = policyServicelookUp;
	}
	public List<VenueRoomsDTO> getRoomLookUpMap() {
		return roomLookUpMap;
	}
	public void setRoomLookUpMap(List<VenueRoomsDTO> roomLookUpMap) {
		this.roomLookUpMap = roomLookUpMap;
	}
	public List<FilterResponseDTO> getTypeLookUp() {
		return typeLookUp;
	}
	public void setTypeLookUp(List<FilterResponseDTO> typeLookUp) {
		this.typeLookUp = typeLookUp;
	}
	public List<VenueAmenitiesDTO> getEventAreaAmenityLookUpMap() {
		return eventAreaAmenityLookUpMap;
	}
	public void setEventAreaAmenityLookUpMap(
			List<VenueAmenitiesDTO> eventAreaAmenityLookUpMap) {
		this.eventAreaAmenityLookUpMap = eventAreaAmenityLookUpMap;
	}
	public List<VenueAmenitiesDTO> getCateringDecorAmenityLookUp() {
		return cateringDecorAmenityLookUp;
	}
	public void setCateringDecorAmenityLookUp(
			List<VenueAmenitiesDTO> cateringDecorAmenityLookUp) {
		this.cateringDecorAmenityLookUp = cateringDecorAmenityLookUp;
	}
}
