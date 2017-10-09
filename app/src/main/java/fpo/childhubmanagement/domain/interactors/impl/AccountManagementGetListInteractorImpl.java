package fpo.childhubmanagement.domain.interactors.impl;

import java.util.List;

import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;

import fpo.childhubmanagement.domain.interactors.AccountManagementGetListInteractor;
import fpo.childhubmanagement.domain.interactors.GetTokenInteractor;
import fpo.childhubmanagement.domain.interactors.base.AbstractInteractor;
import fpo.childhubmanagement.domain.repository.AccountManagementRepository;
import fpo.childhubmanagement.domain.repository.AccountRepository;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

/**
 * Created by mrkaz on 7/26/2017.
 */

public class AccountManagementGetListInteractorImpl extends AbstractInteractor implements AccountManagementGetListInteractor {
    private final AccountManagementGetListInteractor.Callback callback;
    private final AccountManagementRepository accountManagementRepository;
    private final String token;

    public AccountManagementGetListInteractorImpl(Executor threadExecutor, MainThread mainThread, AccountManagementGetListInteractor.Callback callback,
                                                  AccountManagementRepository accountManagementRepository, String token) {
        super(threadExecutor, mainThread);
        this.callback = callback;
        this.accountManagementRepository = accountManagementRepository;
        this.token = token;
    }

    @Override
    public void run() {
        final List<DecodeTokenDataModel> result = accountManagementRepository.getList(token);
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onFinish(result);
            }
        });

    }
}
