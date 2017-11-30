package br.com.bhr.hammerboard.ui.joinboard;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.ui.newboard.NewBoardFormFragment;

public class JoinBoardActivity extends AppCompatActivity {

    private JoinBoardEnterShareCodeFragment joinBoardEnterShareCodeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_board);

        this.renderEnterShareCode();
    }

    private void renderEnterShareCode() {
        FragmentManager fm = getSupportFragmentManager();

        this.joinBoardEnterShareCodeFragment = new JoinBoardEnterShareCodeFragment();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_content, joinBoardEnterShareCodeFragment);
        ft.commit();
    }
}
