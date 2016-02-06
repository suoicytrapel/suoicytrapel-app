package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lepartycious.daos.RentalDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Address;
import lepartycious.models.Rental;
import lepartycious.services.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalDAO rentalDAO;

	@Override
	public SearchResponseDTOWrapper getRentals(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalRentalCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalRentalCount = rentalDAO.getRentalCount(searchDTO.getCityId(), searchDTO.getSearchString());
			if(totalRentalCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populateRentalResults(searchResponseDTOList, searchDTO);
		searchResponseDTOWrapper.setResultCount(totalRentalCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadRentalList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
			List<Rental> Rentals = rentalDAO.loadRentalList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
			for(Rental Rental : Rentals){
				list.add(Rental.getName());
			}
		return list;
	}
	
	private void populateRentalResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO) {
		List<Rental> Rentals = rentalDAO.getRentals(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder());
		for(Rental Rental : Rentals){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(Rental.getName());
			searchResponseDTO.setMainImagerURL(Rental.getAttachments().get(0).getImageURL());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchRentalDetails(DataRequestDTO dataRequestDTO) {
		Rental Rental = rentalDAO.fetchRentalDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<String> serviceList = new ArrayList<String>();
		Address address = Rental.getAddresses().get(0);
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(Rental.getName());
		detailResponseDTO.setAddressLine1(address.getAddressLine1());
		detailResponseDTO.setAddressLine2(address.getAddressLine2());
		detailResponseDTO.setCity(address.getCity());
		detailResponseDTO.setState(address.getState());
		detailResponseDTO.setServices(serviceList);
		return detailResponseDTO;
	}

}
