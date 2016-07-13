package ph.gardenia.com.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ph.gardenia.com.helper.DownlineDscItemHelper;
import ph.gardenia.com.helper.DownlineProductHelper;
import ph.gardenia.com.prototype.R;
import ph.gardenia.com.utils.Constant;
import ph.gardenia.com.utils.OnItemClickListener;


/**
 * Created by otomatik on 8/19/15.
 */
public class DscAdapter extends RecyclerView.Adapter<DscAdapter.ViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private List<DownlineDscItemHelper> dscItemsList;
    private Context context;

    public OnItemClickListener mOnItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtCode;
        public TextView txtDescription;
        public TextView txtQuantity;
        public TextView txtSerialCode;

        public ViewHolder(View v) {
            super(v);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "roboto.ttf");
            txtCode = (TextView) v.findViewById(R.id.txtCode);
            txtCode.setTypeface(typeface);
            txtDescription = (TextView) v.findViewById(R.id.txtDescription);
            txtDescription.setTypeface(typeface);
            txtQuantity = (TextView) v.findViewById(R.id.txtQuantity);
            txtQuantity.setTypeface(typeface);
            txtSerialCode = (TextView) v.findViewById(R.id.txtSerialCode);
            txtSerialCode.setTypeface(typeface);
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

    public DscAdapter(List<DownlineDscItemHelper> dscItemsList, Context context) {
        this.dscItemsList = dscItemsList;
        this.context = context;
    }

    @Override
    public DscAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dsc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        List<DownlineProductHelper> productHelpers = DownlineProductHelper.find (DownlineProductHelper.class, "prod_code = ?", String.valueOf(dscItemsList.get(position).getProductCode()));

        holder.txtCode.setText(String.valueOf(dscItemsList.get(position).getProductCode()));
        holder.txtDescription.setText(String.valueOf(productHelpers.get(Constant.BASE_COLUMN).getDescription()));
        holder.txtQuantity.setText(String.valueOf(dscItemsList.get(position).getIssued()));
        holder.txtSerialCode.setText(String.valueOf(productHelpers.get(Constant.BASE_COLUMN).getBarcode()));

    }

    @Override
    public int getItemCount() {
        return dscItemsList.size();
    }
}
