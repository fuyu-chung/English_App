package com.example.english_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.english_app.colleges.competition.quiz.VocabQuizActivity;
import com.example.english_app.mainpage_fragments.CollegeFragment;
import com.example.english_app.mainpage_fragments.DormFragment;
import com.example.english_app.mainpage_fragments.Library.LibraryFragment;
import com.example.english_app.mainpage_fragments.lounge.LoungeFragment;
import com.example.english_app.user_basic.ChangeUserProfileActivity;
import com.example.english_app.user_basic.LoginActivity;
import com.example.english_app.user_dorm.UserProfileActivity;
import com.google.android.material.navigation.NavigationView;

public class MainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables for animation
    static final float END_SCALE = 0.7f;


    //number of selectedTab. 我們有五個頁面所以 values 介於1-5之簡，default value = 1
    private int selectedTab = 1;

    ImageView menuIcon;
    RelativeLayout contentView;

    //Drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

   //initial headerView and user_name byP
    View headerView;
    TextView navUserTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        /*------------------------------- menu hooks -------------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout); //whole screen of main page
        navigationView = findViewById(R.id.nav_view); //the drawer
        menuIcon = findViewById(R.id.menu_icon); //the menu icon
        contentView = findViewById(R.id.contentView); //the contentView (relative)

        /*---------------------- CALL CURRENT USERNAME -------------------------------*/
        //set into navigation View header byP
        headerView = navigationView.getHeaderView(0);
        ImageView image = headerView.findViewById(R.id.user_photo);
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int imageNum = sharedPreferences.getInt("image", 0);
        if (imageNum == 1){
            image.setImageResource(R.drawable.pic_penguin);
        }
        if (imageNum == 2){
            image.setImageResource(R.drawable.pic_dino);
        }
        if (imageNum == 3){
            image.setImageResource(R.drawable.pic_duck);
        }
        if (imageNum == 4){
            image.setImageResource(R.drawable.pic_fox);
        }
        if (imageNum == 5){
            image.setImageResource(R.drawable.pic_hedge);
        }


        navUserTextView = headerView.findViewById(R.id.user_name);


        //call readDataFromSharedPreferences function
        readDataFromSharedPreferences();

        navigationDrawer();

        /*------------------------------BOTTOM BAR!!!!!-------------------------------------*/
        //全部一起宣告因為立刻會用到
        final LinearLayout collegeLayout = findViewById(R.id.collegeLayout);
        final LinearLayout loungeLayout = findViewById(R.id.loungeLayout);
        final LinearLayout dormLayout = findViewById(R.id.dormLayout);
//        final LinearLayout studyCircleLayout = findViewById(R.id.studyCircleLayout);
        final LinearLayout libraryLayout = findViewById(R.id.libraryLayout);

        final ImageView collegeImage = findViewById(R.id.collegeImage);
        final ImageView loungeImage = findViewById(R.id.loungeImage);
        final ImageView dormImage = findViewById(R.id.dormImage);
//        final ImageView studyCircleImage = findViewById(R.id.studyCircleImage);
        final ImageView libraryImage = findViewById(R.id.libraryImage);

        final TextView collegeText = findViewById(R.id.collegeText);
        final TextView loungeText = findViewById(R.id.loungeText);
        final TextView dormText = findViewById(R.id.dormText);
//        final TextView studyCircleText = findViewById(R.id.studyCircleText);
        final TextView libraryText = findViewById(R.id.libraryText);

        //set college fragment by default
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, CollegeFragment.class, null)
                .commit();

        collegeLayout.setOnClickListener(v -> {

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
//                studyCircleText.setVisibility(View.GONE);
                libraryText.setVisibility(View.GONE);

                //icon before clicked will display
                loungeImage.setImageResource(R.drawable.lounge);
                dormImage.setImageResource(R.drawable.dorm);
//                studyCircleImage.setImageResource(R.drawable.study_circle);
                libraryImage.setImageResource(R.drawable.library);


                //layout transparent
                loungeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                dormLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                studyCircleLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
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

        });

        loungeLayout.setOnClickListener(v -> {

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
//                studyCircleText.setVisibility(View.GONE);
                libraryText.setVisibility(View.GONE);

                //icon before clicked will display
                collegeImage.setImageResource(R.drawable.college);
                dormImage.setImageResource(R.drawable.dorm);
//                studyCircleImage.setImageResource(R.drawable.study_circle);
                libraryImage.setImageResource(R.drawable.library);

                //layout transparent
                collegeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                dormLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                studyCircleLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
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

        });

        dormLayout.setOnClickListener(v -> {

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
//                studyCircleText.setVisibility(View.GONE);
                libraryText.setVisibility(View.GONE);

                //icon before clicked will display
                collegeImage.setImageResource(R.drawable.college);
                loungeImage.setImageResource(R.drawable.lounge);
//                studyCircleImage.setImageResource(R.drawable.study_circle);
                libraryImage.setImageResource(R.drawable.library);

                //layout transparent
                collegeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                loungeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                studyCircleLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
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
        });

//        studyCircleLayout.setOnClickListener(v -> {
//
//            //check if study circle is selected
//            if (selectedTab != 4) {
//
//                //set study circle fragment
//                getSupportFragmentManager().beginTransaction()
//                        .setReorderingAllowed(true)
//                        .replace(R.id.fragmentContainer, StudyCircleFragment.class, null)
//                        .commit();
//
//                //unselect other tabs except study circle tab, here is text that we don't want to display
//                collegeText.setVisibility(View.GONE);
//                loungeText.setVisibility(View.GONE);
//                dormText.setVisibility(View.GONE);
//                libraryText.setVisibility(View.GONE);
//
//                //icon before clicked will display
//                collegeImage.setImageResource(R.drawable.college);
//                loungeImage.setImageResource(R.drawable.lounge);
//                dormImage.setImageResource(R.drawable.dorm);
//                libraryImage.setImageResource(R.drawable.library);
//
//                //layout transparent
//                collegeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                loungeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                dormLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                libraryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//
//                //study circle visibility
//                studyCircleText.setVisibility(View.VISIBLE);
//                studyCircleImage.setImageResource(R.drawable.study_circle_clicks);
//                studyCircleLayout.setBackgroundResource(R.drawable.round_back_study_circle);
//
//                //animation
//                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//                scaleAnimation.setDuration(200);
//                scaleAnimation.setFillAfter(true);
//                dormLayout.startAnimation(scaleAnimation);
//
//                //set 4th tab as selected tab
//                selectedTab = 4;
//
//            }
//
//        });

        libraryLayout.setOnClickListener(v -> {

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
//                studyCircleText.setVisibility(View.GONE);

                //icon before clicked will display
                collegeImage.setImageResource(R.drawable.college);
                loungeImage.setImageResource(R.drawable.lounge);
                dormImage.setImageResource(R.drawable.dorm);
//                studyCircleImage.setImageResource(R.drawable.study_circle);

                //layout transparent
                collegeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                loungeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                dormLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                studyCircleLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

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
        });


    }

    //get user_name from loginActivity byP
    private void readDataFromSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String name = sharedPreferences.getString("user_name","");
        navUserTextView.setText(name);

    }


    //navigation drawer functions
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home); //home item is default

        menuIcon.setOnClickListener(v -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.icon_bg));
        //function form coding with Tea!!!!!!
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }



    /*----------To avoid closing the app on Back pressed------------*/
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

    }

    /*------- Set navigation items ------------*/
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break; //because we are already on the home page
            case R.id.nav_profile:
                Intent intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_passwordChange:
                Intent intent2 = new Intent(this, ChangeUserProfileActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3 = new Intent(this, LoginActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_aboutus:
                Intent intent4 = new Intent(this,AboutUsActivity.class);
                startActivity(intent4);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START); //if any item selected, close drawer
        return true;
    }


}