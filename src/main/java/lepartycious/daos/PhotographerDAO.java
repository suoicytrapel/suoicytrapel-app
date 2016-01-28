package lepartycious.daos;

import java.util.List;

import lepartycious.models.Photographer;

public interface PhotographerDAO extends BaseDAO{
	
	public List<Photographer> getPhotographers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder);

	public List<Photographer> loadPhotographerList(Long cityId, String searchString);

	public Long getPhotographerCount(Long cityid, String searchString);

	public Photographer fetchPhotographerDetails(Long cityId, String name);

}
