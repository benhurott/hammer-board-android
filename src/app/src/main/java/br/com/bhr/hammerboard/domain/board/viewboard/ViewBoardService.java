package br.com.bhr.hammerboard.domain.board.viewboard;

import java.util.ArrayList;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardCardRepository;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardRepository;

/**
 * Created by ben on 02/12/2017.
 */

public class ViewBoardService {
    private BoardCardRepository boardCardRepository;

    public ViewBoardService(BoardCardRepository boardCardRepository) {
        this.boardCardRepository = boardCardRepository;
    }

    public void getCardsForSection(BoardSectionModel boardSectionModel, ActionResult<BoardException, ArrayList<BoardCardEntity>> actionResult) {
        this.boardCardRepository.getCardsForSection(boardSectionModel.getId(), actionResult);
    }
}
