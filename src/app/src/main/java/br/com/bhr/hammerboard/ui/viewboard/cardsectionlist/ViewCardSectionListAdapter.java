package br.com.bhr.hammerboard.ui.viewboard.cardsectionlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.viewboard.BoardSectionModel;
import br.com.bhr.hammerboard.ui.viewboard.ViewBoardActivity;

/**
 * Created by ben on 02/12/2017.
 */

public class ViewCardSectionListAdapter extends RecyclerView.Adapter<ViewCardSectionListAdapter.ViewHolder> {

    private BoardSectionModel boardSectionModel;
    private ArrayList<BoardCardEntity> cards;

    public ViewCardSectionListAdapter(BoardSectionModel boardSectionModel, ArrayList<BoardCardEntity> cards) {
        this.boardSectionModel = boardSectionModel;
        this.cards = cards;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View template = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.view_section_card_list_row, parent, false);

        ViewHolder vh = new ViewHolder(template);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setBoardCardEntity(this.cards.get(position));
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private BoardCardEntity boardCardEntity;

        private TextView tvCardText;

        public ViewHolder(View itemView) {
            super(itemView);

            this.tvCardText = itemView.findViewById(R.id.card_text);
        }


        public BoardCardEntity getBoardCardEntity() {
            return boardCardEntity;
        }

        public void setBoardCardEntity(BoardCardEntity boardCardEntity) {
            this.boardCardEntity = boardCardEntity;

            this.tvCardText.setText(this.boardCardEntity.getText());
        }
    }
}
