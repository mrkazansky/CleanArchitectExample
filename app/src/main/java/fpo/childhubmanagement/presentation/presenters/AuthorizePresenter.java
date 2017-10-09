package fpo.childhubmanagement.presentation.presenters;

import fpo.childhubmanagement.presentation.ui.BaseView;

/**
 * Created by mrkaz on 7/18/2017.
 */

public interface AuthorizePresenter extends BasePresenter {
    interface View extends BaseView
    {
        void onLoginClicked();
        void goToMainScreen();
    }
    void decode(String token);
    void login(String username, String password);
    void checkLogginState();
    void setLogginState(String token);
}
