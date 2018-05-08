package com.example.yanirayanes.parcialprogra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context MyContext;
    private List<Persona> MyData;
    private boolean favorito;
    private Activity activity;

    public RecyclerViewAdapter(Context MyContext, List<Persona> MyData){
        this.MyContext = MyContext;
        this.MyData = MyData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view;
        LayoutInflater Inflater = LayoutInflater.from(MyContext);
        view = Inflater.inflate(R.layout.cardview_obj_persona, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.nombre_persona.setText(MyData.get(position).getNombre());
        holder.img_persona.setImageResource(MyData.get(position).getThumbnail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyContext, info_de_persona.class);
                intent.putExtra("Nombre", MyData.get(position).getNombre());
                intent.putExtra("Telefono", MyData.get(position).getTelefono());
                MyContext.startActivity(intent);
            }
        });

        if(MyData.get(position).yesorno()){
            holder.imgbtn_fav.setImageResource(R.drawable.fav1);
        }else {
            holder.imgbtn_fav.setImageResource(R.drawable.fav0);
        }

        holder.imgbtn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContactoFavorito(position)){
                    holder.imgbtn_fav.setImageResource(R.drawable.fav1);
                    ((MainActivity)MyContext).addFavs(MyData.get(position));
                }else {
                    holder.imgbtn_fav.setImageResource(R.drawable.fav0);
                    ((MainActivity)MyContext).eraseFavourite(MyData.get(position).getNombre());
                    }
            }
        });
    }

    @Override
    public int getItemCount(){
        return MyData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        Button btn_contactos, btn_favoritos, btn_agregar, btn_buscar;
        ImageButton imgbtn_fav;
        TextView nombre_persona;
        ImageView img_persona;
        CardView cardView, cardViewAdd;

        public MyViewHolder(View itemView) {
            super(itemView);

            btn_contactos = (Button) itemView.findViewById(R.id.btn_contactos);
            btn_favoritos = (Button) itemView.findViewById(R.id.btn_favoritos);
            btn_agregar = (Button) itemView.findViewById(R.id.btn_agregar);
            btn_buscar = (Button) itemView.findViewById(R.id.btn_buscar);
            nombre_persona = (TextView) itemView.findViewById(R.id.nombre_persona);
            img_persona = (ImageView) itemView.findViewById(R.id.img_persona);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            imgbtn_fav = (ImageButton) itemView.findViewById(R.id.imgbtn_fav);

        }
    }

    public boolean ContactoFavorito(int position){
        MyData.get(position).set(!MyData.get(position).verificarFav());
        return MyData.get(position).verificarFav();
    }

    public boolean VerificarFav(){
        return favorito;
    }

    public void filterList(List<Persona> filteredList) {
        MyData = filteredList;
        notifyDataSetChanged();

    }
}
