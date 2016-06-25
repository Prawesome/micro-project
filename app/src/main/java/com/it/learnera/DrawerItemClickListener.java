package com.it.learnera;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Prejith on 16-Apr-16.
 */

public class DrawerItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
    }

    private void selectItem(int position) {
        FragmentActivity test = new FragmentActivity();
        AnnouncementFragment mFragment = null;
        Log.d("DrawerItemClickListener", "Fragment made null");
        switch(position) {
            case 0:
                mFragment = new AnnouncementFragment();
                Bundle args = new Bundle();
                args.putInt(null, position);
                mFragment.setArguments(args);
                Log.d("DrawerItemClickListener", "Fragment initialised to announcement");
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

        if(mFragment != null) {
            Log.d("DrawerItemClickListener","Before isFinishing()");
        //    if(getActivity() == null || getActivity().isFinishing()) {
                FragmentTransaction transaction = test.getSupportFragmentManager().beginTransaction();
                //getsupport not found even with import of fragactivity
                Log.d("DrawerItemClickListener", "Fragment before commit");
                transaction.replace(R.id.content_frame, mFragment);
                Log.d("DrawerItemClickListener", "Fragment before backstack");
                transaction.addToBackStack(null);
                Log.d("DrawerItemClickListener", "Fragment at backstack");
                transaction.commit();
                Log.d("DrawerItemClickListener", "Fragment after commit");
        //    }
        }
        else
            Log.d("DrawerItemClickListener", "No fragment");
    }
}