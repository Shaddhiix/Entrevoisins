package com.openclassrooms.entrevoisins.profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class ProfileActivity extends AppCompatActivity implements Serializable {

    ImageView imageProfile;
    FloatingActionButton floatBtn;
    TextView neigName, neigName2, nameCity, numberPhone, fbURL, tAbout;

    private NeighbourApiService nApiService;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageProfile = findViewById(R.id.iv_profile);
        floatBtn = findViewById(R.id.floatFav);
        neigName = findViewById(R.id.tv_name);

        //CardView 1
        neigName2 = findViewById(R.id.tv_name2);
        nameCity = findViewById(R.id.tv_city);
        numberPhone = findViewById(R.id.tv_phone);
        fbURL = findViewById(R.id.tv_web);

        //CardView 2
        tAbout = findViewById(R.id.tv_about2);

        nApiService = DI.getNeighbourApiService();

        Neighbour iNeighbour = (Neighbour) getIntent().getSerializableExtra("profil");
        getDisplay(iNeighbour);
    }

    /** Afficher les Data's */
    private void getDisplay(Neighbour iNeighbour) {
        Glide.with(this).load(iNeighbour.getAvatarUrl()).fitCenter().into(imageProfile);
        neigName.setText(iNeighbour.getName());

        //CardView 1
        neigName2.setText(iNeighbour.getName());
        nameCity.setText(iNeighbour.getAddress());
        numberPhone.setText(iNeighbour.getPhoneNumber());
        fbURL.setText(iNeighbour.getFbURL() + iNeighbour.getName());

        //CardView 2
        tAbout.setText(iNeighbour.getAboutMe());

        //FloatingActionButton
       if (iNeighbour.isFavorite()) {
            floatBtn.setImageResource(R.drawable.ic_active_favorite_24);
       }
       else {
            floatBtn.setImageResource(R.drawable.ic_disabled_favorite_24);
       }

        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!iNeighbour.isFavorite()){
                    floatBtn.setImageResource(R.drawable.ic_active_favorite_24);
                }
                else {
                    floatBtn.setImageResource(R.drawable.ic_disabled_favorite_24);
                }

                iNeighbour.setFavorite(!iNeighbour.isFavorite());
                nApiService.createFavoriteNeighbour(iNeighbour);
            }
        });
    }
}