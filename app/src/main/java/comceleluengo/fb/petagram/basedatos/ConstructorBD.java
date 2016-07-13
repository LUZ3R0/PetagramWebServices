package comceleluengo.fb.petagram.basedatos;

import android.content.ContentValues;
import android.content.Context;
import comceleluengo.fb.petagram.R;
import java.util.ArrayList;

import comceleluengo.fb.petagram.pojo.Animales;

/**
 * Created by Usuario on 13/06/2016.
 */
public class ConstructorBD {

    private Context context;
    private static final int LIKE = 1;

    public ConstructorBD(Context context){
        this.context = context;

    }

    public ArrayList<Animales> obtenerDatos() {

        ArrayList<Animales> misMascotas;
        BaseDatos db = new BaseDatos(context);
        misMascotas = db.obtenerAnimales();
        if (misMascotas.isEmpty()) {
            insertarOchoAnimales(db);
            misMascotas = db.obtenerAnimales();
        }

        return misMascotas;

    }

    public void insertarOchoAnimales(BaseDatos db){

        ContentValues cv = new ContentValues();

        cv.put(ConstantesBD.TABLE_MASCOTAS_NOMBRE, "Jerry");
        cv.put(ConstantesBD.TABLE_MASCOTAS_FOTO, R.drawable.jerry);
        db.insertarAnimales(cv);

        cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_MASCOTAS_NOMBRE, "Tom");
        cv.put(ConstantesBD.TABLE_MASCOTAS_FOTO, R.drawable.tom);
        db.insertarAnimales(cv);

        cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_MASCOTAS_NOMBRE, "A. de Santa");
        cv.put(ConstantesBD.TABLE_MASCOTAS_FOTO, R.drawable.ayudantedesanta);
        db.insertarAnimales(cv);

        cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_MASCOTAS_NOMBRE, "P. Loco");
        cv.put(ConstantesBD.TABLE_MASCOTAS_FOTO, R.drawable.pajaroloco);
        db.insertarAnimales(cv);

        cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_MASCOTAS_NOMBRE, "Mickey");
        cv.put(ConstantesBD.TABLE_MASCOTAS_FOTO, R.drawable.mickey);
        db.insertarAnimales(cv);

        cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_MASCOTAS_NOMBRE, "Pluto");
        cv.put(ConstantesBD.TABLE_MASCOTAS_FOTO, R.drawable.pluto);
        db.insertarAnimales(cv);

        cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_MASCOTAS_NOMBRE, "Garfield");
        cv.put(ConstantesBD.TABLE_MASCOTAS_FOTO, R.drawable.garfield);
        db.insertarAnimales(cv);

        cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_MASCOTAS_NOMBRE, "Stitch");
        cv.put(ConstantesBD.TABLE_MASCOTAS_FOTO, R.drawable.stitch);
        db.insertarAnimales(cv);

    }

    public void darLike (Animales animales){

        BaseDatos db = new BaseDatos(context);
        ContentValues cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_LIKES_ID_MASCOTA, animales.getId());
        cv.put(ConstantesBD.TABLE_LIKES_NUMERO_LIKES, LIKE);
        db.insertarLike(cv);

    }

    public int obtenerLikesAnimales(Animales animales){

        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesAnimales(animales);
    }

}
