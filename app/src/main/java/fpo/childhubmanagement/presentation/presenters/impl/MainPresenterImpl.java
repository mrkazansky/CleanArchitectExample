package fpo.childhubmanagement.presentation.presenters.impl;

import fpo.childhubmanagement.R;
import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;
import fpo.childhubmanagement.domain.executor.impl.ThreadExecutor;
import fpo.childhubmanagement.domain.interactors.GetTokenInteractor;
import fpo.childhubmanagement.domain.interactors.impl.GetTokenInteractorImpl;
import fpo.childhubmanagement.domain.repository.AccountRepository;
import fpo.childhubmanagement.presentation.presenters.AbstractPresenter;
import fpo.childhubmanagement.presentation.presenters.MainPresenter;
import fpo.childhubmanagement.threading.MainThreadImpl;

/**
 * Created by mrkaz on 7/21/2017.
 */

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter, GetTokenInteractor.Callback {
    private final MainPresenter.View view;
    private final AccountRepository accountRepository;

    public MainPresenterImpl(Executor executor, MainThread mainThread, MainPresenter.View view, AccountRepository accountRepository) {
        super(executor, mainThread);
        this.view = view;
        this.accountRepository = accountRepository;
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void gotoFeature(int id) {
        switch (id) {
            case R.id.nav_account :
                view.gotoAccountManagement();
                break;
            default:
                break;
        }
    }

    @Override
    public void getToken() {
        GetTokenInteractor getTokenInteractor = new GetTokenInteractorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this, accountRepository);
        getTokenInteractor.execute();
    }

    @Override
    public void onGetTokenSuccess(String token) {
        view.onReceiveToken(token);
    }

    @Override
    public void onGetTokenFail() {

    }
}
