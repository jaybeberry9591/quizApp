package com.example.quizapp_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GKActivity extends AppCompatActivity {

    List<Question> questionList;
    TextView gkTextView;
    int quid = 0;
    Question currentQuestion;
    DbHelper dbHelper;
    RecyclerView recyclerView;

    ArrayList<String> id, question, answer;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_k);

        dbHelper = new DbHelper(GKActivity.this);
        questionList = dbHelper.getAllQuestions();
        recyclerView = findViewById(R.id.recycleView);
        currentQuestion = questionList.get(quid);

        Toast.makeText(getApplicationContext(), " "+ dbHelper.getRowCount(), Toast.LENGTH_LONG).show();

        id = new ArrayList<>();
        question = new ArrayList<>();
        answer = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(GKActivity.this, id, question, answer);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GKActivity.this));

    }

    void storeDataInArray(){
        Cursor cursor = dbHelper.readAllData();

            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                question.add(cursor.getString(1));
                answer.add(cursor.getString(2));

            }



    }

}