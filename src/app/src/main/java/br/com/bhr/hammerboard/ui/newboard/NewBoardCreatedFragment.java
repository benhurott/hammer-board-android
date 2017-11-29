package br.com.bhr.hammerboard.ui.newboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.domain.board.BoardEntity;

/**
 * Created by ben on 28/11/2017.
 */

public class NewBoardCreatedFragment extends Fragment {

    TextView shareCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_board_created, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.shareCode = view.findViewById(R.id.tv_board_share_code);

        this.load();
    }

    private void load() {
        Bundle args = getArguments();
        BoardEntity createdBoard = (BoardEntity) args.getSerializable("board_created");

        this.shareCode.setText(createdBoard.getShareCode());
    }
}
