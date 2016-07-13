package ph.gardenia.com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ph.gardenia.com.adapter.TransactionAdapter;
import ph.gardenia.com.helper.DownlineForecastHelper;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.utils.DividerItemDecoration;
import ph.gardenia.com.utils.OnItemClickListener;

/**
 * Created by otomatik on 6/14/16.
 */
public class TransactionFragment extends Fragment {

    private String TAG = getClass().getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<DownlineForecastHelper> forecastHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ONCREATE");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvTransaction);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(mLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        forecastHelper = DownlineForecastHelper.listAll(DownlineForecastHelper.class);
        mAdapter = new TransactionAdapter(forecastHelper);
        mRecyclerView.setAdapter(mAdapter);


    }
}
