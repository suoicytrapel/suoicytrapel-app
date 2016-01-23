package lepartycious.controllers;

import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.services.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rest/v1/search")
public class HomeSearchController {
	
	public static final Long DEFAULT_OFFSET = 0l;
	public static final Long DEFAULT_LIMIT = 10l;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(method=RequestMethod.POST)
	public SearchResponseDTOWrapper fetchResults(@RequestBody SearchRequestDTO searchRequestDTO) {
		if(searchRequestDTO.getOffset() == null){
			searchRequestDTO.setOffset(DEFAULT_OFFSET);
		}
		if(searchRequestDTO.getLimit() == null){
			searchRequestDTO.setLimit(DEFAULT_LIMIT);
		}
		SearchResponseDTOWrapper searchResponseDTOWrapper = searchService.getEntities(searchRequestDTO);
		return searchResponseDTOWrapper;
	}
	
}
