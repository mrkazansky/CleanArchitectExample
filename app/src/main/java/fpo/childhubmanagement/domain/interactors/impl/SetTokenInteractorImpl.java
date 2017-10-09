package fpo.childhubmanagement.domain.interactors.impl;

import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;
import fpo.childhubmanagement.domain.interactors.SetTokenInteractor;
import fpo.childhubmanagement.domain.interactors.base.AbstractInteractor;
import fpo.childhubmanagement.domain.repository.AccountRepository;

/**
 * Created by mrkaz on 7/19/2017.
 */

public class SetTokenInteractorImpl extends AbstractInteractor implements SetTokenInteractor {
    SetTokenInteractor.Callback mCallback;
    AccountRepository mAccountRepository;
    String mToken;
    public SetTokenInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                  Callback callback, AccountRepository accountRepository,
                                  String token) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mAccountRepository = accountRepository;
        mToken = token;
    }

    @Override
    public void run() {
        mAccountRepository.saveToken(mToken);
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFinishSaveToken(mToken);
            }
        });
    }
}
