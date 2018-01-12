package doctech.example.anca.doctech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anca on 12/2/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";
    // Patient table
    private static final String TABLE_PATIENT = "patient";
    private static final String TABLE_DIAGNOSIS = "diagnosis_id";
    private static final String TABLE_PATIENT_DIAGNOSIS = "patient_diagnosis";
    private static final String TABLE_CONSULT = "consult";
    private static final String TABLE_PATIENT_TREATMENT = "patient_treatment";


    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_ROLE = "user_role";

    // Patient Table Columns
    private static final String COLUMN_PATIENT_ID = "patient_id";
    private static final String COLUMN_PATIENT_NAME = "patient_name";
    private static final String COLUMN_PATIENT_EMAIL = "patient_email";
    private static final String COLUMN_PATIENT_PASSWORD = "patient_password";
    private static final String COLUMN_PATIENT_CNP = "patient_cnp";
    private static final String COLUMN_PATIENT_ADDRESS = "patient_address";

    // Patient_diagnosis Table Columns
    private static final String COLUMN_P_D_ID = "p_d_id";
    private static final String COLUMN_P_DIAGNOSIS_ID = "p_diagnosis_id";
    private static final String COLUMN_PATIENT_D_ID = "patient_d_id";

    // Diagnosis Table Columns
    private static final String COLUMN_DIAGNOSIS_ID = "diagnosis_id";
    private static final String COLUMN_DIAGNOSIS_NAME = "diagnosis_name";
    private static final String COLUMN_DIAGNOSIS_DESCRIPTION = "diagnosis_description";

    // Consult Table Columns
    private static final String COLUMN_CONSULT_ID= "consult_id";
    private static final String COLUMN_C_PATIENT_ID= "c_patient_id";
    private static final String COLUMN_DATETIME= "datetime_id";

    // Patient Treatment Columns
    private static final String COLUMN_P_T_ID = "p_t_id";
    private static final String COLUMN_PATIENT_T_ID = "patient_t_id";
    private static final String COLUMN_TREATMENT_DESCRIPTION = "treatment_description";



    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_ROLE + " TEXT" + ")";
    private String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
            + COLUMN_PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PATIENT_NAME+ " TEXT,"
            + COLUMN_PATIENT_EMAIL + " TEXT," + COLUMN_PATIENT_PASSWORD + " TEXT," + COLUMN_PATIENT_ADDRESS  +
            " TEXT," + COLUMN_PATIENT_CNP   +  " TEXT" + ")";
    private String CREATE_DIAGNOSIS_TABLE = "CREATE TABLE " + TABLE_DIAGNOSIS + "("
            + COLUMN_DIAGNOSIS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DIAGNOSIS_NAME + " TEXT,"
            + COLUMN_DIAGNOSIS_DESCRIPTION + " TEXT" + ")";
    private String CREATE_PATIENT_DIAGNOSIS_TABLE = "CREATE TABLE " + TABLE_PATIENT_DIAGNOSIS + "("
            + COLUMN_P_D_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,FOREIGN KEY(" + COLUMN_P_DIAGNOSIS_ID + ") REFERENCES "
            + TABLE_DIAGNOSIS + "(" + COLUMN_DIAGNOSIS_ID +"),FOREIGN KEY(" + COLUMN_PATIENT_D_ID + ") REFERENCES "
            + TABLE_PATIENT + "(" + COLUMN_PATIENT_ID + ")" + ")";
    private String CREATE_CONSULT_TABLE = "CREATE TABLE " + TABLE_CONSULT + "("
            + COLUMN_CONSULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,FOREIGN KEY(" + COLUMN_C_PATIENT_ID + ") REFERENCES "
            + TABLE_PATIENT + "(" + COLUMN_PATIENT_ID + ")," + COLUMN_DATETIME + "DATETIME"+ ")";
    private String CREATE_PATIENT_TREATMENT = "CREATE TABLE " + TABLE_PATIENT_TREATMENT + "("
            + COLUMN_P_T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,FOREIGN KEY(" + COLUMN_PATIENT_T_ID + ") REFERENCES "
            + TABLE_PATIENT + "(" + COLUMN_PATIENT_ID+ ")," + COLUMN_TREATMENT_DESCRIPTION + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PATIENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PATIENT;
    private String DROP_PATIENT_TREATMENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PATIENT_TREATMENT;
    private String DROP_DIAGNOSIS_TABLE = "DROP TABLE IF EXISTS " + TABLE_DIAGNOSIS;
    private String DROP_CONSULT_TABLE = "DROP TABLE IF EXISTS " + TABLE_CONSULT;
    private String DROP_PATIENT_DIAGNOSIS_TABLE = "DROP TABLE IF EXISTS " + TABLE_PATIENT_DIAGNOSIS;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PATIENT_TABLE);
//        db.execSQL(CREATE_DIAGNOSIS_TABLE);
//        db.execSQL(CREATE_PATIENT_DIAGNOSIS_TABLE);
//        db.execSQL(CREATE_CONSULT_TABLE);
//        db.execSQL(CREATE_PATIENT_TREATMENT);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
//        db.execSQL(DROP_PATIENT_TREATMENT_TABLE);
//        db.execSQL(DROP_PATIENT_DIAGNOSIS_TABLE);
//        db.execSQL(DROP_CONSULT_TABLE);
//        db.execSQL(DROP_DIAGNOSIS_TABLE);
        db.execSQL(DROP_PATIENT_TABLE);
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_ROLE, user.getRole());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_NAME, patient.getName());
        values.put(COLUMN_PATIENT_EMAIL, patient.getEmail());
        values.put(COLUMN_PATIENT_PASSWORD, patient.getPassword());
        values.put(COLUMN_PATIENT_ADDRESS, patient.getAddress());
        values.put(COLUMN_PATIENT_CNP, patient.getCnp());

        User user = new User();
        user.setRole("patient");
        user.setEmail(patient.getEmail());
        user.setName(patient.getName());
        user.setPassword(patient.getPassword());

        // Inserting Row
        db.insert(TABLE_PATIENT, null, values);
        addUser(user);
        db.close();
    }

    List<Patient> getPatient(){
        SQLiteDatabase db = this.getReadableDatabase();

        String sortOrder =
                COLUMN_PATIENT_NAME + " ASC";
        String[] columns = {
                COLUMN_PATIENT_ID,
                COLUMN_PATIENT_NAME,
                COLUMN_PATIENT_EMAIL,
                COLUMN_PATIENT_PASSWORD,
                COLUMN_PATIENT_ADDRESS,
                COLUMN_PATIENT_CNP
        };
        Cursor cursor = db.rawQuery("SELECT * FROM patient", null);
//        Cursor cursor = db.rawQuery("SELECT * FROM patient WHERE TRIM(patient_name) = '"+name.trim()+"' AND TRIM(patient_email) = '"+email.trim()+"'" , null);
//        Cursor cursor = db.query(TABLE_PATIENT, //Table to query
//                columns,    //columns to return
//                null,        //columns for the WHERE clause
//                null,        //The values for the WHERE clause
//                null,       //group the rows
//                null,       //filter by row groups
//                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        List<Patient> patients = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Patient p = new Patient();
                p.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID))));
                p.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_NAME)));
                p.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_EMAIL)));
                p.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_PASSWORD)));
                p.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ADDRESS)));
                p.setCnp(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_CNP)));
                // Adding user record to list
                patients.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return patients;


    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_ROLE
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setRole(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROLE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_ROLE, user.getRole());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_EMAIL + " = ?",
                new String[]{String.valueOf(user.getEmail())});
        db.close();
    }

    public void updatePatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_NAME, patient.getName());
        values.put(COLUMN_PATIENT_EMAIL, patient.getEmail());
        values.put(COLUMN_PATIENT_ADDRESS, patient.getAddress());
        values.put(COLUMN_PATIENT_CNP, patient.getCnp());
        Log.d("CONTENTVALUES  ", String.valueOf(values));
        User u = new User();
        u.setId(patient.getId());
        u.setPassword(patient.getPassword());
        u.setName(patient.getName());
        u.setEmail(patient.getEmail());
        u.setRole("patient");


        // updating row
        db.update(TABLE_PATIENT, values, COLUMN_PATIENT_EMAIL + " = ?",
                new String[]{String.valueOf(patient.getEmail())});

        updateUser(u);
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUserRole(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_ROLE + " = ?";

        // selection arguments
        String[] selectionArgs = {email, "doctor".trim()};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}