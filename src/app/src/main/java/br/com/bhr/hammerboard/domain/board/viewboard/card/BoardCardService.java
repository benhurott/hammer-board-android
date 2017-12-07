package br.com.bhr.hammerboard.domain.board.viewboard.card;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardCardRepository;
import br.com.bhr.hammerboard.domain.board.BoardException;

/**
 * Created by benhur on 07/12/17.
 */

public class BoardCardService {
    private BoardCardRepository boardCardRepository;

    public BoardCardService(BoardCardRepository boardCardRepository) {
        this.boardCardRepository = boardCardRepository;
    }

    public void createCard(NewCardModel newCardModel, ActionResult<BoardException, BoardCardEntity> actionResult) {
        if (newCardModel.getText() == null || newCardModel.getText().isEmpty()) {
            actionResult.onError(new BoardException("The card text is required."));
            return;
        }

        this.boardCardRepository.createNewCard(newCardModel, actionResult);
    }
}
