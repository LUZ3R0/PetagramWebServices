package comceleluengo.fb.petagram.vista;

import java.util.ArrayList;

import comceleluengo.fb.petagram.adaptadores.AnimalesAdapter;
import comceleluengo.fb.petagram.pojo.Animales;

/**
 * Created by Usuario on 13/06/2016.
 */
public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public AnimalesAdapter crearAdaptador (ArrayList<Animales> animales);

    public void inicializarAdaptador (AnimalesAdapter adaptador);


}
