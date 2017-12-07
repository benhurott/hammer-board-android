package br.com.bhr.hammerboard.domain.board.viewboard.card;

/**
 * Created by benhur on 04/12/17.
 */

public class NewCardModel {
    private String text;

    public NewCardModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
