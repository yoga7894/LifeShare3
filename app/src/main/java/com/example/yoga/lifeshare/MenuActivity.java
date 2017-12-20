package com.example.yoga.lifeshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener,DialogFragment1.Communicator,DialogFragment2.Placer,DialogFragment22.Placer2,DialogFragment11.Communicator1,FragD.Blogger{

    public String name,email;
    public Bundle bundle=new Bundle();
    Fragment fragment=null;

    private ResideMenu resideMenu;
    private MenuActivity mContext;
    private ResideMenuItem Br;
    private ResideMenuItem Rf;
    private ResideMenuItem Bg;
    private ResideMenuItem Ub;
    private ResideMenuItem Up;
    private ResideMenuItem Mp;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        name=getIntent().getExtras().getString("name");
        email=getIntent().getExtras().getString("email");
        bundle.putString("name",name);
        bundle.putString("email",email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new FragA());
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);

        resideMenu.setBackground(R.color.back);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        Br    = new ResideMenuItem(this, R.drawable.redw,     "Blood Requests");
        Rf  = new ResideMenuItem(this, R.drawable.reqww,  "Request for Blood");
        Bg = new ResideMenuItem(this, R.drawable.blogw, "Blog");
        Ub = new ResideMenuItem(this, R.drawable.blofew, "Upload to Blog");
        Up = new ResideMenuItem(this, R.drawable.prow, "User Profiles");
      //  Mp = new ResideMenuItem(this, R.drawable.maprod, "NearBy Users");



        Br.setOnClickListener(this);
        Rf.setOnClickListener(this);
        Bg.setOnClickListener(this);
        Ub.setOnClickListener(this);
        Up.setOnClickListener(this);
     //   Mp.setOnClickListener(this);

        resideMenu.addMenuItem(Br, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Rf, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Up, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(Bg, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(Ub, ResideMenu.DIRECTION_RIGHT);
       // resideMenu.addMenuItem(Mp, ResideMenu.DIRECTION_RIGHT);


        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
//        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == Br){
            changeFragment(new FragA());
        }else if (view == Rf){
            changeFragment(new FragB());
        }else if (view == Bg){
            changeFragment(new FragC());
        }else if (view == Ub){
            changeFragment(new FragD());
        }else if (view==Up){
            changeFragment(new FragE());
//        }else if (view==Mp){
//            changeFragment(new FragF());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        targetFragment.setArguments(bundle);
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
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
            resideMenu.clearIgnoredViewList();
            fragmentTransaction.replace(R.id.main_fragment,fragment);
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
            resideMenu.clearIgnoredViewList();
            fragmentTransaction.replace(R.id.main_fragment,fragment);
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
            resideMenu.clearIgnoredViewList();
            fragmentTransaction.replace(R.id.main_fragment,fragment);
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
            resideMenu.clearIgnoredViewList();
            fragmentTransaction.replace(R.id.main_fragment,fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void Blogee() {
        changeFragment(new FragC());
    }
}
