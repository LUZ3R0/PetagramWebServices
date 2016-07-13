package comceleluengo.fb.petagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigurarCuenta extends AppCompatActivity {

    private android.support.design.widget.TextInputEditText EditUsuario;
    private Button ConfCuenta;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        EditUsuario = (android.support.design.widget.TextInputEditText) findViewById(R.id.EditUsuario);
        ConfCuenta = (Button) findViewById(R.id.butConfCuenta);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        //botón de subir
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Agregamos el Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.cat);

        //Cambiamos el título de la toolbar
        getSupportActionBar().setTitle("    Configurar Cuenta");

        ConfCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarCuenta();
            }
        });

    }

    private void GuardarCuenta() {
        String usuario = EditUsuario.getText().toString().trim();
        if(ValidaCampo(usuario)){
            SharedPreferences perfilInstagram = getSharedPreferences("shared", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = perfilInstagram.edit();
            editor.putString("perfilInstagram", usuario);
            editor.commit();

            Toast.makeText(this, "La cuenta se guardó correctamente", Toast.LENGTH_SHORT).show();

        }
        else{
            EditUsuario.setError("Ingrese una cuenta de usuario");
        }

        //Volvemos a la actividad principal y refrescamos para que cargue el nuevo usuario
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        this.finish();
    }

    private boolean ValidaCampo(String usuario) {
        if(usuario.isEmpty() || usuario == null || usuario.length() == 0)
            return false;
        else
            return true;
    }


}
