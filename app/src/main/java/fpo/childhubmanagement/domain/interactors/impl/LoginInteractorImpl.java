package fpo.childhubmanagement.domain.interactors.impl;

import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;
import fpo.childhubmanagement.domain.interactors.LoginInteractor;
import fpo.childhubmanagement.domain.interactors.base.AbstractInteractor;
import fpo.childhubmanagement.domain.repository.AccountRepository;

/**
 * Created by mrkaz on 7/18/2017.
 */

public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {
    private LoginInteractor.Callback mCallback;
    private AccountRepository mAccountRepository;
    private String mUsername;
    private String mPassword;

    public LoginInteractorImpl(Executor threadExcecutor, MainThread mainThread,
                               Callback callback, AccountRepository accountRepository,
                               String user, String password)
    {
        super(threadExcecutor,mainThread);
        mCallback=callback;
        mAccountRepository = accountRepository;
        mUsername = user;
        mPassword = password;
    }

    @Override
    public void run() {
        //Action
        final String success = mAccountRepository.getTokenFromLogin(mUsername,mPassword);
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                if (success!=null)
                    mCallback.onLoggedSuccess(success);
                else
                    mCallback.onLoggedFail();
            }
        });
    }
}
