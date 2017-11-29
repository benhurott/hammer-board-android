package br.com.bhr.hammerboard.infra.repository.firebase;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardRepository;

/**
 * Created by ben on 29/11/2017.
 */

public class BoardRepositoryFIR implements BoardRepository {

    @Override
    public void insert(final BoardEntity board, final ActionResult<BoardException, BoardEntity> actionResult) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference boardRef = database.getReference("boards/" + board.getShareCode());

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

    private Map<String, Object> convertBoardToDbData(BoardEntity board) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", board.getName());
        data.put("template", board.getTemplate().toString());

        return data;
    }
}
