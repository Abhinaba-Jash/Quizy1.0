package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import org.w3c.dom.Text;

import java.util.List;

public class FinalWindow extends AppCompatActivity {
    List<AnswersData> data;
ImageView celebrate,share;
TextView finalScore;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_window);
        celebrate=findViewById(R.id.celebrate);
        finalScore=findViewById(R.id.score);
        recyclerView=findViewById(R.id.recyclerView);
        share=findViewById(R.id.share);
        // Load and display the GIF using Glide
        Glide.with(this)
                .load(R.drawable.giff) // Change to the resource ID or URL of your GIF
                .into(new ImageViewTarget<GifDrawable>(celebrate) {
                    @Override
                    protected void setResource(GifDrawable resource) {
                        // Set the loaded GIF as the image source
                        celebrate.setImageDrawable(resource);

                        // Start the GIF animation (optional)
                        resource.start();
                    }

                    @Override
                    public void onResourceReady(@NonNull GifDrawable resource, Transition<? super GifDrawable> transition) {
                        super.onResourceReady(resource, transition);



                    }
                }.getView());

        Intent intent=getIntent();
        String score=intent.getStringExtra("FinalScore");
       // String answer=intent.getStringExtra("Answer");
        // Inside your SecondActivity
        List<AnswersData> answerList = (List<AnswersData>) getIntent().getSerializableExtra("AnswerList");
        //List<AnswersData> customList = (List<CustomData>) getIntent().getSerializableExtra("customList");
        if (answerList != null) {
            //Toast.makeText(this, score+answerList.get(1).getQuestion(), Toast.LENGTH_SHORT).show();
            finalScore.setText("Your Score: "+score);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FinalWindowAdapter ad=new FinalWindowAdapter(getApplicationContext(),answerList);
        recyclerView.setAdapter(ad);
        } else {
            // Handle the case where data was not passed correctly
        }


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the text to share from the EditText
                String textToShare = score;

                // Create an Intent to share text
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Try this. My score is "+textToShare+" out of 10. What is your score?");

                // Start the share activity
                startActivity(Intent.createChooser(shareIntent, "Share your marks using.."));
            }
        });


    }
}