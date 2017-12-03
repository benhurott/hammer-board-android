package br.com.bhr.hammerboard.ui.viewboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.core.DependencyManager;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.viewboard.BoardSectionModel;
import br.com.bhr.hammerboard.domain.board.viewboard.ViewBoardService;

/**
 * Created by ben on 02/12/2017.
 */

public class ViewBoardSectionsListAdapter extends RecyclerView.Adapter<ViewBoardSectionsListAdapter.ViewHolder> {

    private BoardEntity boardEntity;
    private ViewBoardSectionsFragment parentContext;

    public ViewBoardSectionsListAdapter(ViewBoardSectionsFragment parentContext) {
        this.boardEntity = ViewBoardManager.getInstance().getBoard();
        this.parentContext = parentContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View template = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_board_sections_list_row, parent, false);
        ViewHolder vh = new ViewHolder(template);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setSection(boardEntity.getSections().get(position));
    }

    @Override
    public int getItemCount() {
        return this.boardEntity.getSections().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private BoardSectionModel section;
        private ArrayList<BoardCardEntity> cards;

        private ImageView ivSectionImage;
        private TextView etSectionName;

        public ViewHolder(View itemView) {
            super(itemView);

            this.ivSectionImage = itemView.findViewById(R.id.section_image);
            this.etSectionName = itemView.findViewById(R.id.section_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    whenTouched();
                }
            });
        }

        public BoardSectionModel getSection() {
            return section;
        }

        public void setSection(BoardSectionModel section) {
            this.section = section;
            this.etSectionName.setText(section.getName() + " (carregando...)");
            this.ivSectionImage.setImageResource(parentContext.getResources().getIdentifier(section.getIconName(), "mipmap", parentContext.getActivity().getPackageName()));
            this.loadCards();
        }

        private void loadCards() {
            ViewBoardManager.getInstance().loadSectionCards(this.section, new ActionResult<BoardException, ArrayList<BoardCardEntity>>() {
                @Override
                public void onSuccess(ArrayList<BoardCardEntity> result) {
                    cards = result;
                    bindCards();
                }

                @Override
                public void onError(BoardException e) {
                    Toast.makeText(parentContext.getActivity(), "Ooops! The cards cannot be loadded =(", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void bindCards() {
            this.etSectionName.setText(this.section.getName() + " (" + this.cards.size() + ")");
        }

        private void whenTouched() {
            ViewBoardManager.getInstance().setSectionAsSelected(this.section);
        }
    }


    public interface Listener {
        void onItemTouched(BoardSectionModel section);
    }
}
