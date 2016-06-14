package ph.gardenia.com.utils;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ph.gardenia.com.prototype.R;

/**
 * Created by otomatik on 6/10/16.
 */
public class Fragments extends FragmentActivity{

    private Context context;

    public Fragments(Context context){
        this.context = context;
    }

    public void replaceFragment(Fragment fragment, int container){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.commit();
    }

}
