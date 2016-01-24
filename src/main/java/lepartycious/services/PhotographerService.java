package lepartycious.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;

@Transactional(readOnly=true)
public interface PhotographerService {
	public SearchResponseDTOWrapper getPhotographers(SearchRequestDTO searchDTO);

	public List<String> loadPhotographerList(SearchRequestDTO searchRequestDTO);

}
