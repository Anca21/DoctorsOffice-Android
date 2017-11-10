package doctech.example.anca.doctech;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.button1);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.email);
                editText.getText().toString();
                sendEmail();
            }
        });
    }

    public void sendEmail() {
        Log.i("Send email", "");
        String TO = String.valueOf("anca_rusu01@yahoo.com");
        EditText editText = (EditText)findViewById(R.id.email);
        EditText editText1 = (EditText)findViewById(R.id.name);
        EditText editText2 = (EditText)findViewById(R.id.username);
        EditText editText3 = (EditText)findViewById(R.id.cnp);
        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {editText.getText().toString()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Registry confirmation");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi," +editText1.getText().toString() + "\n\nThanks for registering on DocTech platform.\n"
                + "Please confirm the correction of your personal data:\n"
                + "Name: " + editText1.getText().toString()
                + "\nUsername: " + editText2.getText().toString()
                + "\nCNP: " + editText3.getText().toString()
                +"\n\n\nRegards,\nDocTech team");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

//        finish();
    }

}
