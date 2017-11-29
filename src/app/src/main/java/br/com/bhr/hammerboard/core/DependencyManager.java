package br.com.bhr.hammerboard.core;

import br.com.bhr.hammerboard.domain.board.BoardRepository;
import br.com.bhr.hammerboard.domain.board.newboard.NewBoardService;
import br.com.bhr.hammerboard.infra.repository.firebase.BoardRepositoryFIR;
import br.com.bhr.hammerboard.infra.repository.mock.BoardRepositoryMock;

/**
 * Created by ben on 28/11/2017.
 */

public class DependencyManager {
    private static DependencyManager instance;
    public static DependencyManager getInstance() {
        if (instance == null) {
            instance = new DependencyManager();
        }

        return  instance;
    }

    private BoardRepository boardRepository;
    private BoardRepository getBoardRepository() {
        if (this.boardRepository == null) {
            this.boardRepository = new BoardRepositoryFIR();
        }

        return this.boardRepository;
    }

    private NewBoardService newBoardService;
    public NewBoardService getNewBoardService() {
        if (this.newBoardService == null) {
            this.newBoardService = new NewBoardService(this.getBoardRepository());
        }

        return this.newBoardService;
    }
}
