package fpo.childhubmanagement.presentation.presenters;

import fpo.childhubmanagement.presentation.ui.BaseView;

/**
 * Created by mrkaz on 7/21/2017.
 */

public interface MainPresenter extends BasePresenter {
    interface View extends BaseView{
        void gotoRequest();
        void gotoAttend();
        void gotoPost();
        void gotoAccountManagement();
        void onReceiveToken(String token);
    }
    void gotoFeature(int id);
    void getToken();
}
