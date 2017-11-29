package br.com.bhr.hammerboard.domain.board.newboard;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardRepository;

/**
 * Created by ben on 28/11/2017.
 */

public class NewBoardService {
    private BoardRepository boardRepository;

    public NewBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void createNewBoard(NewBoardModel model, ActionResult<BoardException, NewBoardModel> actionResult) {
        if (model == null) {
            actionResult.onError(new BoardException("The board must exist."));
            return;
        }

        if (model.getName() == null || model.getName().isEmpty()) {
            actionResult.onError(new BoardException("The board name is required."));
            return;
        }

        if (model.getTemplate() == null) {
            actionResult.onError(new BoardException("The board template is required."));
            return;
        }

        this.boardRepository.create(model, actionResult);
    }
}
