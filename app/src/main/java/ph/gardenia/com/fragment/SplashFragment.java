package ph.gardenia.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ph.gardenia.com.helper.RouteHelper;
import ph.gardenia.com.helper.UserHelper;
import ph.gardenia.com.prototype.MainActivity;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.utils.Fragments;

/**
 * Created by otomatik on 6/10/16.
 */
public class SplashFragment extends Fragment {

    private String TAG = getClass().getSimpleName();
    private int SPLASH_SCREEN_TIME_OUT = 5000;

    private TextView txtAppName;
    private List<RouteHelper> routeHelpers;
    private List<UserHelper> userHelpers;

    private Fragments fragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ONCREATE");

        /*Pass all data gathered from the database to list*/
        routeHelpers = RouteHelper.listAll(RouteHelper.class);
        userHelpers = UserHelper.listAll(UserHelper.class);

        fragments = new Fragments(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        txtAppName = (TextView) view.findViewById(R.id.txtAppName);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /*Check if it has route and user and direct it
                * to the accountable fragment if has not set else
                * to the Main Activity*/

                Fragment fragment = null;

                if (routeHelpers.isEmpty()) {
                    Log.d(TAG, "HAS NO ROUTE SETUP");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
//                    fragment = new RouteFragment();
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.launch, fragment);
//                    fragmentTransaction.commit();
                } else if (userHelpers.isEmpty()) {
                    Log.d(TAG, "HAS NO USER SETUP");
                    fragment = new RouteFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.launch, fragment);
                    fragmentTransaction.commit();
                } else {

                }


            }
        }, SPLASH_SCREEN_TIME_OUT);

        return view;
    }

}
