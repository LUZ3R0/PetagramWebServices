package comceleluengo.fb.petagram.adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import comceleluengo.fb.petagram.DetalleMascota;
import comceleluengo.fb.petagram.MainActivity;
import comceleluengo.fb.petagram.pojo.Animales;
import comceleluengo.fb.petagram.R;

public class AnimalesAdapter extends RecyclerView.Adapter<AnimalesAdapter.AnimalesViewHolder>{

    private ArrayList<Animales> items;
    Activity activity;

    public static class AnimalesViewHolder extends RecyclerView.ViewHolder {

        // Campos de un item
        private ImageView imagen;
        private CircularImageView imgFoto;
        private TextView likes;

        public AnimalesViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imgImagenGrid);
            likes = (TextView) v.findViewById(R.id.tvNumHuesitosGrid);
            imgFoto = (CircularImageView) v.findViewById(R.id.imagenCircular);

        }
    }

    public AnimalesAdapter(ArrayList<Animales> items, Activity activity) {

        this.items = items;
        this.activity = activity;

    }

    //Cantidad de elementos que contiene mi lista
    @Override
    public int getItemCount() {

        return items.size();
    }


    //Infla el layout y lo pasa al viewHolder para que el obtenga los views
    @Override
    public AnimalesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_grid, viewGroup, false);
        return new AnimalesViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final AnimalesViewHolder viewHolder, int i) {

        final Animales an = items.get(i);

        Picasso.with(activity)
                .load(an.getUrlFoto())
                .placeholder(R.drawable.dog_bone)
                .into(viewHolder.imagen);

        viewHolder.likes.setText(String.valueOf(an.getLikes()));

        viewHolder.imagen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent (activity, DetalleMascota.class);
                intent.putExtra("url", an.getUrlFoto());
                intent.putExtra("like", an.getLikes());
                activity.startActivity(intent);
            }

        });


    }



}
