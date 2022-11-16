package com.example.english_app;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainPageActivity extends AppCompatActivity {

    //number of selectedTab. 我們有五個頁面所以 values 介於1-5之簡，default value = 1
    private int selectedTab = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //記得這裡的Toolbar 使用的是androidx.appcompat.widget.Toolbar，不可以選錯宣告(by P)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_menu);


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


        collegeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if college is selected
                if(selectedTab != 1){

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
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f,1f, Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF,0.0f);
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
                if(selectedTab!= 2){

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
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f,1f, Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    loungeLayout.startAnimation(scaleAnimation);

                    //set 1st tab as selected tab
                    selectedTab = 2;
                }

            }
        });

        dormLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if dorm is selected
                if(selectedTab!= 3){

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
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f,1f, Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    dormLayout.startAnimation(scaleAnimation);

                    //set 1st tab as selected tab
                    selectedTab = 3;

                }
            }
        });

        studyCircleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if study circle is selected
                if(selectedTab!= 4){

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
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f,1f, Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    dormLayout.startAnimation(scaleAnimation);

                    //set 1st tab as selected tab
                    selectedTab = 4;

                }

            }
        });

        libraryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if library is selected
                if(selectedTab!= 5){

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
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f,1f, Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    libraryLayout.startAnimation(scaleAnimation);

                    //set 1st tab as selected tab
                    selectedTab = 5;


                }
            }
        });


    }


}