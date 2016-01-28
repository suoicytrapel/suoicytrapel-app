package lepartycious.daos;

import java.util.List;

import lepartycious.models.Caterer;

public interface CatererDAO extends BaseDAO{
	
	public List<Caterer> getCaterers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder);

	public List<Caterer> loadCatererList(Long cityId, String searchString);

	public Long getCatererCount(Long cityid, String searchString);

	public Caterer fetchCatererDetails(Long cityId, String name);

}
