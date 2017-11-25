package doctech.example.anca.doctech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        String message = intent.getStringExtra("username");
        String message1 = intent.getStringExtra("name");
        String message2 = intent.getStringExtra("email");

        EditText textView = (EditText) findViewById(R.id.username);
        EditText textView1 = (EditText) findViewById(R.id.name);
        EditText textView2 = (EditText) findViewById(R.id.email);
        textView.setText(message);
        textView1.setText(message1);
        textView2.setText(message2);
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
