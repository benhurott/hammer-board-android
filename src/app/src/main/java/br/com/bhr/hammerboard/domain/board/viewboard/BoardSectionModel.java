package br.com.bhr.hammerboard.domain.board.viewboard;

import java.io.Serializable;

import br.com.bhr.hammerboard.domain.board.BoardEntity;

/**
 * Created by ben on 02/12/2017.
 */

public class BoardSectionModel implements Serializable {
    private String id;
    private String name;
    private String iconName;
    private BoardEntity refBoard;

    public BoardSectionModel(String id, String name, String iconName, BoardEntity refBoard) {
        this.id = id;
        this.name = name;
        this.iconName = iconName;
        this.refBoard = refBoard;
    }

    public String getName() {
        return name;
    }

    public String getIconName() {
        return iconName;
    }

    public String getId() {
        return id;
    }

    public BoardEntity getRefBoard() {
        return refBoard;
    }
}
