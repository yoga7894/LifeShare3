package com.example.yoga.lifeshare;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Main extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,DialogFragment1.Communicator,DialogFragment2.Placer,DialogFragment22.Placer2,DialogFragment11.Communicator1,FragD.Blogger{

    TextView na,em;
   public String name,email;
    public Bundle bundle=new Bundle();
    Fragment fragment=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=getIntent().getExtras().getString("name");
         email=getIntent().getExtras().getString("email");
        Log.v("Hey","HII"+name+email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.setDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View HeaderView=navigationView.getHeaderView(0);

                na= (TextView) HeaderView.findViewById(R.id.name);
                em= (TextView) HeaderView.findViewById(R.id.email1);
                 na.setText(name);
                 em.setText(email);
              navigationView.setNavigationItemSelectedListener(this);


               bundle.putString("name",name);
               bundle.putString("email",email);
             displaySelectedScreen(R.id.nav_menu1);




    }

            @Override
            public void onBackPressed() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }
            }





            private void displaySelectedScreen(int position)
            {

                switch (position)
                {
                    case R.id.nav_menu1:
                        fragment=new FragA();
                        fragment.setArguments(bundle);

                        break;
                    case R.id.nav_menu2:
                        fragment=new FragB();
                        fragment.setArguments(bundle);
                        break;

                    case R.id.nav_menu3:
                        fragment=new FragC();
                        fragment.setArguments(bundle);
                        break;

                    case R.id.nav_menu4:
                        fragment=new FragD();
                        break;
                    case R.id.nav_menu5:
                        fragment=new FragE();
                        fragment.setArguments(bundle);
                        break;
                }
                if (fragment!=null)
                {
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main,fragment);
                    fragmentTransaction.commit();
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                displaySelectedScreen(id);


                return true;
            }


    @Override
    public void respond(String k) {
        String choice=k;
        if (k.equals("All"))
        {
            fragment=new FragA();
            fragment.setArguments(bundle);
        }
        else
        {
            bundle.putString("choice",choice);
            fragment=new FragA1();
            fragment.setArguments(bundle);
        }

        if (fragment!=null)
        {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void Placerr(String k) {
        String choice=k;
        if (k.equals("All"))
        {
            fragment=new FragA();
            fragment.setArguments(bundle);
        }
        else
        {
            bundle.putString("choice",choice);
            fragment=new FragA11();
            fragment.setArguments(bundle);
        }

        if (fragment!=null)
        {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void respond1(String k) {
        String choice=k;
        if (k.equals("All"))
        {
            fragment=new FragE();
            fragment.setArguments(bundle);
        }
        else
        {
            bundle.putString("choice",choice);
            fragment=new FragE1();
            fragment.setArguments(bundle);
        }

        if (fragment!=null)
        {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        }
    }



    @Override
    public void Placerr2(String k) {

        String choice=k;
        if (k.equals("All"))
        {
            fragment=new FragE();
            fragment.setArguments(bundle);
        }
        else
        {
            bundle.putString("choice",choice);
            fragment=new FragE11();
            fragment.setArguments(bundle);
        }

        if (fragment!=null)
        {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void Blogee() {
        displaySelectedScreen(R.id.nav_menu3);
    }
}



