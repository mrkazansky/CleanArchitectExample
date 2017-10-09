package fpo.childhubmanagement.presentation.presenters;


import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;

/**
 * Created by dmilicic on 12/23/15.
 */
public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
