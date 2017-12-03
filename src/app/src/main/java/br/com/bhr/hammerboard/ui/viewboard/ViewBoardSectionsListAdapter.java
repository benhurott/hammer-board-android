package br.com.bhr.hammerboard.ui.viewboard;

import android.app.Activity;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.boardsections.BoardSectionModel;

/**
 * Created by ben on 02/12/2017.
 */

public class ViewBoardSectionsListAdapter extends RecyclerView.Adapter<ViewBoardSectionsListAdapter.ViewHolder> {

    private BoardEntity boardEntity;
    private ViewBoardSectionsFragment parentContext;

    public ViewBoardSectionsListAdapter(BoardEntity boardEntity, ViewBoardSectionsFragment parentContext) {
        this.boardEntity = boardEntity;
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
        private ImageView ivSectionImage;
        private TextView etSectionName;

        public ViewHolder(View itemView) {
            super(itemView);

            this.ivSectionImage = itemView.findViewById(R.id.section_image);
            this.etSectionName = itemView.findViewById(R.id.section_name);
        }

        public BoardSectionModel getSection() {
            return section;
        }

        public void setSection(BoardSectionModel section) {
            this.section = section;
            this.etSectionName.setText(section.getName());
            this.ivSectionImage.setImageResource(parentContext.getResources().getIdentifier(section.getIconName(), "mipmap", parentContext.getActivity().getPackageName()));
        }
    }
}
