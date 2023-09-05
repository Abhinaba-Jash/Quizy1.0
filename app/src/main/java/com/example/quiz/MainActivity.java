package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView im1,im2,im3,im4,im5,im6,im7,im8,im9,im10,im11,im12,im13,im14,im15,im16,im17,im18,im19,im20,im21,im22,im23,im24,im25,im26,image1,image2;
List<ModelData> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        im1=findViewById(R.id.imageView);
        im2=findViewById(R.id.imageView2);
        im3=findViewById(R.id.imageView3);
        im6=findViewById(R.id.imageView6);
        im7=findViewById(R.id.imageView7);
        im8=findViewById(R.id.imageView8);
        im11=findViewById(R.id.imageView11);
        im12=findViewById(R.id.imageView12);
        im13=findViewById(R.id.imageView13);
        clickCheck(im1,"linux","easy","0");
        clickCheck(im2,"linux","medium","0");
        clickCheck(im3,"linux","hard","0");
        clickCheck(im6,"devops","easy","0");
        clickCheck(im7,"devops","medium","0");
        clickCheck(im8,"devops","hard","0");
        clickCheck(im11,"docker","easy","0");
        clickCheck(im12,"docker","medium","0");
        clickCheck(im13,"docker","hard","0");
        clickCheck(image1,"","medium","2");
        clickCheck(image2,"","medium","1");
    }
void clickCheck(ImageView i, String topic, String diff,String ran){
    i.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Instantiate the RequestQueue.

            Intent intent=new Intent(MainActivity.this,TakeQuiz.class);
            intent.putExtra("topic",topic);
            intent.putExtra("diff",diff);
            intent.putExtra("limit", "10");
            intent.putExtra("ran", ran);
            startActivity(intent);
        }
    });

}





}