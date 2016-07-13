package comceleluengo.fb.petagram.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import comceleluengo.fb.petagram.R;
import comceleluengo.fb.petagram.adaptadores.AnimalesAdapter;
import comceleluengo.fb.petagram.pojo.Animales;
import comceleluengo.fb.petagram.presentador.IRecyclerViewFragmentPresenter;
import comceleluengo.fb.petagram.presentador.RecyclerViewFragmentPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {

    private RecyclerView recycler;
    private ArrayList<Animales> animales;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.reciclador);

        presenter = new RecyclerViewFragmentPresenter(this, getContext());

        return v;

    }

    @Override
    public void generarLinearLayoutVertical() {
        //Usamos un administrador para LinearLayout
        LinearLayoutManager lManager = new LinearLayoutManager(getActivity());
        lManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(lManager);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(gridLayoutManager);
    }

    @Override
    public AnimalesAdapter crearAdaptador(ArrayList<Animales> animales) {

        //  Creamos un nuevo adaptador
        AnimalesAdapter adapter = new AnimalesAdapter(animales, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptador(AnimalesAdapter adapter) {
        //Inicializamos el adaptador
        recycler.setAdapter(adapter);
    }

}
