package br.com.bhr.hammerboard.domain.board.viewboard.card;

import br.com.bhr.hammerboard.domain.board.viewboard.BoardSectionModel;

/**
 * Created by benhur on 04/12/17.
 */

public class NewCardModel {
    private String text;
    private BoardSectionModel boardSection;

    public NewCardModel(String text, BoardSectionModel boardSection) {
        this.text = text;
        this.boardSection = boardSection;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BoardSectionModel getBoardSection() {
        return boardSection;
    }
}
