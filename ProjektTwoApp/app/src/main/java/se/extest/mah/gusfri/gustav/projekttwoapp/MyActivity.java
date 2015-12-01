package se.extest.mah.gusfri.gustav.projekttwoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MyActivity extends Activity {

    private Connection connection;

    private TextView tvRandomWord;
    private Button btnGetWord;
    private EditText etTranslate;
    private Button btnTranslate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        init();
//        connectToServer("100");
    }

    private void init() {
        tvRandomWord = (TextView)findViewById(R.id.textViewInfo);
        btnGetWord = (Button)findViewById(R.id.buttonGetWord);
        btnTranslate = (Button)findViewById(R.id.buttonTranslate);
        etTranslate = (EditText)findViewById(R.id.editTextTranslate);
        btnTranslate.setOnClickListener(listener);
        btnGetWord.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.buttonGetWord) {
                String str = "https://theshub.p.mashape.com/random?language=sv";
                connectToServer(str);
            } if(v.getId() == R.id.buttonTranslate) {
                String word = etTranslate.getText().toString();
                connectToServer("https://theshub.p.mashape.com/translate?from=sv&to=en&word=" + word);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void connectToServer(String str){
        connection = new Connection(str, this);
//        connection = new Connection("http://www.vantetider.se/api/Ajax/GetServicesByArea/2", this);
//        connection = new Connection("http://www.vantetider.se/api/Ajax/GetWaitingAndCapacityByService/42", this);

        connection.addEventListener(new Connection.ConnectionListener() {
            public void myEventOccurred(ConnectionEvent evt) {
                switch (evt.type) {

                    case ConnectionEvent.INCOMMING_MESSAGE:
                        setRandomWord(evt.message);
                        break;

                    default:



                        break;
                }
            }
        });
    }

    private void setRandomWord(String str) {
        tvRandomWord.setText(str);
    }

}
