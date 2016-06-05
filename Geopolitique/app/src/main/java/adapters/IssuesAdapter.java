package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import geopolitique.id11699156.com.geopolitique.IssuesActivity;
import util.Constants;
import geopolitique.id11699156.com.geopolitique.IssueResolveActivity;
import geopolitique.id11699156.com.geopolitique.R;

import model.Cabinet;
import model.Issue;

/**
 * Created by yiannischambers on 30/05/2016.
 */
public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssuesAdapterViewHolder>{
    private ArrayList<Issue> mIssues;
    private Context mContext;
    private Cabinet mCabinet;
    private boolean isSettingMinister = false;
    private IssuesActivity mActivity;

    public IssuesAdapter(Context context, LinkedList<Issue> issues, IssuesActivity activity){
        mIssues = new ArrayList<Issue>(issues);
        mContext = context;
        mActivity = activity;
    }

    @Override
    public IssuesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate a new adapter row
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_adapter_item, parent, false);
        return new IssuesAdapterViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(IssuesAdapterViewHolder holder, int position) {

        final Issue issue  = mIssues.get(position);

        final long ID = issue.getID();


        holder.mName.setText(issue.getName());

        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, IssueResolveActivity.class);
                intent.putExtra(Constants.INTENT_ISSUE_ID, ID);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
                mActivity.finish();


            }
        });

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mIssues.toArray().length;
    }

    public class IssuesAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private Button mButton;

        public IssuesAdapterViewHolder(View itemView) {
            super(itemView);

            //Initialise fields from Views on row
            mName = (TextView) itemView.findViewById(R.id.issue_adapter_name_text);
            mButton = (Button) itemView.findViewById(R.id.issue_adapter_button);
        }
    }
}
