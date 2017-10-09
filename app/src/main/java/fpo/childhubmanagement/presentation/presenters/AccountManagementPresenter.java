package fpo.childhubmanagement.presentation.presenters;

import java.util.List;

import fpo.childhubmanagement.presentation.ui.BaseView;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

/**
 * Created by mrkaz on 7/26/2017.
 */

public interface AccountManagementPresenter extends BasePresenter {
    interface View extends BaseView{
        void showAccountList(List<DecodeTokenDataModel> result);
    }
    void requestNewAccountList(String token);
}
