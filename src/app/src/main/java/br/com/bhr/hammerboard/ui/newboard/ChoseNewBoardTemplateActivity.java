package br.com.bhr.hammerboard.ui.newboard;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.domain.board.BoardTemplateType;

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
        if (!this.validateForm()) {
            return;
        }
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

    private boolean validateForm() {
        return this.validateBoardName()
                && this.validateTemplate();
    }

    private boolean validateTemplate() {
        if (this.selectedTemplate == null) {
            Toast.makeText(this, "Please select a board template.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validateBoardName() {
        if (this.boardName.getText().length() < 1) {
            Toast.makeText(this, "The board name is required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
