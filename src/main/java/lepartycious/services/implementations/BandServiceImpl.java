package lepartycious.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.services.BandService;

@Service
public class BandServiceImpl implements BandService {

	@Override
	public SearchResponseDTOWrapper getBands(SearchRequestDTO searchDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> loadBandList(SearchRequestDTO searchRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
