package ph.gardenia.com.prototype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ph.gardenia.com.fragment.DscFragment;
import ph.gardenia.com.fragment.EmptyDscFragment;

/**
 * Created by otomatik on 6/20/16.
 */
public class EmptyDscActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          /*Set Transition in activity*/
        overridePendingTransition(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_exit);

        setContentView(R.layout.activity_empty_dsc);

        Fragment fragment = new Fragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment = new EmptyDscFragment();
            fragmentTransaction.replace(R.id.empty, fragment);
            fragmentTransaction.commit();
        }
    }
}
