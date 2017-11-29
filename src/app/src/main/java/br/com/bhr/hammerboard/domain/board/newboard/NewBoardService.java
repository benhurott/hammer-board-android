package br.com.bhr.hammerboard.domain.board.newboard;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardRepository;

import java.util.UUID;

/**
 * Created by ben on 28/11/2017.
 */

public class NewBoardService {
    private BoardRepository boardRepository;

    public NewBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void createNewBoard(NewBoardModel newBoard, ActionResult<BoardException, BoardEntity> actionResult) {
        if (newBoard == null) {
            actionResult.onError(new BoardException("The board must exist."));
            return;
        }

        if (newBoard.getName() == null || newBoard.getName().isEmpty()) {
            actionResult.onError(new BoardException("The board name is required."));
            return;
        }

        if (newBoard.getTemplate() == null) {
            actionResult.onError(new BoardException("The board template is required."));
            return;
        }

        BoardEntity entity = new BoardEntity(newBoard.getName(), newBoard.getTemplate(), this.generateShareCode());

        this.boardRepository.insert(entity, actionResult);
    }

    private String generateShareCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        return uuid;
    }
}
