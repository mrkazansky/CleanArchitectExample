
package fpo.childhubmanagement.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpo.childhubmanagement.R;
import fpo.childhubmanagement.contanst.ArgumentKey;
import fpo.childhubmanagement.domain.executor.impl.ThreadExecutor;
import fpo.childhubmanagement.presentation.presenters.MenuListPresenter;
import fpo.childhubmanagement.presentation.presenters.impl.MenuListPresenterImpl;
import fpo.childhubmanagement.threading.MainThreadImpl;

/**
 * Created by mxn on 2016/12/13.
 * MenuListFragment
 */

public class MenuListFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, MenuListPresenter.View{

    @Bind(R.id.vNavigation)
    NavigationView mNavigationView;

    ImageView headerAvatar;
    TextView headerFullname;
    MenuListPresenter menuListPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuListPresenter = new MenuListPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        ButterKnife.bind(this,view);
        mNavigationView.setNavigationItemSelectedListener(this);
        headerAvatar = (ImageView)mNavigationView.getHeaderView(0).findViewById(R.id.avatar);
        headerFullname = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.name);
        setupHeader();
        return view ;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        menuListPresenter.gotoFeature(item.getItemId());
        return false;
    }


    public void setupHeader() {
        String username = getArguments().getString(ArgumentKey.NAME);
        String avatar = getArguments().getString(ArgumentKey.AVATAR);
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
        Picasso.with(getActivity())
                .load(avatar)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .into(headerAvatar);
        headerFullname.setText(username);
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
    public void gotoPost() {

    }

    @Override
    public void gotoAttend() {

    }
}
