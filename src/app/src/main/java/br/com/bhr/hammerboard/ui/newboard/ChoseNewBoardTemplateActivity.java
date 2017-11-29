package br.com.bhr.hammerboard.ui.newboard;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.core.DependencyManager;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardTemplateType;
import br.com.bhr.hammerboard.domain.board.NewBoardModel;
import br.com.bhr.hammerboard.domain.board.NewBoardService;

public class ChoseNewBoardTemplateActivity extends AppCompatActivity {

    private LinearLayout boardPositiveNegative;
    private LinearLayout boardPositiveNegativeNewIdeas;
    private LinearLayout boardTeamToolsProductOthers;

    private EditText boardName;

    private BoardTemplateType selectedTemplate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_new_board_template);

        this.boardPositiveNegative = findViewById(R.id.board_positive_negative);
        this.boardPositiveNegativeNewIdeas = findViewById(R.id.board_positive_negative_newideas);
        this.boardTeamToolsProductOthers = findViewById(R.id.board_team_tools_product_others);
        this.boardName = findViewById(R.id.etBoardName);
    }

    public void onSelectBoardTemplate(View view) {
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

    public void onCreateNewBoardTouched(View view) {
        NewBoardModel model = new NewBoardModel(this.boardName.getText().toString(), this.selectedTemplate);
        NewBoardService newBoardService = DependencyManager.getInstance().getNewBoardService();

        newBoardService.createNewBoard(model, new ActionResult<BoardException, NewBoardModel>() {
            @Override
            public void onSuccess(NewBoardModel result) {
                Toast.makeText(ChoseNewBoardTemplateActivity.this, "Board " + result.getName() + " created!" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(BoardException e) {
                Toast.makeText(ChoseNewBoardTemplateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
}
