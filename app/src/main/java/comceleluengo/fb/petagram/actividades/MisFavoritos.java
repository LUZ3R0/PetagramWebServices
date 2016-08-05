package comceleluengo.fb.petagram.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;

import comceleluengo.fb.petagram.R;
import comceleluengo.fb.petagram.adaptadores.AnimalesAdapter;
import comceleluengo.fb.petagram.basedatos.BaseDatos;
import comceleluengo.fb.petagram.pojo.Animales;

public class MisFavoritos extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recycler;
    private ArrayList<Animales> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_favoritos);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        if (toolbar!=null){
            setSupportActionBar(toolbar);
        }

        //botón de subir
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //cambiamos el título de appbar
        getSupportActionBar().setTitle("    Mis favoritos");

        //Agregamos el Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.cat);

        inicializarMascotas();

        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setClickable(false);
        recycler.setEnabled(false);

        // Usar un administrador para LinearLayout
        LinearLayoutManager lManager = new LinearLayoutManager(this);
        lManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(lManager);

        inicializarAdaptador();

    }

    public void  inicializarAdaptador() {
        // Crear un nuevo adaptador
        AnimalesAdapter adapter = new AnimalesAdapter(items, this);
        recycler.setAdapter(adapter);
    }
    public  void inicializarMascotas() {
        BaseDatos db = new BaseDatos(MisFavoritos.this);
        items = new ArrayList<>();
        items = db.obtenerFavoritos();

    }

}