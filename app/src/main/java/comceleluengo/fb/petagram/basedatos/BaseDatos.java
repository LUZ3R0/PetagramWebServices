package comceleluengo.fb.petagram.basedatos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import comceleluengo.fb.petagram.pojo.Animales;

/**
 * Created by Usuario on 13/06/2016.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;
    Activity activity;

    public BaseDatos(Context context) {
        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBD.TABLE_MASCOTAS + "(" +
                ConstantesBD.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                ConstantesBD.TABLE_MASCOTAS_FOTO + " INTEGER" + ")";

        String queryCrearTablaLikes = "CREATE TABLE " + ConstantesBD.TABLE_LIKES + "(" +
                ConstantesBD.TABLE_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLE_LIKES_ID_MASCOTA + " INTEGER, " +
                ConstantesBD.TABLE_LIKES_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBD.TABLE_LIKES_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBD.TABLE_MASCOTAS + "(" +
                ConstantesBD.TABLE_MASCOTAS_ID + ")" + ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikes);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_LIKES);

        onCreate(db);

    }

    public ArrayList<Animales> obtenerAnimales(){

        ArrayList<Animales> animales = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBD.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        /*while (registros.moveToNext()){

            Animales animalActual = new Animales();
            animalActual.setId(registros.getInt(0));
            animalActual.setNombre(registros.getString(1));
            animalActual.setImagen(registros.getInt(2));

            String querylikes ="SELECT COUNT("+ConstantesBD.TABLE_LIKES_NUMERO_LIKES+") as likes" +
                    " FROM " + ConstantesBD.TABLE_LIKES +
                    " WHERE " + ConstantesBD.TABLE_LIKES_ID_MASCOTA + "=" + animalActual.getId();
            Cursor registroslikes = db.rawQuery(querylikes, null);

            if (registroslikes.moveToNext()){

                animalActual.setLikes(registroslikes.getInt(0));

            }else{

                animalActual.setLikes(0);
            }


            animales.add(animalActual);
        }*/

        db.close();

        return animales;

    }

    public void insertarAnimales(ContentValues cv){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_MASCOTAS, null, cv);
        db.close();

    }

    public void insertarLike (ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_LIKES, null, cv);
        db.close();

    }

    public int obtenerLikesAnimales (Animales animales){

        int likes = 0;
        String query = "SELECT COUNT("+ConstantesBD.TABLE_LIKES_NUMERO_LIKES+")" +
                " FROM " + ConstantesBD.TABLE_LIKES +
                " WHERE " + ConstantesBD.TABLE_LIKES_ID_MASCOTA + "="+animales.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()) {

            likes = registros.getInt(0);

            registros.moveToNext();

        }

        db.close();

        return likes;
    }

    public ArrayList<Animales> obtenerFavoritos() {

        ArrayList<Animales> animales = new ArrayList<>();
        String query = "SELECT *, COUNT(*) FROM " + ConstantesBD.TABLE_MASCOTAS +
                " masc INNER JOIN " + ConstantesBD.TABLE_LIKES + " lik ON masc." +
                ConstantesBD.TABLE_MASCOTAS_ID + "= lik." +
                ConstantesBD.TABLE_LIKES_ID_MASCOTA  + " ORDER BY " +
                ConstantesBD.TABLE_LIKES_NUMERO_LIKES + " DESC LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        /*while (registros.moveToNext()){

            Animales animalActual = new Animales();
            animalActual.setId(registros.getInt(0));
            animalActual.setNombre(registros.getString(1));
            animalActual.setImagen(registros.getInt(2));

            String querylikes ="SELECT COUNT("+ConstantesBD.TABLE_LIKES_NUMERO_LIKES+") as likes" +
                    " FROM " + ConstantesBD.TABLE_LIKES +
                    " WHERE " + ConstantesBD.TABLE_LIKES_ID_MASCOTA + "=" + animalActual.getId();
            Cursor registroslikes = db.rawQuery(querylikes, null);

            if (registroslikes.moveToNext()){

                animalActual.setLikes(registroslikes.getInt(0));

            }else{

                animalActual.setLikes(0);
            }


            animales.add(animalActual);
        }*/

        db.close();

        return animales;

    }



}

