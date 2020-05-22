package com.example.simplegolf.ui.gamehistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.ConfirmDialog;
import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.GameHistoryViewHolder> implements ConfirmDialog.ConfirmDialogListener {
    private Context context;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;
    private GameHistoryViewModel viewModel;

    private int selectedScorecardPosition;

    public GameHistoryAdapter(GameHistoryViewModel viewModel, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
        this.context = context;
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
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(view, getAdapterPosition());
        }

    }

    @NonNull
    @Override
    public GameHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_game_history, parent, false);
        return new GameHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameHistoryViewHolder holder, int position) {
        List<Scorecard> scorecards = viewModel.getScorecards();

        holder.tvPlayers.setText(generatePlayersString(scorecards.get(position).getPlayers()));
        holder.tvCourseName.setText(scorecards.get(position).getCourse().getName());
        holder.tvDate.setText(scorecards.get(position).getDate());
        holder.deleteButton.setOnClickListener(v -> startDeleteDialog(holder));
    }

    private void startDeleteDialog(GameHistoryViewHolder holder) {
        String question = context.getResources().getString(R.string.question_delete_game);

        ConfirmDialog confirmDialog = new ConfirmDialog(this, question);
        confirmDialog.show(((FragmentActivity) context).getSupportFragmentManager(), "confirm deletion");

        this.selectedScorecardPosition = holder.getAdapterPosition();
    }

    private void deleteSelectedScorecard() {
        List<Scorecard> scorecards = viewModel.getScorecards();
        int position = this.selectedScorecardPosition;

        Scorecard scorecard = scorecards.get(position);

        viewModel.removeScorecard(scorecard);
        this.notifyItemRemoved(position);
    }

    @Override
    public void confirm() {
        deleteSelectedScorecard();
    }

    private String generatePlayersString(List<Player> players) {
        StringBuilder res = new StringBuilder();
        for (int p = 0; p < players.size(); p++)
            if (p == 0)
                res.append(players.get(p).getInitials());
            else
                res.append(", ").append(players.get(p).getInitials());
        return res.toString();
    }

    @Override
    public int getItemCount() {
        return viewModel.getScorecards().size();
    }

    public interface ItemClickListener {

        void onItemClick(View view, int position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
