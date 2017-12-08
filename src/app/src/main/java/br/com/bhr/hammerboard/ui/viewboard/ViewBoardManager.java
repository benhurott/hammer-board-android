package br.com.bhr.hammerboard.ui.viewboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.core.DependencyManager;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.viewboard.BoardSectionModel;
import br.com.bhr.hammerboard.domain.board.viewboard.ViewBoardService;

/**
 * Created by ben on 02/12/2017.
 */

public class ViewBoardManager {
    private static ViewBoardManager instance;
    public static ViewBoardManager getInstance() {
        if (instance == null) {
            instance = new ViewBoardManager();
        }

        return instance;
    }

    private BoardEntity board;
    private BoardSectionModel selectedSection;
    private Map<BoardSectionModel, ArrayList<BoardCardEntity>> sectionCards;

    private ArrayList<ActionResult> onSelectedSectionChangeListeners;

    private ViewBoardManager() {
        this.sectionCards = new HashMap<>();
        this.onSelectedSectionChangeListeners = new ArrayList<>();
    }

    public BoardEntity getBoard() {
        return this.board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public BoardSectionModel getSelectedSection() {
        return this.selectedSection;
    }

    public void setSectionAsSelected(BoardSectionModel section) {
        this.selectedSection = section;

        for(ActionResult<BoardException, BoardSectionModel> listener: this.onSelectedSectionChangeListeners) {
            listener.onSuccess(section);
        }
    }

    public void loadSectionCards(final BoardSectionModel section, final ActionResult<BoardException, ArrayList<BoardCardEntity>> actionResult) {
        ViewBoardService service = DependencyManager.getInstance().getViewBoardService();
        service.getCardsForSection(section, new ActionResult<BoardException, ArrayList<BoardCardEntity>>() {
            @Override
            public void onSuccess(ArrayList<BoardCardEntity> result) {
                if (sectionCards.containsKey(section)) {
                    sectionCards.remove(section);
                }

                sectionCards.put(section, result);

                actionResult.onSuccess(result);
            }

            @Override
            public void onError(BoardException e) {
                actionResult.onError(e);
            }
        });
    }

    public ArrayList<BoardCardEntity> getSectionCards(BoardSectionModel section) {
        return sectionCards.get(section);
    }

    public void addOnSelectedSectionChangeListener(ActionResult<BoardException, BoardSectionModel> listener) {
        this.onSelectedSectionChangeListeners.add(listener);
    }

    public void removeOnSelectedSectionChangeListener(ActionResult<BoardException, BoardSectionModel> listener) {
        this.onSelectedSectionChangeListeners.remove(listener);
    }
}
