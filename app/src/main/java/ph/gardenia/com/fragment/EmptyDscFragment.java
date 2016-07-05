package ph.gardenia.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import java.util.List;

import ph.gardenia.com.callback.VolleyCallback;
import ph.gardenia.com.helper.UserHelper;
import ph.gardenia.com.prototype.MainActivity;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.request.PullRequest;
import ph.gardenia.com.utils.Constant;

/**
 * Created by otomatik on 6/24/16.
 */
public class EmptyDscFragment extends Fragment implements View.OnClickListener {

    private TextView lblMsgClick;
    private TextView lblMsgNewDsc;
    private TextView lblNewDsc;
    private DotProgressBar pbSplash;
    private TextView lblStatus;

    private PullRequest pullRequest;
    private List<UserHelper> userHelpers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pullRequest = new PullRequest(getActivity());
        userHelpers = UserHelper.listAll(UserHelper.class);
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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.lblNewDsc:

                pbSplash.setVisibility(View.VISIBLE);
                lblStatus.setVisibility(View.VISIBLE);
                lblMsgNewDsc.setVisibility(View.GONE);
                lblNewDsc.setVisibility(View.GONE);
                lblMsgClick.setVisibility(View.GONE);

                pullRequest.getDsc(userHelpers.get(Constant.BASE_COLUMN).getEmpId(), new VolleyCallback() {
                    @Override
                    public void onSuccess(int result) {

                        switch (result){
                            case VolleyCallback.ON_DOWLINE_STATE_DSC:
                                lblStatus.setText("FETCHING DSC");
                                break;
                            case VolleyCallback.ON_DOWLINE_STATE_DSC_ITEMS:
                                lblStatus.setText("FETCHING DSC ITEMS");
                                break;
                        }

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

                break;

        }
    }

}
