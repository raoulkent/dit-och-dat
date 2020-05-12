package com.example.simplegolf.ui.playerSelect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {
    private List<Player> players;
    private Context context;

    public PlayerListAdapter(List<Player> players, Context context) {
        this.players = players;
        this.context = context;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerAbbr;
        TextView playerHdcp;
        TextView playerTee;

        PlayerViewHolder(View itemView) {
            super(itemView);
            playerAbbr = itemView.findViewById(R.id.player_abbreviation);
            playerHdcp = itemView.findViewById(R.id.player_handicap);
            playerTee = itemView.findViewById(R.id.player_tee);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    @NonNull
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_player, parent, false);
        return new PlayerViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(final PlayerViewHolder holder, int position) {
        // - get element from your data set at this position
        // - replace the contents of the view with that element
        Player player = players.get(position);
        holder.playerAbbr.setText(player.getInitials());
        holder.playerHdcp.setText(Double.toString(player.getHcp()));
        holder.playerTee.setText(player.getTeeName());

        // TODO: Should pressing a player open an edit window?
        holder.itemView.setOnClickListener(v -> selectPlayer(holder));
    }

    private void selectPlayer(PlayerViewHolder holder) {
        Player player = players.get(holder.getAdapterPosition());
        // TODO: Start an edit dialog
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}