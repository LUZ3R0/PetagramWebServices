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
 * Created by Usuario on 23/06/2016.
 */
public class AnimalesDeserializador implements JsonDeserializer<AnimalesResponse> {
    @Override
    public AnimalesResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        AnimalesResponse animalesResponse = gson.fromJson(json, AnimalesResponse.class);
        JsonArray animalesResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        animalesResponse.setAnimales(deserializarAnimalesDeJson(animalesResponseData));
        return animalesResponse;
    }

    private ArrayList<Animales> deserializarAnimalesDeJson(JsonArray animalesResponseData){
        ArrayList<Animales> animales = new ArrayList<>();
        for (int i=0; i < animalesResponseData.size(); i++){
            JsonObject animalesResponseDataObject = animalesResponseData.get(i).getAsJsonObject();

            JsonObject userJson = animalesResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();
            String urlFotoProfile = userJson.get(JsonKeys.USER_IMAGEPROFILE).getAsString();
            String nombreUsuario = userJson.get(JsonKeys.USER_NAME).getAsString();

            JsonObject imageJson = animalesResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = animalesResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Animales animalActual = new Animales();
            animalActual.setId(id);
            animalActual.setNombreCompleto(nombreCompleto);
            animalActual.setUrlFoto(urlFoto);
            animalActual.setLikes(likes);
            animalActual.setUrlFotoPerfil(urlFotoProfile);
            animalActual.setNombreUsuario(nombreUsuario);

            animales.add(animalActual);

        }

        return animales;

    }

}
