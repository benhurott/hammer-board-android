package br.com.bhr.hammerboard.domain.board;

import java.util.ArrayList;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.viewboard.card.NewCardModel;

/**
 * Created by ben on 02/12/2017.
 */

public interface BoardCardRepository {
    void getCardsForSection(String sectionId, ActionResult<BoardException, ArrayList<BoardCardEntity>> actionResult);
    void createNewCard(NewCardModel model, ActionResult<BoardException, BoardCardEntity> actionResult);
}
