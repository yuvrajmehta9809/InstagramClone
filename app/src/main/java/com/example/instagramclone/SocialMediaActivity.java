package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
// IMPLEMENT TABLISTENER TO CHANGE TABS BY CLICKING ON THE TABS.
public class SocialMediaActivity extends AppCompatActivity implements ActionBar.TabListener{
    //DECLARING VARIABLES

TabsAdapter tabsAdapter;
ViewPager viewPager;
ActionBar actionBar;
ActionBar.Tab aTab;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        setTitle("Social Media App");
        //INITIALISING UI COMPONENTS.

        viewPager = findViewById(R.id.viewpager);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);

        // SET THE NAMES OF THE TABS.

        for (String names : TabsAdapter.getTabNames()){

            aTab = actionBar.newTab().setText(names).setTabListener(this);
            actionBar.addTab(aTab);

            //CALL ON PAGE CHANGE LISTENER ON THE VIEWPAGER.

        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //SE NAVIGATION TO SELECTED ITEM ON PAGE CHANGE.

                actionBar.setSelectedNavigationItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });





    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_settings:
                Toast.makeText(SocialMediaActivity.this,"hiiii",Toast.LENGTH_LONG).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        // TO OPEN A TAB WHEN USER CLICK A PARTICULAR TAB.

         viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
