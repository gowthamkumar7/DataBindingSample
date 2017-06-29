package gtm.com.databindingsample.activity;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import gtm.com.databindingsample.R;
import gtm.com.databindingsample.activity.adapters.UsersListAdapter;
import gtm.com.databindingsample.activity.api.ApiService;
import gtm.com.databindingsample.activity.models.UserDetailsModel;
import gtm.com.databindingsample.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private UsersListAdapter mAdapter;
    private static String WORK_COMPLETED = "Work Completed!";
    private static String WORK_PENDING = "Work Pending!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        binding.toolbar.setTitle("Gowthm kumar");

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiService apiService = retrofit.create(ApiService.class);
                apiService.getUsers().enqueue(new Callback<List<UserDetailsModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserDetailsModel>> call, @NonNull Response<List<UserDetailsModel>> response) {
                        mAdapter = new UsersListAdapter(response.body());
                        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                        binding.included.recyclerUsersData.setLayoutManager(manager);
                        binding.included.recyclerUsersData.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<UserDetailsModel>> call, Throwable t) {

                    }
                });

            }
        });


    }

    /**
     * Custom Binding using Binding Adapter.
     */
    @BindingAdapter("isWorkCompleted")
    public static void setWorkCompleted(TextView textView, boolean isWorkCompleted) {
        if (isWorkCompleted) {
            textView.setText(WORK_COMPLETED);
        } else {
            textView.setText(WORK_PENDING);
        }


    }

}
