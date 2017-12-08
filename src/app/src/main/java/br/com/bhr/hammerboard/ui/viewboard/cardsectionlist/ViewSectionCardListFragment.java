package br.com.bhr.hammerboard.ui.viewboard.cardsectionlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.core.DependencyManager;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.viewboard.BoardSectionModel;
import br.com.bhr.hammerboard.domain.board.viewboard.card.BoardCardService;
import br.com.bhr.hammerboard.domain.board.viewboard.card.NewCardModel;
import br.com.bhr.hammerboard.ui.utils.KeyboardUtils;
import br.com.bhr.hammerboard.ui.viewboard.ViewBoardManager;
import br.com.bhr.hammerboard.ui.viewboard.ViewBoardSectionsListAdapter;

/**
 * Created by ben on 02/12/2017.
 */

public class ViewSectionCardListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private View view;
    private View addNewCardForm;
    private FloatingActionButton addNewCardButton;
    private EditText newCardText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_section_card_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        this.mRecyclerView = view.findViewById(R.id.card_list);

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ViewCardSectionListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        this.addNewCardForm = view.findViewById(R.id.new_card_form);
        this.addNewCardButton = this.view.findViewById(R.id.add_new_card_button);
        this.newCardText = this.view.findViewById(R.id.new_card_text);

        this.configActions();
    }


    private void configActions() {
        addNewCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewCardForm();
            }
        });

        this.view.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeNewCardForm();
            }
        });

        this.view.findViewById(R.id.create_card_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCard();
            }
        });
    }

    private void openNewCardForm() {
        this.addNewCardButton.setVisibility(View.INVISIBLE);
        this.addNewCardForm.bringToFront();
        this.addNewCardForm.setVisibility(View.VISIBLE);

        KeyboardUtils.openKeyboardForEditText(this.getActivity(), this.newCardText);
    }

    private void closeNewCardForm() {
        KeyboardUtils.hideKeyboard(this.getActivity());
        this.addNewCardForm.setVisibility(View.INVISIBLE);
        this.addNewCardButton.setVisibility(View.VISIBLE);
        this.newCardText.getText().clear();
    }

    private void createCard() {
        final BoardSectionModel section = ViewBoardManager.getInstance().getSelectedSection();
        NewCardModel model = new NewCardModel(this.newCardText.getText().toString(), section);
        BoardCardService boardCardService = DependencyManager.getInstance().getBoardCardService();

        boardCardService.createCard(model, new ActionResult<BoardException, BoardCardEntity>() {
            @Override
            public void onSuccess(BoardCardEntity result) {
                Toast.makeText(getActivity(), "Card created =).", Toast.LENGTH_SHORT).show();
                closeNewCardForm();

                ViewBoardManager.getInstance().loadSectionCards(section, new ActionResult<BoardException, ArrayList<BoardCardEntity>>() {
                    @Override
                    public void onSuccess(ArrayList<BoardCardEntity> result) {
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(BoardException e) {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onError(BoardException e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
