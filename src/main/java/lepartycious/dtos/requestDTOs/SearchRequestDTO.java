package lepartycious.dtos.requestDTOs;

import java.io.Serializable;
import java.util.List;

import lepartycious.dtos.responseDTOs.FilterResponseDTO;

public class SearchRequestDTO implements Serializable{
	
	private String searchType;
	private String searchString;
	private Long cityId;
	private Long offset;
	private Long limit;
	private String sortOrder;
	private String sortField;
	private FilterWrapperDTO filters;
	private String vendorName;
	private Long vendorId;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getOffset() {
		return offset;
	}
	public void setOffset(Long offset) {
		this.offset = offset;
	}
	public Long getLimit() {
		return limit;
	}
	public void setLimit(Long limit) {
		this.limit = limit;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public FilterWrapperDTO getFilters() {
		return filters;
	}
	public void setFilters(FilterWrapperDTO filters) {
		this.filters = filters;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
}
