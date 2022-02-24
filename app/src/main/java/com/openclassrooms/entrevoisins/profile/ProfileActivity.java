package com.openclassrooms.entrevoisins.profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class ProfileActivity extends AppCompatActivity implements Serializable {

    ImageView imageProfile;
    FloatingActionButton floatBtn;
    TextView neigName, neigName2, nameCity, numberPhone, aURL, tAbout;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageProfile = findViewById(R.id.iv_profile);
        floatBtn = findViewById(R.id.floatFav);
        neigName = findViewById(R.id.tv_name);
        neigName2 = findViewById(R.id.tv_name2);
        nameCity = findViewById(R.id.tv_city);
        numberPhone = findViewById(R.id.tv_phone);
        aURL = findViewById(R.id.tv_web);
        tAbout = findViewById(R.id.tv_about2);

        Neighbour iNeighbour = (Neighbour) getIntent().getSerializableExtra("profil");
        getDisplay(iNeighbour);

        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void getDisplay(Neighbour iNeighbour) {
        Glide.with(this).load(iNeighbour.getAvatarUrl()).into(imageProfile);
        neigName.setText(iNeighbour.getName());
        neigName2.setText(iNeighbour.getName());
        nameCity.setText(iNeighbour.getAddress());
        numberPhone.setText(iNeighbour.getPhoneNumber());

        tAbout.setText(iNeighbour.getAboutMe());
    }
}