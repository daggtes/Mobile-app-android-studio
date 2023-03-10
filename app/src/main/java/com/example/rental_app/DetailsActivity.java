package com.example.rental_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rental_app.model.Update;
import com.example.rental_app.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView price,shortDescription,description, location;
    String pri, des, shdes, img, loc;
    private AppCompatButton updateButton;
    private DatabaseReference fileReference;
    //private CircleImageView profileImage;
    private TextView username;
    private EditText editt;
    private EditText days;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.imageView);
        editt = findViewById(R.id.edit);
        //profileImage = findViewById(R.id.profile_image);
        username = findViewById(R.id.user_name);
        location = findViewById(R.id.location);
        price= findViewById(R.id.price);
        shortDescription = findViewById(R.id.short_description);
        description = findViewById(R.id.description);
        updateButton = findViewById(R.id.UU_button);
        days = findViewById(R.id.editt);



        fileReference = FirebaseDatabase.getInstance().getReference().child("apply");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertFileupdate();
            }

        });




        pri = getIntent().getStringExtra("price");
        des = getIntent().getStringExtra("description");
        img = getIntent().getStringExtra("image");
        shdes = getIntent().getStringExtra("shortDescription");
        loc = getIntent().getStringExtra("location");

        price.setText("$"+pri);
        description.setText(des);
        shortDescription.setText(shdes);
        location.setText(loc);
        Glide.with(this)
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.ic_account)
                .into(imageView);
    }

    private void insertFileupdate() {

        String name = editt.getText().toString();
        String day = days.getText().toString();
        Upddate upddate = new Upddate(name, day);

        fileReference.push().setValue(upddate);
        Toast.makeText(DetailsActivity.this, "Applied", Toast.LENGTH_SHORT).show();


    }


}