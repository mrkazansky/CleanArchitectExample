package fpo.childhubmanagement.domain.interactors.impl;

import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;
import fpo.childhubmanagement.domain.interactors.DecodeTokenInteractor;
import fpo.childhubmanagement.domain.interactors.base.AbstractInteractor;
import fpo.childhubmanagement.domain.repository.AccountRepository;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

/**
 * Created by mrkaz on 7/19/2017.
 */

public class DecodeTokenInteractorImpl extends AbstractInteractor implements DecodeTokenInteractor {
    private final Executor threadExecutor;
    private final MainThread mainThread;
    private final Callback callback;
    private final AccountRepository accountRepository;
    private final String token;

    public DecodeTokenInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                     Callback callback, AccountRepository accountRepository,
                                     String token) {
        super(threadExecutor, mainThread);
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
        this.callback = callback;
        this.accountRepository = accountRepository;
        this.token = token;
    }

    @Override
    public void run() {
        final DecodeTokenDataModel result = accountRepository.decode(token);
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                if (result==null)
                    callback.onDecodeFail();
                else
                    callback.onDecodeSuccess(result);
            }
        });
    }
}
