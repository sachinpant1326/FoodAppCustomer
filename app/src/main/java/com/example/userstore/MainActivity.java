package com.example.userstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    FirebaseAuth fbauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbauth=FirebaseAuth.getInstance();
        getAuthorised();

        BottomNavigationView bnav=findViewById(R.id.home_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.sp_main_frag,new Menu()).commit();
        bnav.setOnNavigationItemSelectedListener(nlistener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener nlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                public boolean onNavigationItemSelected(MenuItem item)
                {
                    Fragment fb=null;
                    switch (item.getItemId())
                    {
                        case R.id.nav_menu:
                            fb=new Menu();
                            break;
                        case R.id.nav_order:
                            fb=new Cart();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.sp_main_frag,fb).commit();
                    return true;
                }
            };


    public void getAuthorised()
    {
        fbauth.signInWithEmailAndPassword("sachinpant1326@gmail.com","hacker")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                    }
                });
    }
}