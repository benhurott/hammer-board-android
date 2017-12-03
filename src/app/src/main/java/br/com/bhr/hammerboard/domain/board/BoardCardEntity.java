package br.com.bhr.hammerboard.domain.board;

import java.io.Serializable;

/**
 * Created by ben on 02/12/2017.
 */

public class BoardCardEntity implements Serializable {
    private String id;
    private String text;

    public BoardCardEntity(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
