package com.example.recyclerviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.recyclerviewandroidapp.adapter.EmailAdapter;
import com.example.recyclerviewandroidapp.models.ItemEmail;

import java.util.ArrayList;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemEmail> arrayList, arrayAuto;
    RecyclerView recyclerView;
    Button btnFavorite;
    EditText edt_textView;
    EmailAdapter emailAdapter;
    boolean isShowingFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnFavorite = (Button) findViewById(R.id.btnFavorite);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Faker faker = new Faker();

        arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(new ItemEmail(faker.name.name(), faker.lorem.sentence()));
        }
        arrayAuto = new ArrayList<ItemEmail>(arrayList);

        emailAdapter = new EmailAdapter(arrayList, this);
        recyclerView.setAdapter(emailAdapter);
        isShowingFavorite = false;
        findViewById(R.id.btnFavorite).requestFocus();
        findViewById(R.id.btnFavorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowingFavorite = !isShowingFavorite;
                if (isShowingFavorite)
                    emailAdapter.showFavorite();
                else
                    emailAdapter.showAll();
            }
        });

        edt_textView = (EditText) findViewById(R.id.edt_textView);

        edt_textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();

                if (keyword.length() > 2) {
                    emailAdapter.search(keyword);
                } else {
                    emailAdapter.showAll();
                }

            }
        });

    }
}
