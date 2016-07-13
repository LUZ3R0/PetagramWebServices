package comceleluengo.fb.petagram.adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import comceleluengo.fb.petagram.R;
import comceleluengo.fb.petagram.pojo.Animales;

/**
 * Created by Usuario on 11/07/2016.
 */
public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.AnimalesViewHolder> {

    ArrayList<Animales> animales;
    Activity activity;

    public static class AnimalesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLikes;
        private CircularImageView imgFoto;
        private ImageView imgLikes;

        public AnimalesViewHolder(View v) {

            super(v);
            imgFoto = (CircularImageView) v.findViewById(R.id.imgFotoperfil);
            tvLikes = (TextView) v.findViewById(R.id.tvraitingperfil);
            imgLikes = (ImageView) v.findViewById(R.id.imgHuesoAmarilloperfil);
        }

    }

    public PerfilAdapter(ArrayList<Animales> animales, Activity activity){

        this.animales = animales;
        this.activity = activity;
    }

    @Override
    public AnimalesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_perfil,viewGroup,false);
        return new AnimalesViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AnimalesViewHolder viewHolder, final int i) {

        final Animales an = animales.get(i);

        viewHolder.tvLikes.setText(String.valueOf(an.getLikes()));

        Picasso.with(activity)
                .load(an.getUrlFotoPerfil())
                .placeholder(R.drawable.dog_bone_48)
                .into(viewHolder.imgFoto);

        viewHolder.imgLikes.setTag(viewHolder);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}