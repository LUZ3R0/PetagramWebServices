package comceleluengo.fb.petagram.vista;

import java.util.ArrayList;

import comceleluengo.fb.petagram.adaptadores.AnimalesAdapter;
import comceleluengo.fb.petagram.adaptadores.PerfilAdapter;
import comceleluengo.fb.petagram.pojo.Animales;

/**
 * Created by Usuario on 23/06/2016.
 */
public interface IPerfilFragment {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public PerfilAdapter crearAdaptador (ArrayList<Animales> animales);

    public void inicializarAdaptador (PerfilAdapter adapter);

    public void creaImagenPerfil(Animales usuario);

    public AnimalesAdapter crearAdaptadorFotos (ArrayList<Animales> animales);

    public void inicializarAdaptadorFotos (AnimalesAdapter adapter);

}
