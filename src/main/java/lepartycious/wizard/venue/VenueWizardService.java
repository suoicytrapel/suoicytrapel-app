package lepartycious.wizard.venue;

import lepartycious.dtos.requestDTOs.VenueDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface VenueWizardService {

	@Transactional(readOnly=false)
	void saveVenueDetails(VenueDTO venueDTO);

	LookUpDTO getVenuelookUp();

	VenueDTO loadVenueWizard(String wizardName)
			throws Exception;

	void updateVenueActivationStatus(String action, Long venueId,
			String actionComments) throws Exception;

}
