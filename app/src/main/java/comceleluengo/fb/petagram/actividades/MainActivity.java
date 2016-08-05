package comceleluengo.fb.petagram.actividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import comceleluengo.fb.petagram.R;
import comceleluengo.fb.petagram.adaptadores.PageAdapter;
import comceleluengo.fb.petagram.model.BusquedaResponse;
import comceleluengo.fb.petagram.model.UsuarioResponse;
import comceleluengo.fb.petagram.restApi.ConstantesRestApi;
import comceleluengo.fb.petagram.restApi.EndPointsApi;
import comceleluengo.fb.petagram.restApi.adapter.RestApiAdapter;
import comceleluengo.fb.petagram.vista.PerfilFragment;
import comceleluengo.fb.petagram.vista.RecyclerViewFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String perfilInstagram, token, usuario;
    private static final String TAG = "FIREBASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        setUpViewPager();

        ObtenerPerfilShared();
        if(perfilInstagram.equals("")){
            crearPerfilShared();
        }

        if (toolbar!=null){
            setSupportActionBar(toolbar);
        }

        //cambiamos el título de appbar
        getSupportActionBar().setTitle("    Mascotas");

        //Agregamos el Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.cat);

    }

    private ArrayList<Fragment> agregarFragments(){

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        //Mostramos los iconos de los fragments
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_recyclerview);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_perfil);


    }

    //Muestro en la appbar los actionviews
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudeopciones, menu);
        return true;
    }

    private void crearPerfilShared() {
        SharedPreferences perfilInstagram = getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = perfilInstagram.edit();
        editor.putString("perfilInstagram", "atena_alana");
        editor.commit();
    }

    private void ObtenerPerfilShared(){
        SharedPreferences misReferencias = getSharedPreferences("shared", Context.MODE_PRIVATE);
        perfilInstagram = misReferencias.getString("perfilInstagram", "");
    }


    //Defino lo que pasa cuando hago click en cualquier actionview de la appbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.misFavoritos:
                Intent miintent = new Intent(this, MisFavoritos.class);
                startActivity(miintent);
                return true;

            case R.id.contacto:
                Intent email = new Intent(this, Contacto.class);
                startActivity(email);
                return true;

            case R.id.acercade:
                Intent mibio = new Intent(this, MiBiografia.class );
                startActivity(mibio);
                return true;

            case R.id.configurarcuenta:
                Intent configcuenta = new Intent(this, ConfigurarCuenta.class );
                startActivity(configcuenta);
                return true;

            case R.id.recibirnotificaciones:
                obtenerDatos();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    public void obtenerDatos() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "ID_DISPOSITIVO: " + token);
        Log.d(TAG, "ID_USUARIO_INSTAGRAM: " + perfilInstagram);

        // Almacena el id del dispositivo y el nombre del usuario de instagram que introducimos en Configurar Cuenta
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endponits = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endponits.registrarTokenId(token, perfilInstagram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

}