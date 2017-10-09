package fpo.childhubmanagement.presentation.presenters;

import fpo.childhubmanagement.presentation.ui.BaseView;

/**
 * Created by mrkaz on 7/21/2017.
 */

public interface MenuListPresenter extends BasePresenter {
    interface View extends BaseView{
        void gotoRequest();
        void gotoPost();
        void gotoAttend();
    }
    void gotoFeature(int id);

}
