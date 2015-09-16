package de.cyberforum.tictactoe;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TicTacToe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tictactoe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //
    //
    //
    protected Button[][] btnArray;

    //@override
    public void onStart() {
        super.onStart();

        btnArray = new Button[3][3];
        int n = 0;
        String btnName;
        Resources res = getResources();
        String pckName = getPackageName();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                n++;
                btnName = "button" + n;
                int btnId = res.getIdentifier(btnName, "id", pckName);
                btnArray[i][j] = (Button) findViewById(btnId);
            }
        }
    }



    public void playbtn(View v1) {
        Button button = (Button)v1;
        if(currentIsCross){
            button.setText(CROSS);
        }else
            button.setText(CIRCLE);
        button.setEnabled(false);
        currentIsCross = !currentIsCross;
        String winner = checkWin(button);
        if (winner != BLANK) {
            TextView tv = (TextView)findViewById(R.id.editText);
            tv.setText("Winner is " + winner);
        }
    }

    public void play_again(View v2) {
        int num = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btnArray[i][j].setEnabled(true);
                btnArray[i][j].setText("");

            }

        }

        TextView tv = (TextView)findViewById(R.id.editText);
        tv.setText("--");
    }

    //
    //
    //
    //
    protected static String CROSS = "X";
    protected static String CIRCLE = "O";
    protected static String BLANK = "";

    protected static boolean currentIsCross = true;

    public String checkNeighbors(Button button){
        int len = btnArray[0].length;
        for(int i=0; i<btnArray[0].length; i++){
            Button b1 = btnArray[i][0];
            String s1 = (String)b1.getText();
            Button b2 = btnArray[i][1];
            String s2 = (String)b2.getText();
            Button b3 = btnArray[i][2];
            String s3 = (String)b3.getText();
            if (btnArray[i][0].getText().equals(BLANK))
                continue;

            if(btnArray[i][0].getText().equals(btnArray[i][1].getText())&&btnArray[i][1].getText().equals(btnArray[i][2].getText())) {
                return (String) btnArray[i][0].getText();
            }
        }
        for(int j=0; j<btnArray[0].length; j++){
            if (btnArray[0][j].getText().equals(BLANK))
                continue;

            if(btnArray[0][j].getText().equals(btnArray[1][j].getText())&&btnArray[1][j].getText().equals(btnArray[2][j].getText())) {
                return (String) btnArray[0][j].getText();
            }
        }
        if(btnArray[0][0].getText().equals(btnArray[1][1].getText())&&btnArray[1][1].getText().equals(btnArray[2][2].getText())) {
            return (String) btnArray[0][0].getText();
        }
        if(btnArray[2][0].getText().equals(btnArray[1][1].getText())&&btnArray[1][1].getText().equals(btnArray[0][2].getText())) {
            return (String) btnArray[0][2].getText();
        }
        return BLANK;
    }

    public void buttonPushed(Button button){

        if(currentIsCross){
            button.setText(CROSS);
        }else
            button.setText(CIRCLE);
        button.setEnabled(false);
        checkWin(button);

    }

    public String checkWin(Button button){
//        int idButton = button.getId();
//        String stringID = getResources().getResourceName(idButton);
//        int intId = getIntId(stringID);
//        int [] arrayIndex = getIndex(intId);
        return checkNeighbors(button);

    }
}
