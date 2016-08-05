package comceleluengo.fb.petagram.vista;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import comceleluengo.fb.petagram.R;
import comceleluengo.fb.petagram.adaptadores.AnimalesAdapter;
import comceleluengo.fb.petagram.adaptadores.PerfilAdapter;
import comceleluengo.fb.petagram.pojo.Animales;
import comceleluengo.fb.petagram.presentador.IPerfilFragmentPresenter;
import comceleluengo.fb.petagram.presentador.PerfilFragmentPresenter;

public class PerfilFragment extends Fragment implements IPerfilFragment {

    private RecyclerView recycler;
    private TextView tvPerfil;
    private IPerfilFragmentPresenter presenter;
    private CircularImageView imgFoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        imgFoto = (CircularImageView) v.findViewById(R.id.imagenCircular);

        tvPerfil = (TextView) v.findViewById(R.id.tvPerfil);

        recycler = (RecyclerView) v.findViewById(R.id.reciclador);

        presenter = new PerfilFragmentPresenter(this, getContext(), obtieneCuentaInstagram());

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
    public PerfilAdapter crearAdaptador(ArrayList<Animales> animales) {
        //  Creamos un nuevo adaptador
        PerfilAdapter adapter = new PerfilAdapter(animales, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptador(PerfilAdapter adapter) {

        recycler.setAdapter(adapter);
    }

    @Override
    public void creaImagenPerfil(Animales usuario) {

        Picasso.with(getActivity())
                .load(usuario.getUrlFotoPerfil())
                .placeholder(R.drawable.dog_bone)
                .into(imgFoto);

        tvPerfil.setText(usuario.getNombreUsuario());

    }

    @Override
    public AnimalesAdapter crearAdaptadorFotos(ArrayList<Animales> animales) {
        //  Creamos un nuevo adaptador
        AnimalesAdapter adapter = new AnimalesAdapter(animales, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorFotos(AnimalesAdapter adapter) {

        recycler.setAdapter(adapter);
    }

    private String obtieneCuentaInstagram() {
        SharedPreferences misReferencias =  this.getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        return misReferencias.getString("perfilInstagram", "");
    }


}
