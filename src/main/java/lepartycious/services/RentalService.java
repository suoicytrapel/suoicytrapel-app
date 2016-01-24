package lepartycious.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;

@Transactional(readOnly=true)
public interface RentalService {
	
	public SearchResponseDTOWrapper getRentals(SearchRequestDTO searchDTO);

	public List<String> loadRentalList(SearchRequestDTO searchRequestDTO);


}
