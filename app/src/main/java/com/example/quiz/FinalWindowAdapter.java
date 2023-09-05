package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FinalWindowAdapter extends RecyclerView.Adapter<FinalWindowAdapter.MyHolder> {
List<AnswersData> data;
Context context;
    public FinalWindowAdapter(Context context, List<AnswersData> data) {
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public FinalWindowAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_final_window_adapter,parent,false);
        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FinalWindowAdapter.MyHolder holder, int position) {

      //  String id=data.get(position).getId();
        String question=data.get(position).getQuestion();
        int n=position+1;
        holder.adquestion.setText(n+". "+question);
        String correct=data.get(position).getCorrect_option();
String yourInput=data.get(position).getYourInput();
if(yourInput.equals(correct)){
    holder.adanswer.setText("Ans: "+data.get(position).getExplanation()+"\nCorrect answer: "+correct);

}else{
    holder.adanswer.setText("Ans: "+data.get(position).getExplanation()+"\nYour Input: "+yourInput+"\nCorrect answer: "+correct);
}
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView adquestion,adanswer;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            adanswer=itemView.findViewById(R.id.adanswer);
            adquestion=itemView.findViewById(R.id.adquestion);

        }
    }
}