package br.com.bhr.hammerboard.infra.repository.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardCardRepository;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.viewboard.BoardSectionModel;
import br.com.bhr.hammerboard.domain.board.viewboard.card.NewCardModel;

/**
 * Created by benhur on 07/12/17.
 */

public class BoardCardRepositoryFIR implements BoardCardRepository {
    @Override
    public void getCardsForSection(BoardSectionModel section, final ActionResult<BoardException, ArrayList<BoardCardEntity>> actionResult) {
        getCardsRef(section).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    actionResult.onSuccess(new ArrayList<BoardCardEntity>());
                    return;
                }

                Map<String, Object> dbData = (Map<String, Object>) dataSnapshot.getValue();
                ArrayList<BoardCardEntity> entity = convertDbDataToEntity(dbData);

                actionResult.onSuccess(entity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                actionResult.onError(new BoardException(databaseError.getMessage()));
            }
        });
    }

    private ArrayList<BoardCardEntity> convertDbDataToEntity(Map<String, Object> dbData) {
        ArrayList<BoardCardEntity> result = new ArrayList<>();

        for (String key: dbData.keySet()) {
            Map<String, Object> cardData = (Map<String, Object>)dbData.get(key);
            BoardCardEntity entity = new BoardCardEntity(key, cardData.get("text").toString());
            result.add(entity);
        }

        return result;
    }

    @Override
    public void createNewCard(final NewCardModel model, final ActionResult<BoardException, BoardCardEntity> actionResult) {
        DatabaseReference dbRef = getCardsRef(model.getBoardSection());

        DatabaseReference cardRef = dbRef.push();
        final String uid = cardRef.getKey();

        Map<String, Object> dbData = new HashMap<>();
        dbData.put("text", model.getText());

        cardRef.setValue(dbData, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    actionResult.onError(new BoardException((databaseError.getMessage())));
                    return;
                }

                actionResult.onSuccess(new BoardCardEntity(uid, model.getText()));
            }
        });
    }

    private DatabaseReference getCardsRef(BoardSectionModel section) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String refUrl = "cards/" + section.getRefBoard().getShareCode() + "/sections/" + section.getId();

        DatabaseReference boardRef = database.getReference(refUrl);
        return boardRef;
    }
}
