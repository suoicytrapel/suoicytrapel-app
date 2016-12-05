package lepartycious.daos;

import java.util.List;

import lepartycious.dtos.requestDTOs.FilterWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.models.Venue;

public interface VenueDAO extends BaseDAO{
	
	public List<Venue> getVenues(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder,FilterWrapperDTO filters);

	public List<Venue> loadVenueList(Long cityId, String searchString);

	public Long getVenueCount(Long cityid, String searchString,FilterWrapperDTO filters);

	public Venue fetchVenueDetails(Long cityId, String name);
	
	public List<Venue> fetchRecomendations(Long cityId);

	public Venue loadVenueByUserId(long userId);

}
