package com.example.rental_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.rental_app.fragments.AccountFragment;
import com.example.rental_app.fragments.ChatFragment;
import com.example.rental_app.fragments.FavouriteFragment;
import com.example.rental_app.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        loadFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()){

            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_fav:
                fragment = new FavouriteFragment();
                break;
            case R.id.menu_chat:
                fragment = new ChatFragment();
                break;
            case R.id.menu_account:
                fragment = new AccountFragment();
                break;
        }
        return loadFragment(fragment);
    }

    boolean loadFragment(Fragment fragment){

        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;

    }
}