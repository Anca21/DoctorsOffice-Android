package doctech.example.anca.doctech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Anca on 11/10/2017.
 */

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.cnp);
        textView.setText(message);
//        final Button edit = (Button) findViewById(R.id.button3);
//        edit.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                edit();
//            }
//        });
    }

    public void edit(){
        EditText editText = (EditText)findViewById(R.id.email);
        EditText editText1 = (EditText)findViewById(R.id.name);
        EditText editText2 = (EditText)findViewById(R.id.username);
        EditText editText3 = (EditText)findViewById(R.id.cnp);

        String name = editText1.getText().toString();
        String email = editText.getText().toString();
        String username = editText2.getText().toString();
        String cnp = editText3.getText().toString();

        editText.setText(email);
        editText1.setText(name);
        editText2.setText(username);
        editText3.setText(cnp);


    }

}
