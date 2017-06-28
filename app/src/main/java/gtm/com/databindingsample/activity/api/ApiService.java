package gtm.com.databindingsample.activity.api;

import java.util.List;

import gtm.com.databindingsample.activity.models.UserDetailsModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 6/28/2017.
 */

public interface ApiService {

    @GET("todos")
    Call<List<UserDetailsModel>> getUsers();

}
