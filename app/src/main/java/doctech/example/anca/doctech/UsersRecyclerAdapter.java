package doctech.example.anca.doctech;

/**
 * Created by Anca on 12/2/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;
    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);


        return new UserViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewPassword.setText(listUsers.get(position).getPassword());
        holder.textViewRole.setText(listUsers.get(position).getRole());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }



    /**
     * ViewHolder class
     */
    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;
        public AppCompatTextView textViewRole;

        public UserViewHolder(final View view) {
            super(view);
            context = view.getContext();
            textViewName = view.findViewById(R.id.textViewName);
            textViewEmail = view.findViewById(R.id.textViewEmail);
            textViewPassword = view.findViewById(R.id.textViewPassword);
            textViewRole = view.findViewById(R.id.textViewRole);
            view.setClickable(true);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            final Intent intent;
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            List<Patient> patients = new ArrayList<>();
            patients.addAll(databaseHelper.getPatient());

            if(textViewRole.getText().equals("patient")){
                intent = new Intent(context , PatientProfileActivity.class);
                intent.putExtra("NAME", textViewName.getText().toString());
                intent.putExtra("EMAIL", textViewEmail.getText().toString());
                intent.putExtra("ADDRESS", patients.get(0).getAddress().toString());
                intent.putExtra("CNP", patients.get(0).getCnp().toString());
                intent.putExtra("PASSWORD", patients.get(0).getPassword().toString());
                intent.putExtra("ID", String.valueOf(patients.get(0).getId()));
                context.startActivity(intent);
            }else{
                intent = new Intent(context , DoctorsProfileActivity.class);
                context.startActivity(intent);
            }



        }
    }


}