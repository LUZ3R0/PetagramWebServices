package comceleluengo.fb.petagram.restApi;

import comceleluengo.fb.petagram.model.AnimalesResponse;
import comceleluengo.fb.petagram.model.BusquedaResponse;
import comceleluengo.fb.petagram.presentador.RecyclerViewFragmentPresenter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Usuario on 22/06/2016.
 */
public interface EndPointsApi {

    @GET(ConstantesRestApi.URL_GET_FOLLOWS_USER)
    Call<AnimalesResponse> getFollowsUsers();

    @GET(ConstantesRestApi.URL_ID_MEDIA_FOLLOWS)
    Call<AnimalesResponse> getRecentMediaUsers(@Path("id") String id);

    @GET(ConstantesRestApi.URL_SEARCH)
    Call<BusquedaResponse> getSearch(@Query("q") String usuario, @Query("access_token") String access_token);

}
