package comceleluengo.fb.petagram.pojo;

/**
 * Created by Usuario on 06/05/2016.
 */
public class Animales {

    private int likes;
    private String id, nombreCompleto, urlFoto, nombreUsuario;
    private String urlFotoPerfil;


    public Animales(String urlFoto, String nombreCompleto, int likes, String urlFotoPerfil, String nombreUsuario) {
        this.urlFoto = urlFoto;
        this.nombreCompleto = nombreCompleto;
        this.likes = likes;
        this.urlFotoPerfil = urlFotoPerfil;
        this.nombreUsuario = nombreUsuario;
    }

    public Animales() {

    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlFotoPerfil() { return urlFotoPerfil; }

    public void setUrlFotoPerfil(String urlFotoPerfil) { this.urlFotoPerfil = urlFotoPerfil; }

    public String getNombreUsuario() { return nombreUsuario; }

    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

}
