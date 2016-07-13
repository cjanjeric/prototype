package ph.gardenia.com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ph.gardenia.com.helper.DownlineCustomerHelper;
import ph.gardenia.com.helper.DownlineDscItemHelper;
import ph.gardenia.com.helper.DownlineForecastHelper;
import ph.gardenia.com.helper.DownlineProductHelper;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.utils.Constant;
import ph.gardenia.com.utils.OnItemClickListener;


/**
 * Created by otomatik on 8/19/15.
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private String TAG = getClass().getSimpleName();
    private List<DownlineForecastHelper> forecastHelpers;

    public OnItemClickListener mOnItemClickListener;

    public TransactionAdapter(List<DownlineForecastHelper> forecastHelpers) {
        this.forecastHelpers = forecastHelpers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgStore;
        public TextView lblCode;
        public TextView lblDescription;

        public ViewHolder(View v) {
            super(v);
            imgStore = (ImageView) v.findViewById(R.id.imgStore);
            lblCode = (TextView) v.findViewById(R.id.lblCustomerCode);
            lblDescription = (TextView) v.findViewById(R.id.lblCustomerName);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void SetOnItemClickListener(final OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String customerId = String.valueOf(forecastHelpers.get(position).getCustomerCid());
        List<DownlineCustomerHelper> customerHelpers = DownlineForecastHelper.find(DownlineCustomerHelper.class, "cid = ?", customerId);

        holder.lblCode.setText(String.valueOf(customerHelpers.get(Constant.BASE_COLUMN).getCustomerCode()));
        holder.lblDescription.setText(customerHelpers.get(Constant.BASE_COLUMN).getCustomerName());

    }

    @Override
    public int getItemCount() {
        return forecastHelpers.size();
    }


}
