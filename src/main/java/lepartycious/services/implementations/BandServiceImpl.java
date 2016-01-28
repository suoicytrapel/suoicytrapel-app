package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import lepartycious.Enums.SearchTypeEnum;
import lepartycious.daos.BandDAO;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.models.Address;
import lepartycious.models.Band;
import lepartycious.services.BandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BandServiceImpl implements BandService {
	
	@Autowired
	private BandDAO bandDAO;

	@Override
	public SearchResponseDTOWrapper getBands(SearchRequestDTO searchDTO) {
		SearchResponseDTOWrapper searchResponseDTOWrapper = new SearchResponseDTOWrapper();
		List<SearchResponseDTO> searchResponseDTOList = new ArrayList<SearchResponseDTO>();
		Long totalBandCount = null;
		if(searchDTO.getOffset() == null || searchDTO.getOffset() == 0){
			totalBandCount = bandDAO.getBandCount(searchDTO.getCityId(), searchDTO.getSearchString());
			if(totalBandCount < 1){
				throw new IllegalArgumentException("No matching results");
			}
		}
		populateBandResults(searchResponseDTOList, searchDTO);
		searchResponseDTOWrapper.setResultCount(totalBandCount);
		searchResponseDTOWrapper.setSearchResponseDTOList(searchResponseDTOList);
		return searchResponseDTOWrapper;	
	}

	@Override
	public List<String> loadBandList(SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
			List<Band> Bands = bandDAO.loadBandList(searchRequestDTO.getCityId(), searchRequestDTO.getSearchString());
			for(Band Band : Bands){
				list.add(Band.getName());
			}
		return list;
	}
	
	private void populateBandResults(
			List<SearchResponseDTO> searchResponseDTOList, SearchRequestDTO searchDTO) {
		List<Band> Bands = bandDAO.getBands(searchDTO.getCityId(), searchDTO.getSearchString(), searchDTO.getOffset(), searchDTO.getLimit(), searchDTO.getSortField(), searchDTO.getSortOrder());
		for(Band Band : Bands){
			SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
			searchResponseDTO.setName(Band.getName());
			searchResponseDTO.setMainImagerURL(Band.getAttachments().get(0).getImageURL());
			searchResponseDTOList.add(searchResponseDTO);
		}
	}

	@Override
	public DetailResponseDTO fetchBandDetails(DataRequestDTO dataRequestDTO) {
		Band Band = bandDAO.fetchBandDetails(dataRequestDTO.getCityId(), dataRequestDTO.getName());
		List<String> serviceList = new ArrayList<>();
		List<String> amenitiesList = new ArrayList<>();
		Address address = Band.getAddresses().get(0);
		DetailResponseDTO detailResponseDTO = new DetailResponseDTO();
		detailResponseDTO.setName(Band.getName());
		detailResponseDTO.setAddressLine1(address.getAddressLine1());
		detailResponseDTO.setAddressLine2(address.getAddressLine2());
		detailResponseDTO.setCity(address.getCity());
		detailResponseDTO.setState(address.getState());
		detailResponseDTO.setServices(serviceList);
		detailResponseDTO.setAmenities(amenitiesList);
		return detailResponseDTO;
	}

}
