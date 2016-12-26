package lepartycious.wizard.caterer;

import lepartycious.dtos.requestDTOs.CatererDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface CatererWizardService {

	@Transactional(readOnly=false)
	void saveCatererDetails(CatererDTO catererDTO);

	LookUpDTO getCatererlookUp();

	CatererDTO loadCatererWizard(String wizardName)
			throws Exception;

	void updateCatererActivationStatus(String action, Long catererId,
			String actionComments) throws Exception;

}
