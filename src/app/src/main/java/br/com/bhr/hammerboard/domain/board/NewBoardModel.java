package br.com.bhr.hammerboard.domain.board;

/**
 * Created by ben on 28/11/2017.
 */

public class NewBoardModel {
    private String name;
    private BoardTemplateType template;

    public NewBoardModel(String name, BoardTemplateType template) {
        this.name = name;
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public BoardTemplateType getTemplate() {
        return template;
    }
}
