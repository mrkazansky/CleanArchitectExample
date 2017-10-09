package fpo.childhubmanagement.domain.interactors;

import fpo.childhubmanagement.domain.interactors.base.Interactor;

/**
 * Created by mrkaz on 7/18/2017.
 */

public interface LoginInteractor extends Interactor {
    interface Callback{
        void onLoggedSuccess(String token);
        void onLoggedFail();
    }
}
