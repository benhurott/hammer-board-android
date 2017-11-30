package br.com.bhr.hammerboard.infra.repository.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardRepository;
import br.com.bhr.hammerboard.domain.board.BoardTemplateType;

/**
 * Created by ben on 29/11/2017.
 */

public class BoardRepositoryFIR implements BoardRepository {

    @Override
    public void insert(final BoardEntity board, final ActionResult<BoardException, BoardEntity> actionResult) {
        DatabaseReference boardRef = this.getBoardRef(board.getShareCode());

        Map<String, Object> dbData = this.convertBoardToDbData(board);

        boardRef.setValue(dbData, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    actionResult.onError(new BoardException(databaseError.getMessage()));
                    return;
                }

                actionResult.onSuccess(board);
            }
        });
    }

    @Override
    public void getByShareCode(final String shareCode, final ActionResult<BoardException, BoardEntity> actionResult) {
        DatabaseReference boardRef = this.getBoardRef(shareCode);

        boardRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    actionResult.onSuccess(null);
                    return;
                }

                Map<String, Object> dbData = (Map<String, Object>) dataSnapshot.getValue();
                BoardEntity entity = convertDbDataToEntity(shareCode, dbData);

                actionResult.onSuccess(entity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                actionResult.onError(new BoardException(databaseError.getMessage()));
            }
        });
    }

    private DatabaseReference getBoardRef(String boardShareCode) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference boardRef = database.getReference("boards/" + boardShareCode);
        return boardRef;
    }

    private BoardEntity convertDbDataToEntity(String shareCode, Map<String, Object> dbData) {
        String name = dbData.get("name").toString();
        BoardTemplateType template = null;

        switch (dbData.get("template").toString()) {
            case "POSITIVE_NEGATIVE":
                template = BoardTemplateType.POSITIVE_NEGATIVE;
                break;
            case "POSITIVE_NEGATIVE_NEWIDEAS":
                template = BoardTemplateType.POSITIVE_NEGATIVE_NEWIDEAS;
                break;
            case "TEAM_TOOLS_PRODUCT_OTHERS":
                template = BoardTemplateType.TEAM_TOOLS_PRODUCT_OTHERS;
                break;
            default: break;
        }

        BoardEntity entity = new BoardEntity(name, template, shareCode);
        return entity;
    }

    private Map<String, Object> convertBoardToDbData(BoardEntity board) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", board.getName());
        data.put("template", board.getTemplate().toString());

        return data;
    }
}
