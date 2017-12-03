package br.com.bhr.hammerboard.ui.viewboard;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.ui.newboard.NewBoardFormFragment;

public class ViewBoardActivity extends AppCompatActivity {

    private ViewBoardSectionsFragment viewBoardSectionsFragment;
    private BoardEntity boardEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_board);

        this.boardEntity = (BoardEntity) getIntent().getSerializableExtra("board");

        this.setTitle(this.boardEntity.getName());

        this.renderBoardSectionList();
    }

    private void renderBoardSectionList() {
        FragmentManager fm = getSupportFragmentManager();

        this.viewBoardSectionsFragment = new ViewBoardSectionsFragment();

        Bundle args = new Bundle();
        args.putSerializable("board", boardEntity);
        this.viewBoardSectionsFragment.setArguments(args);

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_content, this.viewBoardSectionsFragment);
        ft.commit();
    }
}