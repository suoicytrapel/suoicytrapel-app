package lepartycious.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.services.PhotographerService;


@Service
public class PhotographerServiceImpl implements PhotographerService {

	@Override
	public SearchResponseDTOWrapper getPhotographers(SearchRequestDTO searchDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> loadPhotographerList(SearchRequestDTO searchRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
