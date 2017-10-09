package fpo.childhubmanagement.presentation.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpo.childhubmanagement.R;
import fpo.childhubmanagement.domain.executor.impl.ThreadExecutor;
import fpo.childhubmanagement.presentation.model.MessageEvent;
import fpo.childhubmanagement.presentation.model.SearchEvent;
import fpo.childhubmanagement.presentation.presenters.MainPresenter;
import fpo.childhubmanagement.presentation.presenters.impl.MainPresenterImpl;
import fpo.childhubmanagement.presentation.ui.fragments.AccountManagementFragment;
import fpo.childhubmanagement.storage.AccountRepositoryImpl;
import fpo.childhubmanagement.threading.MainThreadImpl;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MainPresenter.View, View.OnClickListener,
        com.lapism.searchview.SearchView.OnQueryTextListener,
        com.lapism.searchview.SearchView.OnOpenCloseListener{

    @Bind(R.id.fab)
    CounterFab fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.searchView)
    com.lapism.searchview.SearchView searchView;

    private MainPresenter mMainPresenter;
    private static String TOKEN="";
    /*Fragment*/
    AccountManagementFragment accountManagementFragment;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupDrawer();
        setupSearchBar();
        fab.setOnClickListener(this);
        mMainPresenter = new MainPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this, new AccountRepositoryImpl(this));
        mMainPresenter.getToken();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setupSearchBar() {
        searchView.setOnQueryTextListener(this);
        searchView.setOnOpenCloseListener(this);
    }

    private void setupDrawer() {
       searchView.setOnMenuClickListener(new com.lapism.searchview.SearchView.OnMenuClickListener() {
           @Override
           public void onMenuClick() {
               drawer.openDrawer(Gravity.START);
           }
       });
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {
            searchView.open(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        mMainPresenter.gotoFeature(id);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    public void gotoRequest() {

    }

    @Override
    public void gotoAttend() {

    }

    @Override
    public void gotoPost() {

    }

    @Override
    public void gotoAccountManagement() {
        Log.d(TAG, "gotoAccountManagement: ");
        getFragmentManager().
                beginTransaction().
                replace(R.id.fragment_place,AccountManagementFragment.getInstance(TOKEN)).
                addToBackStack("AccountManagement").
                commit();
    }

    @Override
    public void onReceiveToken(String token) {
        Log.d(TAG, "onReceiveToken: " +token);
        TOKEN = token;
        mMainPresenter.gotoFeature(R.id.nav_account);
    }

    @Override
    public void onClick(View v) {
        fab.increase();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        EventBus.getDefault().post(new SearchEvent(newText));
        return false;
    }

    @Override
    public boolean onClose() {
        //searchView.setShadow(false);
        return false;
    }

    @Override
    public boolean onOpen() {
        //searchView.setShadow(true);
        return false;
    }
}
