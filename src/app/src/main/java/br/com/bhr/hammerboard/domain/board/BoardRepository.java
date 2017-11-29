package br.com.bhr.hammerboard.domain.board;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.newboard.NewBoardModel;

/**
 * Created by ben on 28/11/2017.
 */

public interface BoardRepository {
    void create(NewBoardModel board, ActionResult<BoardException, NewBoardModel> actionResult);
}
