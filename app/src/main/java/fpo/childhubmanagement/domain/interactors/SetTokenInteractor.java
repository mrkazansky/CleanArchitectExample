package fpo.childhubmanagement.domain.interactors;

import fpo.childhubmanagement.domain.interactors.base.Interactor;

/**
 * Created by mrkaz on 7/19/2017.
 */

public interface SetTokenInteractor extends Interactor {
    interface Callback{
        void onFinishSaveToken(String token);
    }
}
