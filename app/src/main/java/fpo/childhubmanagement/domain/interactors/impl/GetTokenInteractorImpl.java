package fpo.childhubmanagement.domain.interactors.impl;

import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;
import fpo.childhubmanagement.domain.interactors.base.AbstractInteractor;
import fpo.childhubmanagement.domain.repository.AccountRepository;

/**
 * Created by mrkaz on 7/19/2017.
 */

public class GetTokenInteractorImpl extends AbstractInteractor implements fpo.childhubmanagement.domain.interactors.GetTokenInteractor {
    private final Executor threadExecutor;
    private final MainThread mainThread;
    private final Callback callback;
    private final AccountRepository accountRepository;

    public GetTokenInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                  Callback callback, AccountRepository accountRepository) {
        super(threadExecutor, mainThread);
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
        this.callback = callback;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run() {
        final String success = accountRepository.checkToken();

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                if (!success.equals("null"))
                    callback.onGetTokenSuccess(success);
                else
                    callback.onGetTokenFail();
            }
        });
    }
}
