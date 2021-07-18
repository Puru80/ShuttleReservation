package com.mainpage.shuttlereservation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;

    public void bookTickets(View view) {
        Intent i = new Intent(Home.this, Booking.class);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            ShuttleResApplication.getInstance().getAppBeanFactory().getUserManager().
                    signOut(ShuttleResApplication.getInstance().getAppBeanFactory().getUser().userEmail,
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
                            });
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_profile, R.id.nav_share, R.id.nav_tickets)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Todo: Fix this
        View view = navigationView.inflateHeaderView(R.layout.nav_header_home);
        TextView txt = view.findViewById(R.id.text_Name);
        User user = ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUser();
        txt.setText("Puru Agarwal");
    }

    @Override
    protected void onResume() {
        User user = ShuttleResApplication.getInstance().getAppBeanFactory().getDataManager().getUser();
        super.onResume();
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
