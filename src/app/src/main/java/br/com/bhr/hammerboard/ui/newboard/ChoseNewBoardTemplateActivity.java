package br.com.bhr.hammerboard.ui.newboard;

import android.graphics.drawable.GradientDrawable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.bhr.hammerboard.R;

public class ChoseNewBoardTemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_new_board_template);

        this.renderForm();
    }

    private void renderForm() {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_content, new NewBoardFormFragment());
        ft.commit();
    }
}
