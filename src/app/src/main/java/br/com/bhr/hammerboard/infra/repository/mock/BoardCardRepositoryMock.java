package br.com.bhr.hammerboard.infra.repository.mock;

import java.util.ArrayList;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardCardRepository;
import br.com.bhr.hammerboard.domain.board.BoardException;

/**
 * Created by ben on 02/12/2017.
 */

public class BoardCardRepositoryMock implements BoardCardRepository {
    @Override
    public void getCardsForSection(String sectionId, ActionResult<BoardException, ArrayList<BoardCardEntity>> actionResult) {
        ArrayList<BoardCardEntity> list = new ArrayList<>();

        if (sectionId.equals("tools")) {
            list.add(new BoardCardEntity("1", "this is a mocked card"));
        }

        actionResult.onSuccess(list);
    }
}
