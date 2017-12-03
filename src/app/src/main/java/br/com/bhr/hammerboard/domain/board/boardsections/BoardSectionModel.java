package br.com.bhr.hammerboard.domain.board.boardsections;

import java.io.Serializable;

/**
 * Created by ben on 02/12/2017.
 */

public class BoardSectionModel implements Serializable {
    private String name;
    private String iconName;

    public BoardSectionModel(String name, String iconName) {
        this.name = name;
        this.iconName = iconName;
    }

    public String getName() {
        return name;
    }

    public String getIconName() {
        return iconName;
    }
}
