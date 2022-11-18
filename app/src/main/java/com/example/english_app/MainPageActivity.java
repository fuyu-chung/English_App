package com.example.english_app;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //number of selectedTab. 我們有五個頁面所以 values 介於1-5之簡，default value = 1
    private int selectedTab = 1;

    ImageView menuIcon;

    //Drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
//    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        /*------------------------------- menu hooks -------------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout); //whole screen of main page
        navigationView = findViewById(R.id.nav_view); //the drawer
        menuIcon = findViewById(R.id.menu_icon); //the menu icon
//        toolbar = findViewById(R.id.toolbar); //upperBar = toolbar

//        /*--------------------------TOOL BAR (UP)---------------------------*/
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.menu_icon);


        navigationDrawer();



        /*------------------------------BOTTOM BAR!!!!!-------------------------------------*/
        //全部一起宣告因為立刻會用到
        final LinearLayout collegeLayout = findViewById(R.id.collegeLayout);
        final LinearLayout loungeLayout = findViewById(R.id.loungeLayout);
        final LinearLayout dormLayout = findViewById(R.id.dormLayout);
        final LinearLayout studyCircleLayout = findViewById(R.id.studyCircleLayout);
        final LinearLayout libraryLayout = findViewById(R.id.libraryLayout);

        final ImageView collegeImage = findViewById(R.id.collegeImage);
        final ImageView loungeImage = findViewById(R.id.loungeImage);
        final ImageView dormImage = findViewById(R.id.dormImage);
        final ImageView studyCircleImage = findViewById(R.id.studyCircleImage);
        final ImageView libraryImage = findViewById(R.id.libraryImage);

        final TextView collegeText = findViewById(R.id.collegeText);
        final TextView loungeText = findViewById(R.id.loungeText);
        final TextView dormText = findViewById(R.id.dormText);
        final TextView studyCircleText = findViewById(R.id.studyCircleText);
        final TextView libraryText = findViewById(R.id.libraryText);

        //set college fragment by default
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, CollegeFragment.class, null)
                .commit();

        collegeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if college is selected
                if (selectedTab != 1) {

                    //set college fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, CollegeFragment.class, null)
                            .commit();

                    //unselect other tabs except college tab, here is text that we don't want to display
                    loungeText.setVisibility(View.GONE);
                    dormText.setVisibility(View.GONE);
                    studyCircleText.setVisibility(View.GONE);
                    libraryText.setVisibility(View.GONE);

                    //icon before clicked will display
                    loungeImage.setImageResource(R.drawable.lounge);
                    dormImage.setImageResource(R.drawable.dorm);
                    studyCircleImage.setImageResource(R.drawable.study_circle);
                    libraryImage.setImageResource(R.drawable.library);


                    //layout transparent
                    loungeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    dormLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    studyCircleLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    libraryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //college visibility
                    collegeText.setVisibility(View.VISIBLE);
                    collegeImage.setImageResource(R.drawable.college_clicks);
                    collegeLayout.setBackgroundResource(R.drawable.round_back_college);

                    //animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    collegeLayout.startAnimation(scaleAnimation);

                    //set 1st tab as selected tab
                    selectedTab = 1;
                }

            }
        });

        loungeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if lounge is selected
                if (selectedTab != 2) {

                    //set lounge fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, LoungeFragment.class, null)
                            .commit();

                    //unselect other tabs except lounge tab, here is text that we don't want to display
                    collegeText.setVisibility(View.GONE);
                    dormText.setVisibility(View.GONE);
                    studyCircleText.setVisibility(View.GONE);
                    libraryText.setVisibility(View.GONE);

                    //icon before clicked will display
                    collegeImage.setImageResource(R.drawable.college);
                    dormImage.setImageResource(R.drawable.dorm);
                    studyCircleImage.setImageResource(R.drawable.study_circle);
                    libraryImage.setImageResource(R.drawable.library);

                    //layout transparent
                    collegeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    dormLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    studyCircleLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    libraryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //lounge visibility
                    loungeText.setVisibility(View.VISIBLE);
                    loungeImage.setImageResource(R.drawable.lounge_clicks);
                    loungeLayout.setBackgroundResource(R.drawable.round_back_lounge);

                    //animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    loungeLayout.startAnimation(scaleAnimation);

                    //set 2nd tab as selected tab
                    selectedTab = 2;
                }

            }
        });

        dormLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if dorm is selected
                if (selectedTab != 3) {

                    //set dorm fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, DormFragment.class, null)
                            .commit();

                    //unselect other tabs except dorm tab, here is text that we don't want to display
                    collegeText.setVisibility(View.GONE);
                    loungeText.setVisibility(View.GONE);
                    studyCircleText.setVisibility(View.GONE);
                    libraryText.setVisibility(View.GONE);

                    //icon before clicked will display
                    collegeImage.setImageResource(R.drawable.college);
                    loungeImage.setImageResource(R.drawable.lounge);
                    studyCircleImage.setImageResource(R.drawable.study_circle);
                    libraryImage.setImageResource(R.drawable.library);

                    //layout transparent
                    collegeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    loungeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    studyCircleLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    libraryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //dorm visibility
                    dormText.setVisibility(View.VISIBLE);
                    dormImage.setImageResource(R.drawable.dorm_clicks);
                    dormLayout.setBackgroundResource(R.drawable.round_back_dorm);

                    //animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    dormLayout.startAnimation(scaleAnimation);

                    //set 3rd tab as selected tab
                    selectedTab = 3;

                }
            }
        });

        studyCircleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if study circle is selected
                if (selectedTab != 4) {

                    //set study circle fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, StudyCircleFragment.class, null)
                            .commit();

                    //unselect other tabs except study circle tab, here is text that we don't want to display
                    collegeText.setVisibility(View.GONE);
                    loungeText.setVisibility(View.GONE);
                    dormText.setVisibility(View.GONE);
                    libraryText.setVisibility(View.GONE);

                    //icon before clicked will display
                    collegeImage.setImageResource(R.drawable.college);
                    loungeImage.setImageResource(R.drawable.lounge);
                    dormImage.setImageResource(R.drawable.dorm);
                    libraryImage.setImageResource(R.drawable.library);

                    //layout transparent
                    collegeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    loungeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    dormLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    libraryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //study circle visibility
                    studyCircleText.setVisibility(View.VISIBLE);
                    studyCircleImage.setImageResource(R.drawable.study_circle_clicks);
                    studyCircleLayout.setBackgroundResource(R.drawable.round_back_study_circle);

                    //animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    dormLayout.startAnimation(scaleAnimation);

                    //set 4th tab as selected tab
                    selectedTab = 4;

                }

            }
        });

        libraryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if library is selected
                if (selectedTab != 5) {

                    //set library fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, LibraryFragment.class, null)
                            .commit();

                    //unselect other tabs except library tab, here is text that we don't want to display
                    collegeText.setVisibility(View.GONE);
                    loungeText.setVisibility(View.GONE);
                    dormText.setVisibility(View.GONE);
                    studyCircleText.setVisibility(View.GONE);

                    //icon before clicked will display
                    collegeImage.setImageResource(R.drawable.college);
                    loungeImage.setImageResource(R.drawable.lounge);
                    dormImage.setImageResource(R.drawable.dorm);
                    studyCircleImage.setImageResource(R.drawable.study_circle);

                    //layout transparent
                    collegeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    loungeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    dormLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    studyCircleLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //library visibility
                    libraryText.setVisibility(View.VISIBLE);
                    libraryImage.setImageResource(R.drawable.library_clicks);
                    libraryLayout.setBackgroundResource(R.drawable.round_back_library);

                    //animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    libraryLayout.startAnimation(scaleAnimation);

                    //set 5th tab as selected tab
                    selectedTab = 5;


                }
            }
        });


    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home); //home item is default

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    /*-------------------------To avoid closing the app on Back pressed---------------------------*/
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

    }

    /*----------------------------       Set navigation item       -------------------------------*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}