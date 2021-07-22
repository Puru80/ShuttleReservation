package com.mainpage.shuttlereservation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.mainpage.shuttlereservation.domains.models.response.User;
import com.mainpage.shuttlereservation.network.VolleyResponseListener;
import com.mainpage.shuttlereservation.presentation.Booking;
import com.mainpage.shuttlereservation.presentation.LogIn;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private AppCompatButton btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupUI(0);
        listener();
    }

    //TODO: btnBooking not working after seeing tickets
    public void setupUI(int i){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> ShuttleResApplication.getInstance().getAppBeanFactory().getUserManager().
                signOut(ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUser().userEmail,
                        new VolleyResponseListener() {
                            @Override
                            public void onError(String message) {
                                Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSuccess(String message) {
                                SharedPreferences sharedPreferences = ShuttleResApplication.getCtx().getSharedPreferences("SharedPref",
                                        Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", "None");
                                editor.putString("password", "None");
                                editor.apply();

                                Toast.makeText(Home.this, message, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Home.this, LogIn.class);
                                startActivity(i);
                                finish();
                            }
                        }));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_profile, R.id.nav_share, R.id.nav_tickets)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if(i==0) {
            View view = navigationView.inflateHeaderView(R.layout.nav_header_home);
            TextView txtUserName = view.findViewById(R.id.text_Name);
            TextView txtEmail = view.findViewById(R.id.text_Email);
            User user = ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUser();

            String userName = user.getFirstName() + " " + user.getLastName();

            txtUserName.setText(userName);
            txtEmail.setText(user.getUserEmail());
        }

        btnBook = findViewById(R.id.buttonBook);
    }

    public void listener(){
        btnBook.setOnClickListener(view -> {
            Intent i = new Intent(Home.this, Booking.class);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupUI(1);
        listener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
