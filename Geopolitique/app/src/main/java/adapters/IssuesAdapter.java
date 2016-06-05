/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

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
 * Issue Adapter Class:
 * Defines an issue adapter for use on the Issues Activity
 * to list all existing and remaining issues to complete.
 */
public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssuesAdapterViewHolder>{

    private ArrayList<Issue> mIssues;
    private Context mContext;
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

        //Get the current issue.
        final Issue issue  = mIssues.get(position);

        //Get the current issue ID
        final long ID = issue.getID();

        holder.mName.setText(issue.getName());

        //Give the button a listener that changes to the ResolveActivit
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

    /**
     * Viewholder Class to hold the View variables for an Issue Item.
     */
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
