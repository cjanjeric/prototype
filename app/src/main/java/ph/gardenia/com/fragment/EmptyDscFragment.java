package ph.gardenia.com.fragment;

import android.content.Intent;
import android.media.CamcorderProfile;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import java.util.List;

import ph.gardenia.com.callback.VolleyCallback;
import ph.gardenia.com.helper.RouteHelper;
import ph.gardenia.com.helper.UserHelper;
import ph.gardenia.com.prototype.MainActivity;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.request.PullRequest;
import ph.gardenia.com.utils.Constant;

/**
 * Created by otomatik on 6/24/16.
 */
public class EmptyDscFragment extends Fragment implements View.OnClickListener {

    private String TAG = getClass().getSimpleName();

    private TextView lblMsgClick;
    private TextView lblMsgNewDsc;
    private TextView lblNewDsc;
    private DotProgressBar pbSplash;
    private TextView lblStatus;

    private PullRequest pullRequest;
    private List<UserHelper> userHelpers;
    private List<RouteHelper> routeHelpers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pullRequest = new PullRequest(getActivity());
        userHelpers = UserHelper.listAll(UserHelper.class);
        routeHelpers = RouteHelper.listAll(RouteHelper.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_dsc, container, false);

        lblMsgClick = (TextView) view.findViewById(R.id.lblMsgClick);
        lblMsgNewDsc = (TextView) view.findViewById(R.id.lblMsgNewDsc);
        lblNewDsc = (TextView) view.findViewById(R.id.lblNewDsc);
        pbSplash = (DotProgressBar) view.findViewById(R.id.pbEmptyDsc);
        lblStatus = (TextView) view.findViewById(R.id.lblStatus);

        lblNewDsc.setOnClickListener(this);

        return view;
    }

    private void setTextVisibility(int front, int back) {
        pbSplash.setVisibility(back);
        lblStatus.setVisibility(back);
        lblMsgNewDsc.setVisibility(front);
        lblNewDsc.setVisibility(front);
        lblMsgClick.setVisibility(front);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.lblNewDsc:

                setTextVisibility(View.GONE, View.VISIBLE);
                lblStatus.setText("FETCHING PRODUCTS");

                pullRequest.getProduct(new VolleyCallback() {
                    @Override
                    public void onSuccess(int result) {

                        Log.d(TAG, "WEW");
                        getProductPrice();

                    }

                    @Override
                    public void onFailed(int result) {

                    }

                    @Override
                    public void onStringResponse(String result) {

                    }
                });

                break;

        }
    }

    public void getProductPrice() {
        Log.d(TAG, "PRODUCT PRICE");
        lblStatus.setText("FETCHING PRODUCTS PRICE");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                pullRequest.getProductPrice(new VolleyCallback() {
                    @Override
                    public void onSuccess(int result) {
                        GetCustomer getCustomer = new GetCustomer(routeHelpers.get(Constant.BASE_COLUMN).getCodeNo());
                        getCustomer.execute();
                    }

                    @Override
                    public void onFailed(int result) {

                    }

                    @Override
                    public void onStringResponse(String result) {

                    }
                });
            }
        };
        new Thread(runnable).start();
    }

    public class GetCustomer extends AsyncTask<String, Integer, String> {

        String routeCode;

        public GetCustomer(String routeCode) {
            this.routeCode = routeCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lblStatus.setText("FETCHING CUSTOMER");
        }

        @Override
        protected String doInBackground(String... strings) {

            pullRequest.getCustomer(routeCode, new VolleyCallback() {
                @Override
                public void onSuccess(int result) {
                    getDsc(userHelpers.get(Constant.BASE_COLUMN).getEmpId());
                }

                @Override
                public void onFailed(int result) {

                }

                @Override
                public void onStringResponse(String result) {

                }
            });
            return null;
        }


    }

    private void getDsc(int empId) {
        lblStatus.setText("FETCHING DSC");
        pullRequest.getDsc(empId, new VolleyCallback() {
            @Override
            public void onSuccess(int result) {

                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();

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
