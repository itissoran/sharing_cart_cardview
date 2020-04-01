package com.sirwaan.cardviewsharingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MySharingCard mySharingCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySharingCard = findViewById(R.id.mySharingCard);

        init();


    }

    private void init() {
        mySharingCard.getSocialbtn1().setOnClickListener(this);
        mySharingCard.getSocialbtn2().setOnClickListener(this);
        mySharingCard.getSocialbtn3().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == mySharingCard.getSocialbtn1().getId()) {

            Toast.makeText(this, ((AppCompatButton )v).getText(), Toast.LENGTH_SHORT).show();


        } else if (id == mySharingCard.getSocialbtn2().getId()) {

            Toast.makeText(this, ((AppCompatButton )v).getText(), Toast.LENGTH_SHORT).show();


        } else if (id == mySharingCard.getSocialbtn3().getId()) {
            Toast.makeText(this, ((AppCompatButton )v).getText(), Toast.LENGTH_SHORT).show();

        } else {
        }


    }
}
