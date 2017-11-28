package br.com.bhr.hammerboard.ui.newboard;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import br.com.bhr.hammerboard.R;

public class ChoseNewBoardTemplateActivity extends AppCompatActivity {

    private LinearLayout boardPositiveNegative;
    private LinearLayout boardPositiveNegativeNewIdeas;
    private LinearLayout boardTeamToolsProductOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_new_board_template);

        this.boardPositiveNegative = findViewById(R.id.board_positive_negative);
        this.boardPositiveNegativeNewIdeas = findViewById(R.id.board_positive_negative_newideas);
        this.boardTeamToolsProductOthers = findViewById(R.id.board_team_tools_product_others);
    }

    public void onSelectBoardTemplate(View view) {
        this.deselectAllTemplates();
        this.setTemplateAsSelected(view);
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
