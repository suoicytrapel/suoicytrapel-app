package lepartycious.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;

@Transactional(readOnly=true)
public interface CatererService {
	
	public SearchResponseDTOWrapper getCaterers(SearchRequestDTO searchDTO);

	public List<String> loadCatererList(SearchRequestDTO searchRequestDTO);

}
