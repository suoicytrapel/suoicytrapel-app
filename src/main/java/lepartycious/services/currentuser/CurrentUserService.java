package lepartycious.services.currentuser;

import lepartycious.models.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
