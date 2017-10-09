package fpo.childhubmanagement.presentation.presenters.impl;

import fpo.childhubmanagement.domain.executor.Executor;
import fpo.childhubmanagement.domain.executor.MainThread;
import fpo.childhubmanagement.domain.interactors.DecodeTokenInteractor;
import fpo.childhubmanagement.domain.interactors.GetTokenInteractor;
import fpo.childhubmanagement.domain.interactors.LoginInteractor;
import fpo.childhubmanagement.domain.interactors.SetTokenInteractor;
import fpo.childhubmanagement.domain.interactors.impl.DecodeTokenInteractorImpl;
import fpo.childhubmanagement.domain.interactors.impl.GetTokenInteractorImpl;
import fpo.childhubmanagement.domain.interactors.impl.LoginInteractorImpl;
import fpo.childhubmanagement.domain.interactors.impl.SetTokenInteractorImpl;
import fpo.childhubmanagement.domain.repository.AccountRepository;
import fpo.childhubmanagement.presentation.presenters.AbstractPresenter;
import fpo.childhubmanagement.presentation.presenters.AuthorizePresenter;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

/**
 * Created by mrkaz on 7/18/2017.
 */

public class AuthorizePresenterImpl extends AbstractPresenter implements
        AuthorizePresenter,
        LoginInteractor.Callback,
        SetTokenInteractor.Callback,
        GetTokenInteractor.Callback,
        DecodeTokenInteractor.Callback

{
    private AuthorizePresenter.View mView;
    private AccountRepository mAccountRepository;


    public AuthorizePresenterImpl(Executor executor, MainThread mainThread,
                                  View view, AccountRepository accountRepository) {
        super(executor, mainThread);
        mView=view;
        mAccountRepository=accountRepository;
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
        mView.showError(message);
    }


    @Override
    public void decode(String token) {
        DecodeTokenInteractor decodeTokenInteractor = new DecodeTokenInteractorImpl(
                mExecutor,mMainThread,
                this,
                mAccountRepository,
                token
        );
        decodeTokenInteractor.execute();
    }

    @Override
    public void login(String username, String password) {
        LoginInteractor loginInteractor = new LoginInteractorImpl(
                mExecutor, mMainThread,
                this,
                mAccountRepository,
                username,
                password);
        loginInteractor.execute();
    }

    @Override
    public void checkLogginState() {
        GetTokenInteractor getTokenInteractor = new GetTokenInteractorImpl(mExecutor,
                mMainThread,
                this,
                mAccountRepository);
        getTokenInteractor.execute();
    }

    @Override
    public void setLogginState(String token) {
        SetTokenInteractor setTokenInteractor = new SetTokenInteractorImpl(mExecutor,
                mMainThread,
                this,
                mAccountRepository,
                token);
        setTokenInteractor.execute();
    }

    @Override
    public void onFinishSaveToken(String token) {
        decode(token);
    }


    @Override
    public void onGetTokenSuccess(String token) {
        mView.hideProgress();
        mView.goToMainScreen();
    }

    @Override
    public void onGetTokenFail() {
    }

    @Override
    public void onLoggedSuccess(String token) {
        setLogginState(token);
        mView.showProgress();
    }

    @Override
    public void onLoggedFail() {
       onError("Login failed");
    }



    @Override
    public void onDecodeSuccess(DecodeTokenDataModel result) {
        mView.hideProgress();
        mView.goToMainScreen();
    }

    @Override
    public void onDecodeFail() {
        mView.hideProgress();
        onError("Decode token failed");
    }
}
