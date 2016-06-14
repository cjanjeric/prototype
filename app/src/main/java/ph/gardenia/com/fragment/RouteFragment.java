package ph.gardenia.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ph.gardenia.com.callback.VolleyCallback;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.request.PullRequest;

/**
 * Created by otomatik on 6/10/16.
 */
public class RouteFragment extends Fragment {

    @BindView(R.id.txtRouteCode)
    EditText txtRoutecode;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

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
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onclick() {

        Log.d(TAG, "ROUTE CODE " + txtRoutecode.getText().toString());
        String routeCode = txtRoutecode.getText().toString();
        pullRequest.getRoute(routeCode, new VolleyCallback() {
            @Override
            public void onSuccess(int result) {

            }

            @Override
            public void onFailed(int result) {

            }

            @Override
            public void onStringResponse(String result) {

            }
        });

    }

}
