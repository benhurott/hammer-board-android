package br.com.bhr.hammerboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.bhr.hammerboard.ui.joinboard.JoinBoardActivity;
import br.com.bhr.hammerboard.ui.newboard.ChoseNewBoardTemplateActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onCreateNewBoardTouched(View view) {
        Intent newBoardIntent = new Intent(this, ChoseNewBoardTemplateActivity.class);
        this.startActivity(newBoardIntent);
    }

    public void onJoinBoard(View view) {
        Intent newBoardIntent = new Intent(this, JoinBoardActivity.class);
        this.startActivity(newBoardIntent);
    }
}
