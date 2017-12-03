package br.com.bhr.hammerboard.domain.board;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.bhr.hammerboard.domain.board.BoardTemplateType;
import br.com.bhr.hammerboard.domain.board.boardsections.BoardSectionModel;

/**
 * Created by ben on 28/11/2017.
 */

public class BoardEntity implements Serializable {
    private String name;
    private BoardTemplateType template;
    private String shareCode;

    private ArrayList<BoardSectionModel> sections;

    public BoardEntity(String name, BoardTemplateType template, String shareCode) {
        this.name = name;
        this.template = template;
        this.shareCode = shareCode;

        this.buildSections();
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

    public ArrayList<BoardSectionModel> getSections() {
        return sections;
    }

    private void buildSections() {
        this.sections = new ArrayList<>();
        BoardTemplateType currentTemplate = this.getTemplate();

        if (currentTemplate == BoardTemplateType.POSITIVE_NEGATIVE) {
            this.buildSectionForPositiveNegative();
        }
        else if (currentTemplate == BoardTemplateType.POSITIVE_NEGATIVE_NEWIDEAS) {
            this.buildSectionForPositiveNegativeNewIdeas();
        }
        else if (currentTemplate == BoardTemplateType.TEAM_TOOLS_PRODUCT_OTHERS) {
            this.buildSectionForTeamToolsProductOther();
        }
    }

    private void buildSectionForPositiveNegative() {
        this.sections.add(new BoardSectionModel("Positive", "smile_happy_bl"));
        this.sections.add(new BoardSectionModel("Negative", "smile_negative_bl"));
    }

    private void buildSectionForPositiveNegativeNewIdeas() {
        this.sections.add(new BoardSectionModel("Positive", "smile_happy_bl"));
        this.sections.add(new BoardSectionModel("Negative", "smile_negative_bl"));
        this.sections.add(new BoardSectionModel("New Ideas", "new_idea"));
    }

    private void buildSectionForTeamToolsProductOther() {
        this.sections.add(new BoardSectionModel("Team", "team"));
        this.sections.add(new BoardSectionModel("Tools", "tools"));
        this.sections.add(new BoardSectionModel("Product", "product"));
        this.sections.add(new BoardSectionModel("Others", "others"));
    }
}
