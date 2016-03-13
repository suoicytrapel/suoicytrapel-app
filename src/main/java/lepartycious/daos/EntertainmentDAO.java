package lepartycious.daos;

import java.util.List;

import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Entertainment;

public interface EntertainmentDAO extends BaseDAO{
	
	public List<Entertainment> getRentals(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters);

	public List<Entertainment> loadRentalList(Long cityId, String searchString);

	public Long getRentalCount(Long cityid, String searchString, FilterWrapperDTO filters);

	public Entertainment fetchRentalDetails(Long cityId, String name);
	
	public List<Entertainment> fetchRecomendations(Long cityId);

}
