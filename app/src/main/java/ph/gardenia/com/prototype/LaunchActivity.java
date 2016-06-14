package ph.gardenia.com.prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.orm.SugarContext;

import ph.gardenia.com.fragment.SplashFragment;


/**
 * Created by otomatik on 6/3/16.
 */
public class LaunchActivity extends FragmentActivity {

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Fragment fragment = null;
        fragment = new SplashFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.launch, fragment);
            fragmentTransaction.commit();
        }

    }

}
