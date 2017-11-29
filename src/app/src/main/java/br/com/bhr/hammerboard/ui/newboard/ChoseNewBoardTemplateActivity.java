package br.com.bhr.hammerboard.ui.newboard;

import android.graphics.drawable.GradientDrawable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.domain.board.BoardEntity;

public class ChoseNewBoardTemplateActivity extends AppCompatActivity implements NewBoardFormFragment.Listener {

    private NewBoardFormFragment formFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_new_board_template);

        this.renderForm();
    }

    private void renderForm() {
        FragmentManager fm = getSupportFragmentManager();

        this.formFragment = new NewBoardFormFragment();
        this.formFragment.setListener(this);

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_content, formFragment);
        ft.commit();
    }

    @Override
    public void onBoardCreated(BoardEntity board) {
        Toast.makeText(this, "Board " + board.getName() + " created!" , Toast.LENGTH_SHORT).show();
    }
}
