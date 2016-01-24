package lepartycious.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.services.DecoratorService;

@Service
public class DecoratorServiceImpl implements DecoratorService {

	@Override
	public SearchResponseDTOWrapper getDecorators(SearchRequestDTO searchDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> loadDecoratorList(SearchRequestDTO searchRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
