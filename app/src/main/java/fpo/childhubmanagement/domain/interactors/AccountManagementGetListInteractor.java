package fpo.childhubmanagement.domain.interactors;

import java.util.List;

import fpo.childhubmanagement.domain.interactors.base.Interactor;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

/**
 * Created by mrkaz on 7/26/2017.
 */

public interface AccountManagementGetListInteractor extends Interactor {
    interface Callback{
        void onFinish(List<DecodeTokenDataModel> result);
    }
}
