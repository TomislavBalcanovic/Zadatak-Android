package com.tomybdeveloper.zadatak_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomybdeveloper.zadatak_android.R;
import com.tomybdeveloper.zadatak_android.model.Zaposleni;

import java.util.ArrayList;
import java.util.List;

public class ZaposleniAdapter extends RecyclerView.Adapter<ZaposleniAdapter.ViewHolder> {

          private List<Zaposleni> zaposleni = new ArrayList<>();



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zaposleni_item , parent , false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

               Zaposleni trenutniZaposleni = zaposleni.get(position);

               holder.textViewIme.setText(trenutniZaposleni.getIme());
               holder.textViewPrezime.setText(trenutniZaposleni.getPrezime());
               holder.textViewSifra.setText(trenutniZaposleni.getSifra());
               holder.textViewGrad.setText(trenutniZaposleni.getGrad());
               holder.textViewMagacin.setText(trenutniZaposleni.getMagacin());

    }

    @Override
    public int getItemCount() {
        return zaposleni.size();
    }

    public void setZaposleniList(List<Zaposleni> zaposlenis) {

          zaposleni.clear();
          zaposleni.addAll(zaposlenis);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewIme;
        private final TextView textViewPrezime;
        private final TextView textViewSifra;
        private final TextView textViewGrad;
        private final TextView textViewMagacin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewIme = itemView.findViewById(R.id.tv_ime_naziv);
            textViewPrezime = itemView.findViewById(R.id.tv_pravo_prezime);
            textViewSifra = itemView.findViewById(R.id.tv_broj_sifraZaposleni);
            textViewGrad = itemView.findViewById(R.id.tv_ime_grada);
            textViewMagacin = itemView.findViewById(R.id.tv_broj_magacina);

        }
    }
}
