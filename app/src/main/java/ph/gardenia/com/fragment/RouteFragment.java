package ph.gardenia.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ph.gardenia.com.callback.VolleyCallback;
import ph.gardenia.com.helper.RouteHelper;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.request.PullRequest;
import ph.gardenia.com.utils.Constant;

/**
 * Created by otomatik on 6/10/16.
 */
public class RouteFragment extends Fragment implements View.OnClickListener {

    private EditText txtRoutecode;
    private Button btnConfirm;

    private String TAG = getClass().getSimpleName();
    private PullRequest pullRequest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ONCREATE");
        pullRequest = new PullRequest(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);

        txtRoutecode = (EditText) view.findViewById(R.id.txtRouteCode);
        btnConfirm = (Button) view.findViewById(R.id.btnConfirm);

        /*Set Onclicklistener for this button*/
        btnConfirm.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnConfirm:

                /*Disable button to limit the request to one*/
                btnConfirm.setEnabled(false);

                Log.d(TAG, "ROUTE CODE " + txtRoutecode.getText().toString().toUpperCase());
                String routeCode = txtRoutecode.getText().toString().toUpperCase();
                pullRequest.getRoute(routeCode, new VolleyCallback() {
                    @Override
                    public void onSuccess(int result) {

                        /*Set the application depends on route*/

                        Fragment fragment = null;

                        switch (result) {

                            case Constant.IS_REGULAR_DOWNLINE:
                                fragment = new LoginFragment();
                                break;
                            case Constant.IS_FEEDER_DOWNLINE:
                                break;
                            case Constant.IS_PREINVOICE_DOWNLINE:
                                break;
                            case Constant.IS_FEEDER:
                                break;
                            case Constant.IS_BO:
                                break;
                        }

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                                R.anim.fragment_slide_right_exit, R.anim.fragment_slide_right_enter,
                                R.anim.fragment_slide_left_exit);
                        fragmentTransaction.replace(R.id.launch, fragment);
                        fragmentTransaction.commit();

                    }

                    @Override
                    public void onFailed(int result) {

                /*Enable button if the response failed*/
                        btnConfirm.setEnabled(true);

                        switch (result) {
                            case VolleyCallback.ON_RESPONSE_JSON_ERROR:
                                Toast.makeText(getActivity(), R.string.json_msg_failed, Toast.LENGTH_LONG).show();
                                break;
                            case VolleyCallback.ON_RESPONSE_FAILED:
                                Toast.makeText(getActivity(), R.string.json_msg_failed, Toast.LENGTH_LONG).show();
                                break;
                        }

                    }

                    @Override
                    public void onStringResponse(String result) {

                    }
                });


                break;
        }

    }

}
