package lepartycious.daos;

import java.util.List;

import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.models.Caterer;

public interface CatererDAO extends BaseDAO{

	public List<Caterer> getCaterers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters);

	public List<Caterer> loadCatererList(Long cityId, String searchString);

	public Long getCatererCount(Long cityid, String searchString, FilterWrapperDTO filters);

	public Caterer fetchCatererDetails(Long cityId, String name);
	
	public List<Caterer> fetchRecomendations(Long cityId);

	public Caterer loadCatererByName(String wizardName);

}
