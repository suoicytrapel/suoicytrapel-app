package lepartycious.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;

@Transactional(readOnly=true)
public interface BandService {
	
	public SearchResponseDTOWrapper getBands(SearchRequestDTO searchDTO);

	public List<String> loadBandList(SearchRequestDTO searchRequestDTO);
	
	public DetailResponseDTO fetchBandDetails(DataRequestDTO dataRequestDTO);

}
