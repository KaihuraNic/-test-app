package com.example.a_heel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_heel.R;
import com.example.a_heel.models.Survey;

import java.util.List;

public class SurveyListAdapter extends RecyclerView.Adapter<SurveyListAdapter.ViewHolderClass> {
    private Context context;
    private List<Survey> surveyList;
    private OnItemClickListener onClickListener;


    public SurveyListAdapter(Context context, List<Survey> surveyList, OnItemClickListener onClickListener) {
        this.context = context;
        this.surveyList = surveyList;
        this.onClickListener = onClickListener;
    }

    public interface OnItemClickListener {
        void onTransactionClick(Survey survey);
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_suervey_layout, parent, false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        holder.title.setText(surveyList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onTransactionClick(surveyList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return surveyList.size();
    }

    public class ViewHolderClass  extends RecyclerView.ViewHolder{
        TextView title;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tittle);
        }
    }
}
