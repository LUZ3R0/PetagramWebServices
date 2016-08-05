package comceleluengo.fb.petagram.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import comceleluengo.fb.petagram.model.AnimalesResponse;
import comceleluengo.fb.petagram.model.BusquedaResponse;
import comceleluengo.fb.petagram.restApi.ConstantesRestApi;
import comceleluengo.fb.petagram.restApi.EndPointsApi;
import comceleluengo.fb.petagram.restApi.deserializador.AnimalesDeserializador;
import comceleluengo.fb.petagram.restApi.deserializador.BusquedaDeserializador;
import comceleluengo.fb.petagram.restApi.deserializador.PerfilDeserializador;
import comceleluengo.fb.petagram.restApi.deserializador.SeguidosDeserializador;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Usuario on 22/06/2016.
 */
public class RestApiAdapter {

    public EndPointsApi establecerConexionRestApiInstagram(Gson gson){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return  retrofit.create(EndPointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AnimalesResponse.class, new AnimalesDeserializador());

        return gsonBuilder.create();

    }

    public Gson construyeGsonDeserializadorSeguidos(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AnimalesResponse.class, new SeguidosDeserializador());

        return gsonBuilder.create();

    }

    public Gson construyeGsonDeserealizadorBusqueda() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BusquedaResponse.class, new BusquedaDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserealizadorPerfil(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AnimalesResponse.class, new PerfilDeserializador());
        return gsonBuilder.create();
    }

    public EndPointsApi establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_TOKEN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndPointsApi.class);
    }

}
