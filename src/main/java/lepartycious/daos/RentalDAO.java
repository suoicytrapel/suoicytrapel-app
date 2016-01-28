package lepartycious.daos;

import java.util.List;

import lepartycious.models.Rental;

public interface RentalDAO extends BaseDAO{
	
	public List<Rental> getRentals(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder);

	public List<Rental> loadRentalList(Long cityId, String searchString);

	public Long getRentalCount(Long cityid, String searchString);

	public Rental fetchRentalDetails(Long cityId, String name);

}
