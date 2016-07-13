package comceleluengo.fb.petagram.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import comceleluengo.fb.petagram.model.BusquedaResponse;
import comceleluengo.fb.petagram.pojo.Animales;
import comceleluengo.fb.petagram.restApi.JsonKeys;

/**
 * Created by Usuario on 11/07/2016.
 */
public class BusquedaDeserializador implements JsonDeserializer{

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        BusquedaResponse busquedaResponse = gson.fromJson(json, BusquedaResponse.class);
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        busquedaResponse.setCuenta(deserealizarBusqueda(jsonArray));
        return busquedaResponse;
    }

    private Animales deserealizarBusqueda(JsonArray jsonArray) {

        Animales animalActual = new Animales();

        if( jsonArray.size() > 0 ){

            JsonObject searchDataObject = jsonArray.get(0).getAsJsonObject();

            String nombreUsuario = searchDataObject.get(JsonKeys.USER_NAME).getAsString();
            String urlFotoProfile = searchDataObject.get(JsonKeys.USER_IMAGEPROFILE).getAsString();
            String id = searchDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = searchDataObject.get(JsonKeys.USER_FULLNAME).getAsString();

            animalActual.setNombreCompleto(nombreCompleto);
            animalActual.setNombreUsuario(nombreUsuario);
            animalActual.setUrlFotoPerfil(urlFotoProfile);
            animalActual.setId(id);

        }else

            animalActual.setNombreUsuario("Inexistente");

            return animalActual;

    }

}
