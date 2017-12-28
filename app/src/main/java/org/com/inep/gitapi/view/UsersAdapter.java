package org.com.inep.gitapi.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.com.inep.gitapi.R;
import org.com.inep.gitapi.model.User;

import java.util.List;

/**
 * Created by Shiki on 27/12/2017.
 */
public class UsersAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context context;

    private List<User> users;

    public UsersAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    /** ***************************************************** */
    /** ********************* OVERRRIDE ********************* */
    /** ***************************************************** */
    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int index) {
        return users.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {

        User user = users.get(index);

        View result;
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.listview_user, parent, false);

            viewHolder.userLogin = (TextView) convertView.findViewById(R.id.elementTextViewUserLogin);
            viewHolder.userURL = (TextView) convertView.findViewById(R.id.elementTextViewUserURL);
            viewHolder.userRepo = (TextView) convertView.findViewById(R.id.elementTextViewUserRepo);
            viewHolder.userIcon = (ImageView) convertView.findViewById(R.id.elementImageViewUser);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            result = convertView;
        }
        viewHolder.userLogin.setText(user.getLogin());
        viewHolder.userURL.setText(user.getUrlHTML());
        viewHolder.userRepo.setText(user.getUrlRepositories());
        viewHolder.userIcon.setTag(index);

        Picasso.with(context).load(user.getUrlAvatar()).fit()
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(viewHolder.userIcon);

        return convertView;
    }

    /** ***************************************************** */
    /** *********************** EVENTs ********************** */
    /** ***************************************************** */
//    @Override
//    public void onClick(View v) {
//        int position = (Integer) v.getTag();
//
//        Object object = getItem(position);
//
//        User dataModel = (User) object;
//
//        Toast.makeText(context, String.valueOf(dataModel.getLogin()), Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
        Object object = getItem(index);

        User user = (User) object;

        Intent intent = new Intent(context, UserActivity.class);

        intent.putExtra("param_user_login", user.getLogin());

        context.startActivity(intent);
    }


    /** ***************************************************** */
    /** ********************** GETTERs ********************** */
    /** ***************************************************** */
    public Context getContext() {
        return context;
    }

    public List<User> getUsers() {
        return users;
    }

    /** ***************************************************** */
    /** ********************** SETTERs ********************** */
    /** ***************************************************** */
    public void setContext(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /** ***************************************************** */
    /** *********************** CLASS *********************** */
    /** ***************************************************** */
    public static class ViewHolder {
        public TextView userLogin;
        public TextView userURL;
        public TextView userRepo;
        public ImageView userIcon;
    }

}
