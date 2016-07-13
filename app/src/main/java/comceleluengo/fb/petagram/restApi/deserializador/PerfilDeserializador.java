package comceleluengo.fb.petagram.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import comceleluengo.fb.petagram.model.AnimalesResponse;
import comceleluengo.fb.petagram.pojo.Animales;
import comceleluengo.fb.petagram.restApi.JsonKeys;

/**
 * Created by Usuario on 11/07/2016.
 */
public class PerfilDeserializador implements JsonDeserializer{

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        AnimalesResponse animalesResponse = gson.fromJson(json, AnimalesResponse.class);
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        animalesResponse.setAnimales(deserealizadorPerfil(jsonArray));
        return animalesResponse;
    }

    private ArrayList<Animales> deserealizadorPerfil(JsonArray jsonArray) {

        ArrayList<Animales> animales = new ArrayList<>();

        for(int i = 0; i < jsonArray.size(); i++ ){

            JsonObject animalesDataObject = jsonArray.get(i).getAsJsonObject();

            Animales animalActual = new Animales();
            JsonObject imagenes = animalesDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolution = imagenes.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            JsonObject likesJson = animalesDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);

            String urlFoto = stdResolution.get(JsonKeys.MEDIA_URL).getAsString();
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            animalActual.setUrlFoto(urlFoto);
            animalActual.setLikes(likes);

            animales.add(animalActual);
        }

        return animales;
    }

}
