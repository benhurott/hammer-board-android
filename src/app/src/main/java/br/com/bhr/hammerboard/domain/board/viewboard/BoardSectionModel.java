package br.com.bhr.hammerboard.domain.board.viewboard;

import java.io.Serializable;

/**
 * Created by ben on 02/12/2017.
 */

public class BoardSectionModel implements Serializable {
    private String id;
    private String name;
    private String iconName;

    public BoardSectionModel(String id, String name, String iconName) {
        this.id = id;
        this.name = name;
        this.iconName = iconName;
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
}
