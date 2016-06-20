package ph.gardenia.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import ph.gardenia.com.prototype.MainActivity;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.request.PullRequest;
import ph.gardenia.com.utils.Constant;

/**
 * Created by otomatik on 6/10/16.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private String TAG = getClass().getSimpleName();

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;

    private PullRequest pullRequest;

    private List<RouteHelper> routeHelpers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ONCREATE");
        pullRequest = new PullRequest(getActivity());
        routeHelpers = RouteHelper.listAll(RouteHelper.class);
        Log.d(TAG, "ROUTE HELPER " + routeHelpers.get(Constant.BASE_COLUMN).getCodeNo());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtUsername = (EditText) view.findViewById(R.id.txtUsername);
        txtPassword = (EditText) view.findViewById(R.id.txtPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        /*Set Onclicklistener for this button*/
        btnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLogin:

                /*Validation of Login*/

                if (!txtUsername.getText().toString().equalsIgnoreCase("")||
                        !txtPassword.getText().toString().equalsIgnoreCase("")) {

                /*Request of Data*/
                    pullRequest.getUser(txtUsername.getText().toString(),
                            txtPassword.getText().toString(), routeHelpers.get(Constant.BASE_COLUMN).getCodeNo(), new VolleyCallback() {
                                @Override
                                public void onSuccess(int result) {

                                    switch (result){
                                        case VolleyCallback.ON_RESPONSE_SUCCESS:

                                            startActivity(new Intent(getActivity(), MainActivity.class));
                                            getActivity().finish();

                                            break;
                                    }

                                }

                                @Override
                                public void onFailed(int result) {

                                }

                                @Override
                                public void onStringResponse(String result) {

                                }
                            });
                }else {
                    Toast.makeText(getActivity(), R.string.msg_incorrect_user, Toast.LENGTH_LONG).show();
                }

                break;
        }

    }
}
