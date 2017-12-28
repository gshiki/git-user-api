package org.com.inep.gitapi.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.com.inep.gitapi.R;
import org.com.inep.gitapi.model.User;
import org.com.inep.gitapi.util.MyConstants;
import org.com.inep.gitapi.view.UserActivity;

import java.util.List;

/**
 * Created by Shiki on 27/12/2017.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private Context context;

    private RecyclerView recyclerViewUsers;

    private List<User> users;

    public UsersAdapter(Context context, RecyclerView recyclerViewUsers, List<User> users) {
        this.context = context;
        this.recyclerViewUsers = recyclerViewUsers;
        this.users = users;
    }

    /** *********************** HOLDER *********************** */
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserLogin;
        TextView textViewUserURL;
        TextView textViewUserRepo;
        ImageView imageViewUserIcon;

        MyViewHolder(View view) {
            super(view);

            textViewUserLogin = view.findViewById(R.id.elementItemUserTextLogin);
            textViewUserURL = view.findViewById(R.id.elementItemUserTextURL);
            textViewUserRepo = view.findViewById(R.id.elementItemUserTextRepo);
            imageViewUserIcon = view.findViewById(R.id.elementItemUserImage);
        }
    }

    /** ********************* OVERRRIDE ********************* */
    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = users.get(position);

        holder.textViewUserLogin.setText(user.getLogin());
        holder.textViewUserURL.setText(user.getUrlHTML());
        holder.textViewUserRepo.setText(user.getUrlRepositories());

        Picasso.with(context).load(user.getUrlAvatar()).fit()
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(holder.imageViewUserIcon);
    }

    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_user, parent, false);

        v.setOnClickListener(new MyOnClickListener());

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    /** *********************** EVENTs ********************** */
    public class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int position = recyclerViewUsers.getChildAdapterPosition(view);

            User user = users.get(position);

            Intent intent = new Intent(context, UserActivity.class);

            intent.putExtra(MyConstants.PARAM_ACTIVITY_USER_LOGIN, user.getLogin());

            context.startActivity(intent);
        }
    }


    /** ********************** GETTERs ********************** */
    public Context getContext() {
        return context;
    }

    public List<User> getUsers() {
        return users;
    }

    /** ********************** SETTERs ********************** */
    public void setContext(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



}
