package comceleluengo.fb.petagram.restApi;

import comceleluengo.fb.petagram.presentador.RecyclerViewFragmentPresenter;

/**
 * Created by Usuario on 22/06/2016.
 */
public final class ConstantesRestApi {

    //https://api.instagram.com/v1/ (URL BASE)

    public static final String VERSIONINST = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSIONINST;
    public static final String ACCESS_TOKEN = "2163701855.91ddccc.e11e2f36a8fc4e2b89b2673de85a6b9b";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";

    //Buscar seguidores
    //https://api.instagram.com/v1/users/self/follows?access_token=2163701855.91ddccc.e11e2f36a8fc4e2b89b2673de85a6b9b

    public static final String KEY_GET_FOLLOWS_USER = "users/self/follows";
    public static final String URL_GET_FOLLOWS_USER = KEY_GET_FOLLOWS_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //Media recent de los seguidores
    //https://api.instagram.com/v1/users/3452990908/media/recent/?access_token=2163701855.91ddccc.e11e2f36a8fc4e2b89b2673de85a6b9b

    public static final String KEY_USERS = "users/{id}/media/recent/";
    public static final String URL_ID_MEDIA_FOLLOWS = KEY_USERS + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //Buscar por nombre de usuario
    //https://api.instagram.com/v1/users/search?q=usuario&access_token=2163701855.91ddccc.e11e2f36a8fc4e2b89b2673de85a6b9b

    public static final String KEY_SEARCH = "users/search";
    public static final String URL_SEARCH = KEY_SEARCH;

    //Registro
    public static final String ROOT_URL_TOKEN = "https://young-journey-16330.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario";


}
