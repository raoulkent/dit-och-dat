package com.example.simplegolf.ui.oldGamesSelect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;

import java.util.List;

public class OldGamesRecyclerViewAdapter extends RecyclerView.Adapter<OldGamesRecyclerViewAdapter.ViewHolder> {

    private List<Scorecard> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public OldGamesRecyclerViewAdapter(Context context, List<Scorecard> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.oldgames_row, parent, false);
        return new ViewHolder(view);
    }



    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPlayers.setText(generatePlayersString(mData.get(0).getPlayers()));
        holder.tvCourseName.setText(mData.get(position).getCourse().getName());
        holder.tvDate.setText(mData.get(position).getDate());
    }

    private String generatePlayersString(List<Player> players) {
        String res = "";
        for(int p = 0; p < players.size(); p++)
            if(p == 0)
                res += players.get(p).getInitials();
            else
                res += ", " + players.get(p).getInitials();
        return res;
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }
    
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvPlayers;
        TextView tvCourseName;
        TextView tvDate;

        ViewHolder(View itemView) {
            super(itemView);
            tvPlayers = itemView.findViewById(R.id.tvPlayers);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvDate = itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Scorecard getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void update(List<Scorecard> scorecards) {
        mData = scorecards;
        notifyDataSetChanged();
    }
}
