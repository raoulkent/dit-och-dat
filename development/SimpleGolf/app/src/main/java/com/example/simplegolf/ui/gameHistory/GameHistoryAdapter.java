package com.example.simplegolf.ui.gameHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.GameHistoryViewHolder> {
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;
    private GameHistoryViewModel viewModel;

    public GameHistoryAdapter(GameHistoryViewModel viewModel, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    @Override
    public GameHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_game_history, parent, false);
        return new GameHistoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(GameHistoryViewHolder holder, int position) {
        List<Scorecard> scorecards = viewModel.getScorecards();

        holder.tvPlayers.setText(generatePlayersString(scorecards.get(position).getPlayers()));
        holder.tvCourseName.setText(scorecards.get(position).getCourse().getName());
        holder.tvDate.setText(scorecards.get(position).getDate());
        holder.deleteButton.setOnClickListener(v -> deleteGame(holder));
    }

    private void deleteGame(GameHistoryViewHolder holder) {
        List<Scorecard> scorecards = viewModel.getScorecards();

        int position = holder.getAdapterPosition();
        Scorecard scorecard = scorecards.get(position);

        viewModel.removeScorecard(scorecard);
        this.notifyItemRemoved(position);
    }

    private String generatePlayersString(List<Player> players) {
        String res = "";
        for (int p = 0; p < players.size(); p++)
            if (p == 0)
                res += players.get(p).getInitials();
            else
                res += ", " + players.get(p).getInitials();
        return res;
    }


    @Override
    public int getItemCount() {
        return viewModel.getScorecards().size();
    }

    // stores and recycles views as they are scrolled off screen
    public class GameHistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvPlayers, tvCourseName, tvDate;
        MaterialButton deleteButton;

        GameHistoryViewHolder(View itemView) {
            super(itemView);
            tvPlayers = itemView.findViewById(R.id.tvPlayers);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvDate = itemView.findViewById(R.id.tvDate);
            deleteButton = itemView.findViewById(R.id.delete_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Scorecard getItem(int id) {
        List<Scorecard> scorecards = viewModel.getScorecards();

        return scorecards.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void update(GameHistoryViewModel viewModel) {
        this.viewModel = viewModel;
        notifyDataSetChanged();
    }
}
