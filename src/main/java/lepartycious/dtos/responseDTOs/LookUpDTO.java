package lepartycious.dtos.responseDTOs;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lepartycious.dtos.requestDTOs.AdditionalEntityServicesDTO;
import lepartycious.dtos.requestDTOs.EntityFiltersDTO;
import lepartycious.dtos.requestDTOs.EntityServicesDTO;
import lepartycious.dtos.requestDTOs.VenueAmenitiesDTO;
import lepartycious.dtos.requestDTOs.VenueRoomsDTO;

public class LookUpDTO implements Serializable{
	
	private List<VenueRoomsDTO> roomLookUpMap;
	private List<FilterResponseDTO> typeLookUp;
	private List<VenueAmenitiesDTO> eventAreaAmenityLookUpMap;
	private List<EntityServicesDTO> basicServiceLookUpMap;
	private List<EntityServicesDTO> additionalServiceLookUpMap;
	private List<EntityServicesDTO> policyServiceLookUp;
	private List<VenueAmenitiesDTO> cateringDecorAmenityLookUp;
	private List<EntityServicesDTO> basicCateringServicesLookUp;
	private List<EntityServicesDTO> cuisinesLookUp;
	private List<EntityFiltersDTO> entertainmentType;
	private List<EntityFiltersDTO> othersType;
	private List<EntityServicesDTO> catererServicesLookUp;
	private List<EntityServicesDTO> decoratorServicesLookUp;
	private List<EntityServicesDTO> photographerServicesLookUp;
	private List<EntityServicesDTO> entertainmentServicesLookUp;
	private List<EntityServicesDTO> othersServicesLookUp;
	
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
	public List<EntityServicesDTO> getPolicyServiceLookUp() {
		return policyServiceLookUp;
	}
	public void setPolicyServiceLookUp(List<EntityServicesDTO> policyServiceLookUp) {
		this.policyServiceLookUp = policyServiceLookUp;
	}
	public List<EntityServicesDTO> getBasicCateringServicesLookUp() {
		return basicCateringServicesLookUp;
	}
	public void setBasicCateringServicesLookUp(
			List<EntityServicesDTO> basicCateringServicesLookUp) {
		this.basicCateringServicesLookUp = basicCateringServicesLookUp;
	}
	public List<EntityServicesDTO> getCuisinesLookUp() {
		return cuisinesLookUp;
	}
	public void setCuisinesLookUp(List<EntityServicesDTO> cuisinesLookUp) {
		this.cuisinesLookUp = cuisinesLookUp;
	}
	public List<EntityFiltersDTO> getEntertainmentType() {
		return entertainmentType;
	}
	public void setEntertainmentType(List<EntityFiltersDTO> entertainmentType) {
		this.entertainmentType = entertainmentType;
	}
	public List<EntityFiltersDTO> getOthersType() {
		return othersType;
	}
	public void setOthersType(List<EntityFiltersDTO> othersType) {
		this.othersType = othersType;
	}
	public List<EntityServicesDTO> getCatererServicesLookUp() {
		return catererServicesLookUp;
	}
	public void setCatererServicesLookUp(
			List<EntityServicesDTO> catererServicesLookUp) {
		this.catererServicesLookUp = catererServicesLookUp;
	}
	public List<EntityServicesDTO> getDecoratorServicesLookUp() {
		return decoratorServicesLookUp;
	}
	public void setDecoratorServicesLookUp(
			List<EntityServicesDTO> decoratorServicesLookUp) {
		this.decoratorServicesLookUp = decoratorServicesLookUp;
	}
	public List<EntityServicesDTO> getPhotographerServicesLookUp() {
		return photographerServicesLookUp;
	}
	public void setPhotographerServicesLookUp(
			List<EntityServicesDTO> photographerServicesLookUp) {
		this.photographerServicesLookUp = photographerServicesLookUp;
	}
	public List<EntityServicesDTO> getEntertainmentServicesLookUp() {
		return entertainmentServicesLookUp;
	}
	public void setEntertainmentServicesLookUp(
			List<EntityServicesDTO> entertainmentServicesLookUp) {
		this.entertainmentServicesLookUp = entertainmentServicesLookUp;
	}
	public List<EntityServicesDTO> getOthersServicesLookUp() {
		return othersServicesLookUp;
	}
	public void setOthersServicesLookUp(List<EntityServicesDTO> othersServicesLookUp) {
		this.othersServicesLookUp = othersServicesLookUp;
	}
}
