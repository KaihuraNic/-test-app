package com.example.a_heel.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StatusAdapter  extends RecyclerView.Adapter<StatusAdapter.ViewHolderClass>{
    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderClass  extends RecyclerView.ViewHolder{
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
        }
    }
}
