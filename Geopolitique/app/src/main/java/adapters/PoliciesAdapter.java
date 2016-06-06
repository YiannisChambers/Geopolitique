/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */

package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import geopolitique.id11699156.com.geopolitique.PoliciesScreen;
import util.Constants;
import util.NumberHelper;
import geopolitique.id11699156.com.geopolitique.PolicyActivity;
import geopolitique.id11699156.com.geopolitique.R;
import model.Cabinet;
import model.Policy;

/**
 * Ministers Adapter Class:
 * Defines an Minister adapter for use on the  CabinetScreen and the MinistersActivity
 * to list all Ministers in the database for manipulation.
 */
public class PoliciesAdapter extends RecyclerView.Adapter<PoliciesAdapter.PoliciesAdapterViewHolder> {

    private ArrayList<Policy> mPolicies;
    private Context mContext;
    private PoliciesScreen mActivity;
    private boolean mIsCurrentPolicy;

    public PoliciesAdapter(Context context, LinkedList<Policy> policies, PoliciesScreen screen, boolean isCurrentPolicy) {
        mPolicies = new ArrayList<Policy>(policies);
        mContext = context;
        mActivity = screen;
        mIsCurrentPolicy = isCurrentPolicy;
    }

    @Override
    public PoliciesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate a new adapter row
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.policy_adapter_item, parent, false);
        return new PoliciesAdapterViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(PoliciesAdapterViewHolder holder, int position) {

        //Get current policy
        final Policy policy = mPolicies.get(position);

        final long pos = policy.getID();

        //Set the values of the views on the card.
        holder.mName.setText(policy.getName());
        holder.mDescription.setText(policy.getDescription());
        holder.mSize.setText(policy.getSize() + "");
        holder.mCost.setText("" + NumberHelper.getWordedVersion(policy.getCost()));
        holder.mTimeToComplete.setText("" + policy.getTimeToComplete());

        //If it's a current policy
        /*if(mIsCurrentPolicy) {
            //Remove the button.
            holder.mButton.setVisibility(View.INVISIBLE);
        }
        else {*/
            //Set up a listener for the button to go to the detailed Policy screen
            holder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PolicyActivity.class);
                    //Place the policy ID in the Intent for retrieval
                    intent.putExtra(Constants.INTENT_POLICY_ID, pos);
                    mContext.startActivity(intent);
                    mActivity.finish();

                }
            });
        //}

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mPolicies.toArray().length;
    }

    /**
     * Viewholder Class to hold the View variables for a Policy Item.
     */
    public class PoliciesAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;
        private TextView mSize;
        private TextView mCost;
        private TextView mTimeToComplete;
        private Button mButton;

        public PoliciesAdapterViewHolder(View itemView) {
            super(itemView);

            //Initialise fields from Views on row
            mName = (TextView) itemView.findViewById(R.id.policy_adapter_name);
            mDescription = (TextView) itemView.findViewById(R.id.policy_adapter_description);
            mSize = (TextView) itemView.findViewById(R.id.policy_adapter_size);
            mCost = (TextView) itemView.findViewById(R.id.policy_adapter_cost);
            mTimeToComplete = (TextView) itemView.findViewById(R.id.policy_adapter_time);
            mButton = (Button) itemView.findViewById(R.id.policy_adapter_change_button);
        }
    }
}
