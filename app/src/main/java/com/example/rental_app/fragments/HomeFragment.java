package com.example.rental_app.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rental_app.DetailsActivity;
import com.example.rental_app.R;
import com.example.rental_app.adapters.HomeAdapter;
import com.example.rental_app.listeners.ItemListener;
import com.example.rental_app.model.Item;
import com.example.rental_app.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements ItemListener {

    private RecyclerView topDealRV;
    private HomeAdapter adapter;
    private List<Item> itemList;
    private CircleImageView profileImage;
    private TextView username;
    private DatabaseReference ref;
    private ImageView homess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topDealRV = view.findViewById(R.id.top_deal_RV);
        profileImage = view.findViewById(R.id.profile_image);
        username = view.findViewById(R.id.user_name);
        homess = view.findViewById(R.id.home_image);

        homess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                User user = snapshot.getValue(User.class);
                if (user != null){
                    username.setText(user.getName());
                    Glide.with(getContext())
                            .load(user.getImage())
                            .centerCrop()
                            .placeholder(R.drawable.ic_account)
                            .into(profileImage);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        itemList = new ArrayList<>();
        itemList.add(new Item("Street # 1, Ethiopia", "$500", "1 Bedroom"));
        itemList.add(new Item("Street # 2, Ethiopia", "$500", "1 Bedroom"));
        itemList.add(new Item("Street # 3, Ethiopia", "$500", "1 Bedroom"));
        itemList.add(new Item("Street # 4, Ethiopia", "$500", "1 Bedroom"));
        FirebaseDatabase.getInstance().getReference().child("images")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                            itemList.add(new Item(
                                    Objects.requireNonNull(dataSnapshot.child("location").getValue()).toString(),
                                    Objects.requireNonNull(dataSnapshot.child("price").getValue()).toString(),
                                    Objects.requireNonNull(dataSnapshot.child("description").getValue()).toString(),
                                    Objects.requireNonNull(dataSnapshot.child("shortDescription").getValue()).toString(),
                                    Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString()

                            ));
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        adapter = new HomeAdapter(getContext(), itemList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topDealRV.setLayoutManager(linearLayoutManager);
        topDealRV.setAdapter(adapter);

    }

    @Override
    public void OnItemPosition(int position) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);

        intent.putExtra("price", itemList.get(position).getPrice());
        intent.putExtra("location", itemList.get(position).getLocation());
        intent.putExtra("description", itemList.get(position).getDescription());
        intent.putExtra("shortDescription", itemList.get(position).getShortDescription());
        intent.putExtra("image", itemList.get(position).getImage());

        startActivity(intent);
    }
}