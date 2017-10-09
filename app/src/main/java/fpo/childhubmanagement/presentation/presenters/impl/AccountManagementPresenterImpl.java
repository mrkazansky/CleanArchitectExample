package fpo.childhubmanagement.presentation.presenters.impl;

import java.util.List;

import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;
import fpo.childhubmanagement.domain.executor.impl.ThreadExecutor;
import fpo.childhubmanagement.domain.interactors.AccountManagementGetListInteractor;
import fpo.childhubmanagement.domain.interactors.impl.AccountManagementGetListInteractorImpl;
import fpo.childhubmanagement.domain.repository.AccountManagementRepository;
import fpo.childhubmanagement.presentation.presenters.AbstractPresenter;
import fpo.childhubmanagement.presentation.presenters.AccountManagementPresenter;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;
import fpo.childhubmanagement.threading.MainThreadImpl;

/**
 * Created by mrkaz on 7/26/2017.
 */

public class AccountManagementPresenterImpl extends AbstractPresenter implements
        AccountManagementPresenter,
        AccountManagementGetListInteractor.Callback{
    private  View view;
    private  AccountManagementRepository accountManagementRepository;

    public AccountManagementPresenterImpl(Executor executor, MainThread mainThread,
                                          View view, AccountManagementRepository accountManagementRepository) {
        super(executor, mainThread);

        this.view = view;
        this.accountManagementRepository = accountManagementRepository;
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
    public void requestNewAccountList(String token) {
        AccountManagementGetListInteractor accountManagementGetListInteractor
                = new AccountManagementGetListInteractorImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),this,accountManagementRepository,token);
        accountManagementGetListInteractor.execute();
    }

    @Override
    public void onFinish(List<DecodeTokenDataModel> result) {
        view.showAccountList(result);
    }
}
