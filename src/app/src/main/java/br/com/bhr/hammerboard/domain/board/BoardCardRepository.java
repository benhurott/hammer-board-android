package br.com.bhr.hammerboard.domain.board;

import java.util.ArrayList;

import br.com.bhr.hammerboard.core.ActionResult;

/**
 * Created by ben on 02/12/2017.
 */

public interface BoardCardRepository {
    void getCardsForSection(String sectionId, ActionResult<BoardException, ArrayList<BoardCardEntity>> actionResult);
}
