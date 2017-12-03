package br.com.bhr.hammerboard.ui.joinboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.core.DependencyManager;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.joinboard.JoinBoardService;
import br.com.bhr.hammerboard.ui.viewboard.ViewBoardActivity;

/**
 * Created by ben on 29/11/2017.
 */

public class JoinBoardEnterShareCodeFragment extends Fragment {

    EditText shareCode;
    Button btnJoinBoard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.join_board_enter_share_code, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.shareCode = view.findViewById(R.id.etShareCode);
        this.btnJoinBoard = view.findViewById(R.id.btnJoinBoard);
        this.btnJoinBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinBoard();
            }
        });

        this.shareCode.setText("4ec393c2f2");
    }

    private void joinBoard() {
        JoinBoardService joinBoardService = DependencyManager.getInstance().getJoinBoardService();

        joinBoardService.joinWithShareCode(this.shareCode.getText().toString(), new ActionResult<BoardException, BoardEntity>() {
            @Override
            public void onSuccess(BoardEntity result) {
                Intent viewBoardIntent = new Intent(getActivity(), ViewBoardActivity.class);
                viewBoardIntent.putExtra("board", result);
                getActivity().startActivity(viewBoardIntent);
            }

            @Override
            public void onError(BoardException e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
