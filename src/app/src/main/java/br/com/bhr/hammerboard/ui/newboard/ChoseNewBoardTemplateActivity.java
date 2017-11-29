package br.com.bhr.hammerboard.ui.newboard;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.transition.Slide;
import android.view.Gravity;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.domain.board.BoardEntity;

public class ChoseNewBoardTemplateActivity extends AppCompatActivity implements NewBoardFormFragment.Listener {

    private NewBoardFormFragment formFragment;
    private NewBoardCreatedFragment boardCreatedFragment;

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

        this.formFragment.setExitTransition(new Slide(Gravity.BOTTOM));

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_content, formFragment);
        ft.commit();
    }

    @Override
    public void onBoardCreated(BoardEntity board) {
        this.renderBoardCreated(board);
    }

    private void renderBoardCreated(BoardEntity board) {
        FragmentManager fm = getSupportFragmentManager();
        this.boardCreatedFragment = new NewBoardCreatedFragment();

        Bundle args = new Bundle();
        args.putSerializable("board_created", board);
        this.boardCreatedFragment.setArguments(args);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, this.boardCreatedFragment);
        ft.commit();
    }
}
