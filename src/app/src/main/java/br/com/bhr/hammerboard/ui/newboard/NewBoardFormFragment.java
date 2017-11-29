package br.com.bhr.hammerboard.ui.newboard;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.core.DependencyManager;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardTemplateType;
import br.com.bhr.hammerboard.domain.board.newboard.NewBoardModel;
import br.com.bhr.hammerboard.domain.board.newboard.NewBoardService;

/**
 * Created by ben on 28/11/2017.
 */

public class NewBoardFormFragment extends Fragment {

    private EditText boardName;

    private LinearLayout boardPositiveNegative;
    private LinearLayout boardPositiveNegativeNewIdeas;
    private LinearLayout boardTeamToolsProductOthers;

    private Button buttonCreateBoard;

    private BoardTemplateType selectedTemplate = null;

    private Listener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_board_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.boardPositiveNegative = view.findViewById(R.id.board_positive_negative);
        this.boardPositiveNegativeNewIdeas = view.findViewById(R.id.board_positive_negative_newideas);
        this.boardTeamToolsProductOthers = view.findViewById(R.id.board_team_tools_product_others);
        this.boardName = view.findViewById(R.id.etBoardName);
        this.buttonCreateBoard = view.findViewById(R.id.btn_create_board);

        this.buttonCreateBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateNewBoardTouched();
            }
        });

        View.OnClickListener onTemplateTouched = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectBoardTemplate(view);
            }
        };
        this.boardPositiveNegative.setOnClickListener(onTemplateTouched);
        this.boardPositiveNegativeNewIdeas.setOnClickListener(onTemplateTouched);
        this.boardTeamToolsProductOthers.setOnClickListener(onTemplateTouched);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private void onSelectBoardTemplate(View view) {
        this.deselectAllTemplates();

        switch (view.getId()) {
            case R.id.board_positive_negative:
                this.selectedTemplate = BoardTemplateType.POSITIVE_NEGATIVE;
                this.setTemplateAsSelected(this.boardPositiveNegative);
                break;
            case R.id.board_positive_negative_newideas:
                this.selectedTemplate = BoardTemplateType.POSITIVE_NEGATIVE_NEWIDEAS;
                this.setTemplateAsSelected(this.boardPositiveNegativeNewIdeas);
                break;
            case R.id.board_team_tools_product_others:
                this.selectedTemplate = BoardTemplateType.TEAM_TOOLS_PRODUCT_OTHERS;
                this.setTemplateAsSelected(this.boardTeamToolsProductOthers);
                break;
            default:
                break;
        }

        this.setTemplateAsSelected(view);
    }

    private void onCreateNewBoardTouched() {
        NewBoardModel model = new NewBoardModel(this.boardName.getText().toString(), this.selectedTemplate);
        NewBoardService newBoardService = DependencyManager.getInstance().getNewBoardService();

        newBoardService.createNewBoard(model, new ActionResult<BoardException, BoardEntity>() {
            @Override
            public void onSuccess(BoardEntity result) {

                listener.onBoardCreated(result);
            }

            @Override
            public void onError(BoardException e) {
                Toast.makeText(NewBoardFormFragment.this.getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTemplateAsSelected(View view) {
        GradientDrawable border = new GradientDrawable();
        border.setColor(getResources().getColor(R.color.colorUltraLightGray)); //white background
        border.setStroke(10, getResources().getColor(R.color.colorAccent)); //black border with full opacity

        view.setBackground(border);
    }

    private void deselectAllTemplates() {
        int color = getResources().getColor(R.color.colorUltraLightGray);
        this.boardPositiveNegative.setBackgroundColor(color);
        this.boardPositiveNegativeNewIdeas.setBackgroundColor(color);
        this.boardTeamToolsProductOthers.setBackgroundColor(color);
    }

    public interface Listener {
        void onBoardCreated(BoardEntity board);
    }
}
