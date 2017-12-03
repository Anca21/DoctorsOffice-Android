package doctech.example.anca.doctech;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutAddress;
    private TextInputLayout textInputLayoutCnp;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;

    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private TextInputEditText textInputEditTextAddress;
    private TextInputEditText textInputEditTextCnp;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;
    private Patient patient;
    private NestedScrollView nestedScrollView;

    private AppCompatButton appCompatButtonAddPatient;
    private final AppCompatActivity activity = AddUserActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        initViews();
        initListeners();
        initObjects();
    }


    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutAddress = (TextInputLayout) findViewById(R.id.textInputLayoutAddress);
        textInputLayoutCnp = (TextInputLayout) findViewById(R.id.textInputLayoutCnp);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextAddress = (TextInputEditText) findViewById(R.id.textInputEditTextAddress);
        textInputEditTextCnp = (TextInputEditText) findViewById(R.id.textInputEditTextCnp);

        appCompatButtonAddPatient = (AppCompatButton) findViewById(R.id.appCompatButtonAddPatient);


    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonAddPatient.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        patient = new Patient();

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonAddPatient:
                postDataToSQLite();
                //sendEmail();
                break;

        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAddress, textInputLayoutAddress, getString(R.string.error_message_address))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextCnp, textInputLayoutCnp, getString(R.string.error_message_cnp))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            patient.setName(textInputEditTextName.getText().toString().trim());
            patient.setEmail(textInputEditTextEmail.getText().toString().trim());
            patient.setPassword(textInputEditTextPassword.getText().toString().trim());
            patient.setAddress(textInputEditTextAddress.getText().toString().trim());
            patient.setCnp(textInputEditTextCnp.getText().toString().trim());


            databaseHelper.addPatient(patient);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            sendEmail();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditTextAddress.setText(null);
        textInputEditTextCnp.setText(null);
    }

    public void sendEmail() {
        Log.i("Send email", "");
        String TO = String.valueOf("anca_rusu01@yahoo.com");
        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {patient.getEmail()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Registry confirmation");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi," +patient.getName() + "\n\nYou have a new account on DocTech platform.\n"
                + "\nYour credentials are:\n"
                + "\n CNP:  " + patient.getCnp()
                + "\n Address: " + patient.getAddress()
                + "\n Password: " + patient.getPassword()
                +"\n\n\nRegards,\nDocTech team");


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AddUserActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

//        finish();
    }
}

