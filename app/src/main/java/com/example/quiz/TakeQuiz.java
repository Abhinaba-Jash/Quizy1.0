package com.example.quiz;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TakeQuiz extends AppCompatActivity {
List<ModelData>data;
    int i=0;
List<AnswersData> answer;
      // progressBar.setVisibility(View.VISIBLE);
    String apiKey="DY2VcyQWXveMtFMKWVhD5FxAZhgg0SmytNPtjjBA";
    String url="https://quizapi.io/api/v1/questions?apiKey=" + apiKey+"&limit=10";
TextView question,a,b,c,d,timerShow;
Button next,previous;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
       question = findViewById(R.id.question);
        a = findViewById(R.id.optiona);
        b = findViewById(R.id.optionb);
        c = findViewById(R.id.optionc);
        d = findViewById(R.id.optiond);
        next = findViewById(R.id.next);
        timerShow=findViewById(R.id.timer);
        String topic = getIntent().getStringExtra("topic");
        String diff = getIntent().getStringExtra("diff");
        //String limit = getIntent().getStringExtra("limit");
        String ran = getIntent().getStringExtra("ran");

        data = new ArrayList<>();
        answer=new ArrayList<>();

        if(Integer.parseInt(ran)==2){
            timerShow.setVisibility(View.VISIBLE);
            url="https://quizapi.io/api/v1/questions?apiKey=" + apiKey+"&limit=20";
            startTimer();
        }


        if(Integer.parseInt(ran)==0) {
            url = "https://quizapi.io/api/v1/questions?apiKey=" + apiKey + "&category=" + topic + "&difficulty=" + diff + "&limit=5";
        }
        loadApi();

    }

  //  }
    @SuppressLint("SetTextI18n")
    private void setEverything(int i) {
        int n1=i+1;
        question.setText(Integer.toString(n1)+". "+data.get(i).getQuestion());
        a.setText(data.get(i).getA());
        b.setText(data.get(i).getB());
        c.setText(data.get(i).getC());
        d.setText(data.get(i).getD());
    }
    final int[] score = {0};
    private void loadApi() {
        RequestQueue queue = Volley.newRequestQueue(TakeQuiz.this);

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++) {
                            JSONObject jsonObject = null;

                            try {
                                jsonObject = response.getJSONObject(i);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            String question = null;
                            String id = null;
                            String a = null;
                            String b = null;
                            String c = null;
                            String d = null;
                            String e1 = null;
                            String f = null;
                            String explanation = null;
                            try {
                                id = jsonObject.getString("id");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
//                            Toast.makeText(TakeQuiz.this, id, Toast.LENGTH_SHORT).show();
                            try {
                                question = jsonObject.getString("question");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                explanation = jsonObject.getString("explanation");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            JSONObject answers = null;
                            try {
                                answers = jsonObject.getJSONObject("answers");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                a = answers.getString("answer_a");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                b = answers.getString("answer_b");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                c = answers.getString("answer_c");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                d = answers.getString("answer_d");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                e1 = answers.getString("answer_e");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                f = answers.getString("answer_f");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            JSONObject correct_answers = null;
                            try {
                                correct_answers = jsonObject.getJSONObject("correct_answers");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            String correct = "";
                            String c_a = null;
                            try {
                                c_a = correct_answers.getString("answer_a_correct");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (c_a.equals("true")) {
                                correct=correct.replace("",a);
                            }
                            String c_b = null;
                            try {
                                c_b = correct_answers.getString("answer_b_correct");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (c_b.equals("true")) {
                                correct=correct.replace("",b);
                            }
                            String c_c = null;
                            try {
                                c_c = correct_answers.getString("answer_c_correct");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (c_c.equals("true")) {
                                correct=correct.replace("",c);
                            }
                            String c_d = null;
                            try {
                                c_d = correct_answers.getString("answer_d_correct");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (c_d.equals("true")) {
                                correct=correct.replace("",d);
                            }
                            String c_e = null;
                            try {
                                c_e = correct_answers.getString("answer_e_correct");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (c_e.equals("true")) {
                                correct=correct.replace("",e1);
                            }
                            String c_f = null;
                            try {
                                c_f = correct_answers.getString("answer_f_correct");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (c_f.equals("true")) {
                                correct=correct.replace("",f);
                            }
                            ModelData newData = new ModelData(id, question, a, b, c, d, correct, explanation);
                            data.add(newData);
                        }
                        setEverything(i);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final String[] selectedOption = new String[1];
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the selected RadioButton's ID

                RadioButton selectedRadioButton = findViewById(checkedId);

                if (selectedRadioButton != null) {
                    selectedOption[0] = selectedRadioButton.getText().toString();
                }
            }
        });
        int id=radioGroup.getCheckedRadioButtonId();
        if(id==-1){
            selectedOption[0]="";
        }
        final String[] exp = new String[1];
        final String[] btntxt = new String[1];
        final String[] yourInput = new String[1];
        next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                String que = data.get(i).getQuestion();
                String id = data.get(i).getId();
                String correct_option = data.get(i).getCorrect();
                exp[0] = "Incorrect";
                if(Objects.equals(selectedOption[0], "")){
                   yourInput[0] ="";
                    exp[0]="Not Submitted.";
                }

                yourInput[0]=selectedOption[0];

                btntxt[0] = next.getText().toString();


submitAnswer(new String[]{selectedOption[0]},que,id,correct_option, new String[]{exp[0]}, new String[]{yourInput[0]},i);
                if(btntxt[0].equals("Submit")){
                    StartFinalWindow();
                }
                if(i==data.size()-2) {
                    next.setText("Submit");
                }
                i++;
                setEverything(i);

                radioGroup.clearCheck();
            }

        });


        }

    private void submitAnswer(String[] selectedOption, String que, String id, String correct_option, String[] exp, String[] yourInput,int i) {
        if(selectedOption[0].equals(data.get(i).getCorrect())){

            // Increase the processing time by delaying execution
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This code will run after the delay
                    ++score[0];
                    exp[0] ="Correct";
                    AnswersData newAnswerData = new AnswersData(id, que, correct_option, exp[0], yourInput[0]);
                   // Toast.makeText(TakeQuiz.this, ""+score[0], Toast.LENGTH_SHORT).show();
                    answer.add(newAnswerData);

                }
            }, 0);
        }else {
            AnswersData newAnswerData = new AnswersData(id, que, correct_option, exp[0], yourInput[0]);
            answer.add(newAnswerData);

        }
    }


    private void StartFinalWindow() {
       // Toast.makeText(TakeQuiz.this, "~"+score[0], Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TakeQuiz.this, FinalWindow.class);
        intent.putExtra("FinalScore", ""+score[0]);
        intent.putExtra("AnswerList", (Serializable) answer);
        startActivity(intent);
        finish();
    }

    private void startTimer() {
        // 60 seconds timer
        CountDownTimer timer = new CountDownTimer(60000, 1000) { // 60 seconds timer
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                int sec = (int) (millisUntilFinished / 1000);
                timerShow.setText(sec + "");
            }

            public void onFinish() {
                StartFinalWindow();
            }
        }.start();
    }


}