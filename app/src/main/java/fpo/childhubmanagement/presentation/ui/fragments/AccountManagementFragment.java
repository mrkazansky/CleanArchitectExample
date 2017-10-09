package fpo.childhubmanagement.presentation.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpo.childhubmanagement.R;
import fpo.childhubmanagement.contanst.CacheKey;
import fpo.childhubmanagement.domain.executor.impl.ThreadExecutor;
import fpo.childhubmanagement.presentation.model.SearchEvent;
import fpo.childhubmanagement.presentation.presenters.AccountManagementPresenter;
import fpo.childhubmanagement.presentation.presenters.impl.AccountManagementPresenterImpl;
import fpo.childhubmanagement.presentation.ui.adapters.AccountManagementAdapter;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;
import fpo.childhubmanagement.storage.AccountManagementRepositoryImpl;
import fpo.childhubmanagement.threading.MainThreadImpl;

/**
 * Created by mrkaz on 7/25/2017.
 */

public class AccountManagementFragment extends Fragment implements AccountManagementPresenter.View {
    private static final String TAG = "AccountManagement";
    @Bind(R.id.list)
    RecyclerView accountList;

    private AccountManagementAdapter accountManagementAdapter;

    private String token;
    private AccountManagementPresenter accountManagementPresenter;

    private static AccountManagementFragment accountManagementFragment;

    public static AccountManagementFragment getInstance(String token) {
        if (accountManagementFragment == null)
        {
            Bundle bundle = new Bundle();
            bundle.putString(CacheKey.TOKEN,token);
            accountManagementFragment = new AccountManagementFragment();
            accountManagementFragment.setArguments(bundle);
        }
        return accountManagementFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = getArguments().getString(CacheKey.TOKEN);
        accountManagementPresenter = new AccountManagementPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this,new AccountManagementRepositoryImpl(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_management, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        accountList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        accountList.setItemAnimator(new DefaultItemAnimator());
        accountManagementPresenter.requestNewAccountList(token);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SearchEvent event) {
        Log.d(TAG, "onMessageEvent: "+event.message);
        ((AccountManagementAdapter)accountList.getAdapter()).getFilter().filter(event.message);
    }

    @Override
    public void showAccountList(List<DecodeTokenDataModel> result) {
        Log.d(TAG, "showAccountList: " + result.size());
        accountManagementAdapter = new AccountManagementAdapter(getActivity(),result);
        accountList.setAdapter(accountManagementAdapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
