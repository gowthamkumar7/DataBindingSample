package gtm.com.databindingsample.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
