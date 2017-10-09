package fpo.childhubmanagement.domain.interactors;

import fpo.childhubmanagement.domain.interactors.base.Interactor;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

/**
 * Created by mrkaz on 7/19/2017.
 */

public interface DecodeTokenInteractor extends Interactor {
    interface Callback{
        void onDecodeSuccess(DecodeTokenDataModel result);
        void onDecodeFail();
    }
}
