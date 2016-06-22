package ph.gardenia.com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ph.gardenia.com.callback.VolleyCallback;
import ph.gardenia.com.helper.DownlineDscHelper;
import ph.gardenia.com.helper.DownlineDscItemHelper;
import ph.gardenia.com.helper.UserHelper;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.request.PullRequest;
import ph.gardenia.com.utils.Constant;

/**
 * Created by otomatik on 5/30/16.
 */
public class DscFragment extends Fragment {

    private String TAG = getClass().getSimpleName();

    private PullRequest pullRequest;

    private List<UserHelper> userHelpers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ONCREATE");

        /*set toolbar in this fragment*/
        setHasOptionsMenu(true);

        /*Instantiate PullRequest Object*/
        pullRequest = new PullRequest(getActivity());

        /*get data of user and pass it on the list*/
        userHelpers = UserHelper.listAll(UserHelper.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dsc, container, false);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.send:

                Log.d(TAG, "GET DSC");

//                pullRequest.getDsc(userHelpers.get(Constant.BASE_COLUMN).getEmpId(), new VolleyCallback() {
//                    @Override
//                    public void onSuccess(int result) {
//
//                    }
//
//                    @Override
//                    public void onFailed(int result) {
//
//                    }
//
//                    @Override
//                    public void onStringResponse(String result) {
//
//                    }
//                });

                List<DownlineDscHelper> dscHelpers = DownlineDscHelper.listAll(DownlineDscHelper.class);

                DownlineDscHelper dscHelper = new DownlineDscHelper();

                Log.d(TAG, "WTF " + dscHelpers.get(Constant.BASE_COLUMN).getDscNo());


                List<DownlineDscItemHelper> downlineDscItemHelpers = DownlineDscItemHelper.listAll(DownlineDscItemHelper.class);

                for (DownlineDscItemHelper dscItemHelper : downlineDscItemHelpers){
                    Log.d(TAG, "PRODUCT " + dscItemHelper.getProductCode());
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
