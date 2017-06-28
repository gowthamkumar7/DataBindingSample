package gtm.com.databindingsample.activity.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import gtm.com.databindingsample.R;
import gtm.com.databindingsample.activity.models.UserDetailsModel;
import gtm.com.databindingsample.databinding.ItemUsersDataViewBinding;

/**
 * Created by Administrator on 6/28/2017.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersDataViewHolder> {

    private List<UserDetailsModel> mResponse;
    private ItemUsersDataViewBinding binding;

    public UsersListAdapter(List<UserDetailsModel> response) {
        this.mResponse = response;
    }

    @Override
    public UsersDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_users_data_view, parent, false);
        return new UsersDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UsersDataViewHolder holder, int position) {
        UserDetailsModel model = mResponse.get(position);
        holder.binding.setUserDataModel(model);
    }

    @Override
    public int getItemCount() {
        return mResponse.size();
    }

    class UsersDataViewHolder extends RecyclerView.ViewHolder {

        ItemUsersDataViewBinding binding;

        UsersDataViewHolder(ItemUsersDataViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
