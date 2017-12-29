package org.com.inep.gitusers.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.com.inep.gitusers.R;
import org.com.inep.gitusers.model.User;
import org.com.inep.gitusers.util.MyConstants;
import org.com.inep.gitusers.view.UserActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shiki on 27/12/2017.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private Context context;

    private List<User> users;

    private RecyclerView recyclerViewUsers;

    public UsersAdapter(Context context, RecyclerView recyclerViewUsers, List<User> users) {
        this.users = users;
        this.context = context;
        this.recyclerViewUsers = recyclerViewUsers;
    }

    /** *********************** HOLDER *********************** */
    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.elementItemUserTextLogin) TextView textViewUserLogin;
        @BindView(R.id.elementItemUserTextURL) TextView textViewUserURL;
        @BindView(R.id.elementItemUserImage) ImageView imageViewUserIcon;

        MyViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
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

        Picasso.with(context).load(user.getUrlAvatar()).fit()
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(holder.imageViewUserIcon);
    }

    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_user, parent, false);

        view.setOnClickListener(new MyOnClickListener());

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    /** *********************** EVENTs ********************** */
    public class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int position = recyclerViewUsers.getChildAdapterPosition(view);

            User user = users.get(position);

            Intent intent = new Intent(context, UserActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra(MyConstants.PARAM_ACTIVITY_USER_LOGIN, user.getLogin());

            context.startActivity(intent);
        }
    }
}
