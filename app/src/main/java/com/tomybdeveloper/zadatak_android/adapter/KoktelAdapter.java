package com.tomybdeveloper.zadatak_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.model.Koktel;
import com.tomybdeveloper.zadatak_android.model.KoktelResponse;

import java.util.ArrayList;
import java.util.List;

public class KoktelAdapter extends RecyclerView.Adapter<KoktelAdapter.ViewHolder> {

    private List<Koktel> koktel = new ArrayList<>();

    @NonNull
    @Override
    public KoktelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.web_item , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KoktelAdapter.ViewHolder holder, int position) {

        Koktel trenutniKoktel = koktel.get(position);

        holder.textViewIme.setText(trenutniKoktel.getIme());
        holder.textViewKategorija.setText(trenutniKoktel.getKategorija());

        Glide.with(holder.itemView.getContext()).load(trenutniKoktel.getSlika()).centerCrop().placeholder(R.drawable.slika_koktela).into(holder.imageViewKoktel);
    }

    @Override
    public int getItemCount() {
        return koktel.size();
    }

    public void setKoktelLista(List<Koktel> koktelLista) {

        koktel.clear();
        koktel.addAll(koktelLista);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewIme;
        private ImageView imageViewKoktel;
        private TextView textViewKategorija;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewIme = itemView.findViewById(R.id.tv_ime_koktela);
            imageViewKoktel = itemView.findViewById(R.id.iv_koktel);
            textViewKategorija = itemView.findViewById(R.id.tv_kategorija_koktela);
        }

    }
}
