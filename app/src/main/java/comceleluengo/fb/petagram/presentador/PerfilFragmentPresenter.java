package comceleluengo.fb.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;

import java.util.ArrayList;

import comceleluengo.fb.petagram.basedatos.ConstructorBD;
import comceleluengo.fb.petagram.model.AnimalesResponse;
import comceleluengo.fb.petagram.model.BusquedaResponse;
import comceleluengo.fb.petagram.pojo.Animales;
import comceleluengo.fb.petagram.restApi.ConstantesRestApi;
import comceleluengo.fb.petagram.restApi.EndPointsApi;
import comceleluengo.fb.petagram.restApi.adapter.RestApiAdapter;
import comceleluengo.fb.petagram.vista.IPerfilFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Usuario on 23/06/2016.
 */
public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {

    private IPerfilFragment iPerfilFragment;
    private Context context;
    private ConstructorBD constructor;
    private ArrayList<Animales> animales;
    private String cuenta;
    private Animales usuario = new Animales();
    public static String id;

    public PerfilFragmentPresenter(IPerfilFragment iPerfilFragment, Context context, String usuario) {
        this.iPerfilFragment = iPerfilFragment;
        this.context = context;
        this.cuenta = usuario;
        obtenerPerfilInstagram();

    }

    @Override
    public void obtenerContactosBaseDatos() {
        constructor = new ConstructorBD(context);
        animales = constructor.obtenerDatos();
        mostrarDatosPerfilRV();

    }

    @Override
    public void obtenerFotoPerfilInstagram() {

        if(!usuario.getNombreUsuario().equals("No encontrado")){
            RestApiAdapter  restApiAdapter = new RestApiAdapter();
            Gson gson = restApiAdapter.construyeGsonDeserealizadorPerfil();
            EndPointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);

            animales = new ArrayList<>();

            id = usuario.getId();

            Call<AnimalesResponse> perfilResponseCall = endpointsApi.getRecentMediaUsers(id);
            perfilResponseCall.enqueue(new Callback<AnimalesResponse>() {
                @Override
                public void onResponse(Call<AnimalesResponse> call, Response<AnimalesResponse> response) {
                    AnimalesResponse perfilResponse = response.body();
                    animales = perfilResponse.getAnimales();

                    mostrarDatosPerfilRV();

                    obtenerImagenesRecientes();
                }

                @Override
                public void onFailure(Call<AnimalesResponse> call, Throwable t) {
                    Toast.makeText(context, "Error al cargar perfil, ningún usuario ingresado/ el usuario ingresado es inexistente/ el usuario no le ha otorgado permisos en modo sandbox para obtener sus datos", Toast.LENGTH_LONG).show();
                }
            });

        }else{

            animales =  new ArrayList<>();
            mostrarDatosPerfilRV();
        }
    }

    @Override
    public void obtenerPerfilInstagram() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonSearch = restApiAdapter.construyeGsonDeserealizadorBusqueda();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonSearch);

        Call<BusquedaResponse> searchResponseCall = endPointsApi.getSearch(cuenta, ConstantesRestApi.ACCESS_TOKEN);
        searchResponseCall.enqueue(new Callback<BusquedaResponse>() {
            @Override
            public void onResponse(Call<BusquedaResponse> call, Response<BusquedaResponse> response) {
                BusquedaResponse busquedaResponse =  response.body();

                usuario = busquedaResponse.getCuenta();

                if (!usuario.getNombreUsuario().equals("NoEncontrado")){
                    obtenerFotoPerfilInstagram();
                }else{
                    usuario.setNombreUsuario("No encontrado");
                    obtenerFotoPerfilInstagram();
                }

            }

            @Override
            public void onFailure(Call<BusquedaResponse> call, Throwable t) {
                //Mensaje para usuarios
                Toast.makeText(context, "Error al cargar perfil, ningún usuario ingresado.", Toast.LENGTH_LONG).show();
                //Mensaje para programadores
                Log.e("USUARIO NO INGRESADO", t.toString());

            }

        });

    }

    @Override
    public void mostrarDatosPerfilRV() {

        iPerfilFragment.creaImagenPerfil(usuario);
        iPerfilFragment.inicializarAdaptador(iPerfilFragment.crearAdaptador(animales));
        iPerfilFragment.generarGridLayout();
    }

    @Override
    public void mostrarFotosRV() {

        iPerfilFragment.inicializarAdaptadorFotos(iPerfilFragment.crearAdaptadorFotos(animales));
        iPerfilFragment.generarGridLayout();
    }

    @Override
    public void obtenerImagenesRecientes() {

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

                 mostrarFotosRV();

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

}
