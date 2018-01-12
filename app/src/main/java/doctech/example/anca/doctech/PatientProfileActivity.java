package doctech.example.anca.doctech;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class PatientProfileActivity extends AppCompatActivity {

    TextInputEditText textInputEditTextEmail1;
    TextInputEditText textInputEditTextCnp1;
    TextInputEditText textInputEditTextAddress1;
    TextInputEditText textInputEditTextName1;


    TextInputLayout textInputLayoutName1;
    TextInputLayout textInputLayoutEmail1;
    TextInputLayout textInputLayoutCnp1;
    TextInputLayout textInputLayoutAddress1;
    TextView textView1;
//    Intent intent = getIntent();

    AppCompatButton appCompatButtonEdit1;
    NestedScrollView nestedScrollView1;
    Patient p = new Patient();


    @Override
    @Nullable
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        initPatient();
        initViews();

        final Button button = (Button) findViewById(R.id.appCompatButtonEdit1);
        button.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {

            DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
            Log.d("DBHELPNER   ", String.valueOf(databaseHelper));
            editPatient(databaseHelper);
            Intent intent = new Intent();
            setResult(RESULT_OK);
            finish();
        }
    });

    }






    /**
     * This method is to initialize listeners
     */
    private void initPatient() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        p.setId(Integer.valueOf(extras.getString("ID")));
        p.setCnp(extras.getString("CNP"));
        p.setName(extras.getString("NAME"));
        p.setEmail(extras.getString("EMAIL"));
        p.setPassword(extras.getString("PASSWORD"));
        p.setAddress(extras.getString("ADDRESS"));
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {


        nestedScrollView1 = (NestedScrollView) findViewById(R.id.nestedScrollView1);

        textInputLayoutName1 = (TextInputLayout) findViewById(R.id.textInputLayoutName1);

        textInputLayoutEmail1 = (TextInputLayout) findViewById(R.id.textInputLayoutEmail1);
        textInputLayoutCnp1 = (TextInputLayout) findViewById(R.id.textInputLayoutCnp1);
        textInputLayoutAddress1 = (TextInputLayout) findViewById(R.id.textInputLayoutAddress1);


        textInputEditTextName1 = (TextInputEditText) findViewById(R.id.textInputEditTextName1);
        textInputEditTextName1.setText(p.getName());
        textInputEditTextEmail1 = (TextInputEditText) findViewById(R.id.textInputEditTextEmail1);
        textInputEditTextEmail1.setText(p.getEmail());
        textInputEditTextCnp1 = (TextInputEditText) findViewById(R.id.textInputEditTextCnp1);
        textInputEditTextCnp1.setText(p.getCnp());
        textInputEditTextAddress1 = (TextInputEditText) findViewById(R.id.textInputEditTextAddress1);
        textInputEditTextAddress1.setText(p.getAddress());

        appCompatButtonEdit1 = (AppCompatButton) findViewById(R.id.appCompatButtonEdit1);

    }


    public void editPatient(DatabaseHelper db){
        p.setName(textInputEditTextName1.getText().toString().trim());
        p.setEmail(textInputEditTextEmail1.getText().toString().trim());
        p.setCnp(textInputEditTextCnp1.getText().toString().trim());
        p.setAddress(textInputEditTextAddress1.getText().toString().trim());

        Log.d("PATIENT  ", p.toString());
        Log.d("ALLPATIENTS ", String.valueOf(db.getPatient().size()));
        db.updatePatient(p);
        List<Patient> patientList = db.getPatient();
        Log.d("THE PATIENT", String.valueOf(patientList.contains(p)));
    }


//    @Override
//    public void onClick(View v) {
//        DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
//        Log.d("DBHELPNER   ", String.valueOf(databaseHelper));
//        editPatient(databaseHelper);
//    }
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