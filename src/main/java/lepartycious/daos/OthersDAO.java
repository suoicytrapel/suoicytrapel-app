package lepartycious.daos;

import java.util.List;

import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Others;

public interface OthersDAO extends BaseDAO{
	
	public List<Others> getOthers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters);

	public List<Others> loadOthersList(Long cityId, String searchString);

	public Long getOthersCount(Long cityid, String searchString, FilterWrapperDTO filters);

	public Others fetchOthersDetails(Long cityId, String name);
	
	public List<Others> fetchRecomendations(Long cityId);

}
