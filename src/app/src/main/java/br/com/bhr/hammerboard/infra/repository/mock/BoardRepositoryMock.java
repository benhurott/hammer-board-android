package br.com.bhr.hammerboard.infra.repository.mock;

import java.util.ArrayList;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardRepository;
import br.com.bhr.hammerboard.domain.board.BoardTemplateType;
import br.com.bhr.hammerboard.domain.board.newboard.NewBoardModel;

/**
 * Created by ben on 28/11/2017.
 */

public class BoardRepositoryMock implements BoardRepository {

    @Override
    public void insert(BoardEntity board, ActionResult<BoardException, BoardEntity> actionResult) {
        actionResult.onSuccess(board);
    }

    @Override
    public void getByShareCode(String shareCode, ActionResult<BoardException, BoardEntity> actionResult) {
        actionResult.onSuccess(new BoardEntity("Mock", BoardTemplateType.POSITIVE_NEGATIVE, "abc123def"));
    }
}
