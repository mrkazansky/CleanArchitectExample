package fpo.childhubmanagement.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpo.childhubmanagement.R;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

/**
 * Created by mrkaz on 7/26/2017.
 */

public class AccountManagementAdapter extends RecyclerView.Adapter<AccountManagementAdapter.ViewHolder> implements Filterable {
    private List<DecodeTokenDataModel> dataList;
    private List<DecodeTokenDataModel> filterList;
    private Context context;
    private AccountFilter accountFiller;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public AccountManagementAdapter(Context context,List<DecodeTokenDataModel> list){
        this.context=context;
        dataList=list;
        filterList=list;
        viewBinderHelper.setOpenOnlyOne(true);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_account_management,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String regency="";
        DecodeTokenDataModel item = dataList.get(position);
        switch (item.getPermission())
        {
            case 0 : regency = context.getString(R.string.code_parent);
                break;
            case 1 : regency = context.getString(R.string.code_teacher);
                break;
            case 2 : regency = context.getString(R.string.code_driver);
                break;
            case 3 : regency = context.getString(R.string.code_admin);
                break;
            default: regency = context.getString(R.string.code_another_user);
                break;
        }
        holder.regency.setText(regency);
        holder.name.setText(item.getFullname());
        holder.adress.setText(item.getAddress());
        viewBinderHelper.bind(holder.swipeRevealLayout, item.getId());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public Filter getFilter() {
        if(accountFiller == null)
            accountFiller = new AccountFilter(this, filterList);
        return accountFiller;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.regency)
        TextView regency;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.adress)
        TextView adress;
        @Bind(R.id.block)
        TextView block;
        @Bind(R.id.call)
        TextView call;
        @Bind(R.id.detail)
        TextView detail;
        @Bind(R.id.swipe_reveal_layout)
        SwipeRevealLayout swipeRevealLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            block.setOnClickListener(this);
            call.setOnClickListener(this);
            detail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.block:
                    break;
                case R.id.call:
                    break;
                case R.id.detail:
                    break;
            }

        }
    }
    private class AccountFilter extends Filter{
        private  AccountManagementAdapter adapter;

        private  List<DecodeTokenDataModel> originalList;

        private  List<DecodeTokenDataModel> filteredList;

        private AccountFilter(AccountManagementAdapter adapter, List<DecodeTokenDataModel> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new LinkedList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                for ( DecodeTokenDataModel user : originalList) {
                    if (user.getFullname().contains(filterPattern) ||
                            user.getAddress().contains(filterPattern) ||
                            user.getBirthday().contains(filterPattern) ||
                            user.getEmail().contains(filterPattern) ||
                            user.getGender().contains(filterPattern) ||
                            user.getPhone().contains(filterPattern) ||
                            user.getUsername().contains(filterPattern))
                    {
                        filteredList.add(user);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filterList.clear();
            adapter.filterList.addAll((ArrayList<DecodeTokenDataModel>) results.values);
            adapter.notifyDataSetChanged();
        }
    }

}
