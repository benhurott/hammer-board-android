package br.com.bhr.hammerboard.domain.board;

import java.io.Serializable;

import br.com.bhr.hammerboard.domain.board.BoardTemplateType;

/**
 * Created by ben on 28/11/2017.
 */

public class BoardEntity implements Serializable {
    private String name;
    private BoardTemplateType template;
    private String shareCode;

    public BoardEntity(String name, BoardTemplateType template, String shareCode) {
        this.name = name;
        this.template = template;
        this.shareCode = shareCode;
    }

    public String getName() {
        return name;
    }

    public BoardTemplateType getTemplate() {
        return template;
    }

    public String getShareCode() {
        return shareCode;
    }
}
