package br.com.bhr.hammerboard.infra.repository.mock;

import java.util.ArrayList;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardRepository;
import br.com.bhr.hammerboard.domain.board.NewBoardModel;

/**
 * Created by ben on 28/11/2017.
 */

public class BoardRepositoryMock implements BoardRepository {
    @Override
    public void create(NewBoardModel board, ActionResult<BoardException, NewBoardModel> actionResult) {
        actionResult.onSuccess(board);
    }
}
