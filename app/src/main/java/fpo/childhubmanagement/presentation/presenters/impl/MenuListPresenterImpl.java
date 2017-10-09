package fpo.childhubmanagement.presentation.presenters.impl;

import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;
import fpo.childhubmanagement.presentation.presenters.AbstractPresenter;
import fpo.childhubmanagement.presentation.presenters.MenuListPresenter;

/**
 * Created by mrkaz on 7/21/2017.
 */

public class MenuListPresenterImpl extends AbstractPresenter implements MenuListPresenter {
    private final View mView;

    public MenuListPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        mView = view;
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
        switch (id){
            default:
                break;
        }
    }
}
