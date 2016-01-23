package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import lepartycious.Enums.SearchTypeEnum;
import lepartycious.daos.SearchDAO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Attachment;
import lepartycious.models.Caterer;
import lepartycious.models.Venue;
import lepartycious.services.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchDAO searchDAO;

	@Override
	public SearchResponseDTOWrapper getEntities(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalVenueCount = null;
		if(SearchTypeEnum.VENUE.toString().equals(searchDTO.getSearchType())){
			if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
				totalVenueCount = searchDAO.getVenueCount(searchDTO.getCityId(), searchDTO.getSearchString());
				if(totalVenueCount < 1){
					throw new IllegalArgumentException("No matching results");
				}
			}
			populateVenueResults(searchResponseDTOList, searchDTO);
			searchResponseDTOWrapper.setResultCount(totalVenueCount);
			searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
			return searchResponseDTOWrapper;
		}
		// TODO Auto-generated method stub
		return null;
	}

	private void populateVenueResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO) {
		List<Venue> venues = searchDAO.getVenues(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder());
		for(Venue venue : venues){
			List<Attachment> attachments = venue.getAttachments();
			List<String> attachmentURLs = new ArrayList<String>();
			for(Attachment attachment : attachments){
				attachmentURLs.add(attachment.getImageURL());
			}
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO(venue.getName(), attachmentURLs);
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

}
