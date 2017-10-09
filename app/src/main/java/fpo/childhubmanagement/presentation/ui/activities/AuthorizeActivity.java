package fpo.childhubmanagement.presentation.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpo.childhubmanagement.R;
import fpo.childhubmanagement.domain.executor.impl.ThreadExecutor;
import fpo.childhubmanagement.presentation.presenters.AuthorizePresenter;
import fpo.childhubmanagement.presentation.presenters.impl.AuthorizePresenterImpl;
import fpo.childhubmanagement.storage.AccountRepositoryImpl;
import fpo.childhubmanagement.threading.MainThreadImpl;
import fpo.childhubmanagement.utils.StringUtils;

public class AuthorizeActivity extends BaseActivity implements AuthorizePresenter.View{

    @Bind(R.id.txt_login_email)
    EditText txtUsername;

    @Bind(R.id.txt_login_password)
    EditText txtPassword;

    @Bind(R.id.btn_login)
    AppCompatButton btnLogin;

    private String mUsername;
    private String mPassword;

    AuthorizePresenter mAuthorizePresenter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuthorizePresenter.checkLogginState();
    }

    private void init() {
        progressDialog = new ProgressDialog(this);
        mAuthorizePresenter = new AuthorizePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new AccountRepositoryImpl(this));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClicked();
            }
        });
    }


    @Override
    public void onLoginClicked() {
        try
        {
            mUsername = txtUsername.getText().toString().trim();
            mPassword = txtPassword.getText().toString().trim();
        }
        catch (Exception ex)
        {
            mUsername = "";
            mPassword = "";
        }
        mAuthorizePresenter.login(mUsername, StringUtils.convertPassMd5(mPassword));
    }

    @Override
    public void goToMainScreen() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        this.progressDialog.show(this,"Processing","",true);
    }

    @Override
    public void hideProgress() {
        this.progressDialog.cancel();
    }

    @Override
    public void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.show();
    }
}
