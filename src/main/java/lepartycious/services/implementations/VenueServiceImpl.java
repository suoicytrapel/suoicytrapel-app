package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import lepartycious.Enums.SearchTypeEnum;
import lepartycious.daos.VenueDAO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Attachment;
import lepartycious.models.Venue;
import lepartycious.services.VenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService {
	
	@Autowired
	private VenueDAO venueDAO;

	@Override
	public SearchResponseDTOWrapper getVenues(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalVenueCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalVenueCount = venueDAO.getVenueCount(searchDTO.getCityId(), searchDTO.getSearchString());
			if(totalVenueCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populateVenueResults(searchResponseDTOList, searchDTO);
		searchResponseDTOWrapper.setResultCount(totalVenueCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadVenueList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		if(SearchTypeEnum.VENUE.toString().equals(searchRequestDTO.getSearchType())){
			List<Venue> venues = venueDAO.loadVenueList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
			for(Venue venue : venues){
				list.add(venue.getName());
			}
		}
		return list;
	}
	
	private void populateVenueResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO) {
		List<Venue> venues = venueDAO.getVenues(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder());
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
