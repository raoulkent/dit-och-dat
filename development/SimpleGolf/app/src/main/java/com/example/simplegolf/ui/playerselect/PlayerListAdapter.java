package com.example.simplegolf.ui.playerselect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplegolf.R;
import com.example.simplegolf.model.Player;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {
    private PlayerSelectViewModel playerSelectViewModel;
    private List<Player> players;
    private Context context;

    public PlayerListAdapter(PlayerSelectViewModel playerSelectViewModel, Context context) {
        this.playerSelectViewModel = playerSelectViewModel;
        this.players = playerSelectViewModel.getPlayers();
        this.context = context;
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerAbbr, playerHcp, playerTee;
        MaterialButton playerEdit, playerDelete;

        PlayerViewHolder(View itemView) {
            super(itemView);
            playerAbbr = itemView.findViewById(R.id.player_abbreviation);
            playerHcp = itemView.findViewById(R.id.player_handicap);
            playerTee = itemView.findViewById(R.id.player_tee);
            playerEdit = itemView.findViewById(R.id.edit_button);
            playerDelete = itemView.findViewById(R.id.delete_button);
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
        holder.playerHcp.setText(Double.toString(player.getHcp()));
        holder.playerTee.setText(player.getTeeName());

        holder.playerEdit.setOnClickListener(v -> selectPlayer(holder));
        holder.playerDelete.setOnClickListener(v -> deletePlayer(holder));
    }

    private void selectPlayer(PlayerViewHolder holder) {
        Player player = players.get(holder.getAdapterPosition());

        AddPlayerDialog addPlayerDialog = new EditPlayerDialog(player);
        addPlayerDialog.show(((FragmentActivity) context).getSupportFragmentManager(), "game activity dialog");
    }

    private void deletePlayer(PlayerViewHolder holder) {
        int position = holder.getAdapterPosition();
        Player player = players.get(position);

        playerSelectViewModel.removePlayer(player);
        this.notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}