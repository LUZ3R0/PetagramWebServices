package comceleluengo.fb.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;

import comceleluengo.fb.petagram.basedatos.ConstructorBD;
import comceleluengo.fb.petagram.model.AnimalesResponse;
import comceleluengo.fb.petagram.pojo.Animales;
import comceleluengo.fb.petagram.restApi.EndPointsApi;
import comceleluengo.fb.petagram.restApi.adapter.RestApiAdapter;
import comceleluengo.fb.petagram.vista.IRecyclerViewFragmentView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Usuario on 13/06/2016.
 */
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorBD constructor;
    private ArrayList<Animales> animales, seguidores;
    public static String id;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerFollows();

    }

    @Override
    public void obtenerContactosBaseDatos() {
        constructor = new ConstructorBD(context);
        animales = constructor.obtenerDatos();
        mostrarContactosRV();

    }

    @Override
    public void obtenerFollows() {

        //Obtenemos los seguidores de sandbox
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFollowsUsers = restApiAdapter.construyeGsonDeserializadorSeguidos();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollowsUsers);
        Call<AnimalesResponse> animalesResponseCall = endPointsApi.getFollowsUsers();

        animalesResponseCall.enqueue(new Callback<AnimalesResponse>() {
            @Override
            public void onResponse(Call<AnimalesResponse> call, Response<AnimalesResponse> response) {
                AnimalesResponse animalesResponse = response.body(); //trae la data
                seguidores = animalesResponse.getAnimales(); //cargamos los datos desde el web service al arraylist
                for (int i=0; i<seguidores.size(); i++){

                    //Obtenemos sus ids
                    id = seguidores.get(i).getId();

                    obtenerMediaRecentFollows();

                }

                seguidores.clear();
            }

            @Override
            public void onFailure(Call<AnimalesResponse> call, Throwable t) {
                //Mensaje para usuarios
                Toast.makeText(context, "Algo pasó en la conexión, intenta de nuevo", Toast.LENGTH_LONG).show();
                //Mensaje para programadores
                Log.e("FALLO LA CONEXION", t.toString());

            }

        });

    }

    @Override
    public void obtenerMediaRecentFollows(){
        //Traemos las publicaciones a través de sus ids
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<AnimalesResponse> animalesResponseCall = endPointsApi.getRecentMediaUsers(id);

        animalesResponseCall.enqueue(new Callback<AnimalesResponse>() {
            @Override
            public void onResponse(Call<AnimalesResponse> call, Response<AnimalesResponse> response) {
                AnimalesResponse animalesResponses = response.body(); //trae la data
                animales=animalesResponses.getAnimales();
                seguidores.addAll(animales);

                mostrarContactosRV();

            }

            @Override
            public void onFailure(Call<AnimalesResponse> call, Throwable t) {
                //Mensaje para usuarios
                Toast.makeText(context, "Algo pasó en la conexión, intenta de nuevo", Toast.LENGTH_LONG).show();
                //Mensaje para programadores
                Log.e("FALLO LA CONEXION", t.toString());

            }
        });
    }


    @Override
    public void mostrarContactosRV() {

        iRecyclerViewFragmentView.inicializarAdaptador(iRecyclerViewFragmentView.crearAdaptador(seguidores));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }

}
