package com.tomybdeveloper.zadatak_android.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.model.Kupac;
import com.tomybdeveloper.zadatak_android.model.KupacWithZaposleni;

import java.util.ArrayList;
import java.util.List;

public class KupacAdapter extends RecyclerView.Adapter<KupacAdapter.ViewHolder> {

    private List<KupacWithZaposleni> kupac = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public KupacAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kupac_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        KupacWithZaposleni trenutniKupac = kupac.get(position);

        holder.textViewNaziv.setText(trenutniKupac.getNaziv());
        holder.textViewPib.setText(trenutniKupac.getPib());
        holder.textViewSifra.setText(trenutniKupac.getSifra());
        holder.textViewPripada.setText(trenutniKupac.getZaposleni());
    }

    @Override
    public int getItemCount() {
        return kupac.size();
    }

    public void setKupacList(List<KupacWithZaposleni> kupacs) {
        kupac.clear();
        kupac.addAll(kupacs);

        notifyDataSetChanged();
    }

    public KupacWithZaposleni getKupacAt(int adapterPosition) {
        return kupac.get(adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewNaziv;
        private final TextView textViewPib;
        private final TextView textViewSifra;
        private final TextView textViewPripada;
        private final ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNaziv = itemView.findViewById(R.id.tv_ime_naziv);
            textViewPib = itemView.findViewById(R.id.tv_broj_pib);
            textViewSifra = itemView.findViewById(R.id.tv_broj_sifre);
            textViewPripada = itemView.findViewById(R.id.tv_kupacZaposleni);
            imageButton = itemView.findViewById(R.id.imageButton);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   int position = getAdapterPosition();
                    listener.onItemClick(kupac.get(position));

                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(KupacWithZaposleni kupac);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
