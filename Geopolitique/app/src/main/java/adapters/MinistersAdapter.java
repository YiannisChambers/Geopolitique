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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import data.MinisterRepo;
import data.PlayerRepo;
import data.RealmHelper;
import geopolitique.id11699156.com.geopolitique.CabinetActivity;
import geopolitique.id11699156.com.geopolitique.MinistersActivity;
import geopolitique.id11699156.com.geopolitique.R;
import model.Cabinet;
import model.Minister;
import util.Constants;

/**
 * Ministers Adapter Class:
 * Defines an Minister adapter for use on the  CabinetScreen and the MinistersActivity
 * to list all Ministers in the database for manipulation.
 */

public class MinistersAdapter extends RecyclerView.Adapter<MinistersAdapter.MinistersAdapterViewHolder> {

    private ArrayList<Minister> mMinisters;
    private Context mContext;
    private Cabinet mCabinet;
    private boolean isSettingMinister = false;
    private MinistersActivity mActivity;
    private CabinetActivity mCabinetActivity;
    private int mMinisterPosition;

    public MinistersAdapter(Context context, Cabinet cabinet, boolean isSetting, CabinetActivity activity) {
        mCabinet = cabinet;
        mMinisters = new ArrayList<Minister>(cabinet.getMinisters());
        mContext = context;
        isSettingMinister = isSetting;
        mCabinetActivity = activity;
    }

    public MinistersAdapter(Context context, LinkedList<Minister> ministers, boolean isSetting, MinistersActivity activity, int ministerPosition) {
        mMinisters = new ArrayList<Minister>(ministers);
        mContext = context;
        isSettingMinister = isSetting;
        mActivity = activity;
        mMinisterPosition = ministerPosition;
    }

    @Override
    public MinistersAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate a new adapter row
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.minister_adapter_item, parent, false);
        return new MinistersAdapterViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(MinistersAdapterViewHolder holder, int position) {

        //Get the specified minister
        final Minister minister = mMinisters.get(position);

        long pos = 0;

        //If the minister has not been selected...
        if(minister == null){
            //...remove the Views that show states, and display name as 'not selected'...
            holder.mStats.setVisibility(View.INVISIBLE);
            holder.mName.setText(R.string.ministers_adapter_not_selected_text);
            holder.mKnowledge.setVisibility(View.INVISIBLE);
            holder.mExperience.setVisibility(View.INVISIBLE);
            holder.mWorkload.setVisibility(View.INVISIBLE);
        }
        else
        {
            //...else set the View to show all the minister's characterstics
            pos = minister.getID();
            holder.mName.setText(minister.getLastName() + ", " + minister.getFirstName().charAt(0));
            holder.mKnowledge.setText("" + minister.getKnowledge());
            holder.mExperience.setText("" + minister.getExperience());
            holder.mWorkload.setText("" + minister.getWorkload());
        }


        //If the Minister Adapter is being used to select a new Minister...
        if(isSettingMinister) {
            //Do not set the Title field.
            holder.mTitle.setVisibility(View.INVISIBLE);
            final long p = pos;

            //Set the button to set a Minister
            holder.mButton.setText(R.string.minsters_adapter_set_text);
            holder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Minister minister = MinisterRepo.getMinisterByID(p);

                    RealmHelper.beginTransaction();
                    //int ministerPosition = PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().getMinisters().indexOf(minister);
                    PlayerRepo.getCurrentPlayer().getCountry().getGovernment().getCabinet().setMinister(mMinisterPosition, minister);
                    RealmHelper.endTransaction();

                    Intent intent = new Intent(mContext, CabinetActivity.class);
                    mContext.startActivity(intent);

                    mActivity.finish();
                    //mActivity.OnSetClick(v, p);
                }
            });
        }
        else
        {
            holder.mTitle.setText(Cabinet.getTitle(position));
            final int q = position;
            holder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, MinistersActivity.class);
                    intent.putExtra(Constants.INTENT_MINISTER_NUMBER, q);
                    mContext.startActivity(intent);
                    mCabinetActivity.finish();
                    //mCabinetActivity.OnChangeClick(v, q);
                }
            });
        }


        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMinisters.toArray().length;
    }

    /**
     * Viewholder Class to hold the View variables for a Minister Item.
     */
    public class MinistersAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mTitle;
        private TextView mKnowledge;
        private TextView mWorkload;
        private TextView mExperience;
        private Button mButton;
        private LinearLayout mStats;

        /**
         * Constructor
         * @param itemView
         */
        public MinistersAdapterViewHolder(View itemView) {
            super(itemView);

            //Initialise fields from Views on row
            mName = (TextView) itemView.findViewById(R.id.minister_adapter_name);
            mTitle = (TextView) itemView.findViewById(R.id.minister_adapter_title);
            mKnowledge = (TextView) itemView.findViewById(R.id.minister_adapter_knowledge);
            mWorkload = (TextView) itemView.findViewById(R.id.minister_adapter_workload);
            mExperience = (TextView) itemView.findViewById(R.id.minister_adapter_experience);
            mButton = (Button) itemView.findViewById(R.id.minister_adapter_change_button);
            mStats = (LinearLayout) itemView.findViewById(R.id.minister_adapter_stats);
        }
    }
}