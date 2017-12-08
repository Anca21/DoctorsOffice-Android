package doctech.example.anca.doctech;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PatientProfileActivity extends AppCompatActivity {

    TextInputEditText textInputEditTextEmail;
    TextInputEditText textInputEditTextCnp;
    TextInputEditText textInputEditTextAddress;
    TextInputEditText textInputEditTextName;


    TextInputLayout textInputLayoutName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutCnp;
    TextInputLayout textInputLayoutAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        initViews();

        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }



    /**
     * This method is to initialize views
     */
    private void initViews() {

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutCnp = (TextInputLayout) findViewById(R.id.textInputLayoutCnp);
        textInputLayoutAddress = (TextInputLayout) findViewById(R.id.textInputLayoutAddress);


        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextCnp = (TextInputEditText) findViewById(R.id.textInputEditTextCnp);
        textInputEditTextAddress = (TextInputEditText) findViewById(R.id.textInputEditTextAddress);
    }



}





//package doctech.example.anca.doctech;
//
//        import android.support.design.widget.TextInputEditText;
//        import android.support.design.widget.TextInputLayout;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.support.v7.widget.AppCompatTextView;
//        import android.view.View;
//        import android.widget.Button;
//
//public class PatientProfileActivity extends AppCompatActivity {
//
//    TextInputEditText textInputEditTextEmail;
//    TextInputEditText textInputEditTextCnp;
//    TextInputEditText textInputEditTextAddress;
//    TextInputEditText textInputEditTextName;
//
//
//    TextInputLayout textInputLayoutName;
//    TextInputLayout textInputLayoutEmail;
//    TextInputLayout textInputLayoutCnp;
//    TextInputLayout textInputLayoutAddress;
//
//    DatabaseHelper databaseHelper;
//
//    Patient p;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_user);
//
//        //      initViews();
////
////        final Button button = (Button) findViewById(R.id.appCompatButtonEdit);
////        button.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////            //    databaseHelper.updatePatient(p);
////            }
////        });
//    }
//
//
//
//    /**
//     * This method is to initialize views
//     */
//    private void initViews() {
//
////        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName1);
////        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail1);
////        textInputLayoutCnp = (TextInputLayout) findViewById(R.id.textInputLayoutCnp1);
////        textInputLayoutAddress = (TextInputLayout) findViewById(R.id.textInputLayoutAddress1);
//
//
////        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName1);
////        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail1);
////        textInputEditTextCnp = (TextInputEditText) findViewById(R.id.textInputEditTextCnp1);
////        textInputEditTextAddress = (TextInputEditText) findViewById(R.id.textInputEditTextAddress1);
//
////        p.setName(textInputEditTextName.getText().toString());
////        p.setEmail(textInputEditTextEmail.getText().toString());
////        p.setCnp(textInputEditTextCnp.getText().toString());
////        p.setAddress(textInputEditTextAddress.getText().toString());
//    }
//
//
//
//}