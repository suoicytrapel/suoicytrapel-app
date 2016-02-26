package lepartycious.daos;

import java.util.List;

import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.models.Caterer;
import lepartycious.models.Photographer;

public interface PhotographerDAO extends BaseDAO{
	
	public List<Photographer> getPhotographers(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder, FilterWrapperDTO filters);

	public List<Photographer> loadPhotographerList(Long cityId, String searchString);

	public Long getPhotographerCount(Long cityid, String searchString, FilterWrapperDTO filters);

	public Photographer fetchPhotographerDetails(Long cityId, String name);
	
	public List<Photographer> fetchRecomendations(Long cityId);

}
