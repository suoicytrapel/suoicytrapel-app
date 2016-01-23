package lepartycious.services;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
public interface SearchService{

	public SearchResponseDTOWrapper getEntities(SearchRequestDTO searchDTO);

}