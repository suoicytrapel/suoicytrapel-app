package lepartycious.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.services.CatererService;

@Service
public class CatererServiceImpl implements CatererService {

	@Override
	public SearchResponseDTOWrapper getCaterers(SearchRequestDTO searchDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> loadCatererList(SearchRequestDTO searchRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
