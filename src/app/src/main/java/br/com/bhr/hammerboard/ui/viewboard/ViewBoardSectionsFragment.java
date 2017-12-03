package br.com.bhr.hammerboard.ui.viewboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.viewboard.BoardSectionModel;

/**
 * Created by ben on 02/12/2017.
 */

public class ViewBoardSectionsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ViewBoardSectionsListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_board_sections, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mRecyclerView = view.findViewById(R.id.list_sections);

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        BoardEntity board = ViewBoardManager.getInstance().getBoard();

        mAdapter = new ViewBoardSectionsListAdapter(this);

        mRecyclerView.setAdapter(mAdapter);
    }
}
