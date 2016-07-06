package com.it.learnera;

import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private String[] mDrawerArray;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;

    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState(); //Change navigation drawer toggle according to state

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //Navigation drawer implementation starts here
        mDrawerArray = getResources().getStringArray(R.array.navArray);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mTitle = mDrawerTitle = getApplicationInfo().loadLabel(getPackageManager());

        //initialise object of objectdraweritem for navigation drawer
        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];

        drawerItem[0] = new ObjectDrawerItem("Announcement");
        drawerItem[1] = new ObjectDrawerItem("Notes");
        drawerItem[2] = new ObjectDrawerItem("Assignments");

        //set navigation drawer adapter
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_navigation_item, drawerItem);
        mDrawerList.setAdapter(adapter);

        //set navigation drawer item click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //for navigation bar toggle
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                //Call overriden method as mTitle should be changed when nav drawer is closed
                setTitle(mTitle);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                //Call original method as mTitle should not be changed when nav drawer is opened. If changed, titles will be messed up on opening of nav drawer with no selection
                getSupportActionBar().setTitle(mDrawerTitle);

            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);  //enable default burger indicator
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true); //Nav drawer implementation end
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    //Item click listener implementation starts here
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position); //call select item and pass position as int
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AnnouncementFragment();
                break;
            case 1:
                fragment = new NotesFragment();
                break;
            case 2:
                fragment = new AssignmentFragment();
                break;
            case 3:
            //    fragment = new SettingsFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mDrawerArray[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("FRAGMENT_CALL", "Fragment cant be called");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
}