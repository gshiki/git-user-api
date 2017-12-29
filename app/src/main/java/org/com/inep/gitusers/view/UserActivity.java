package org.com.inep.gitusers.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.com.inep.gitusers.R;
import org.com.inep.gitusers.control.RepositoryController;
import org.com.inep.gitusers.control.UserController;
import org.com.inep.gitusers.model.Repository;
import org.com.inep.gitusers.model.User;
import org.com.inep.gitusers.util.Loader;
import org.com.inep.gitusers.util.MyConstants;
import org.com.inep.gitusers.view.adapter.RepositoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * User
 */
public class UserActivity extends AppCompatActivity {

    private String userLogin;

    @BindView(R.id.elementUserTextViewName)
    TextView textViewUserName;

    @BindView(R.id.elementUserTextViewLogin)
    TextView textViewLogin;

    @BindView(R.id.elementUserImage)
    ImageView imageViewUser;

    @BindView(R.id.elementUserTextViewUserBio)
    TextView textViewUserBiography;

    @BindView(R.id.elementUserTextViewUserCompany)
    TextView textViewUserCompany;

    @BindView(R.id.elementUserTextViewUserLocation)
    TextView textViewUserLocation;

    @BindView(R.id.elementUserTextViewCountRepositories)
    TextView textViewCountRepositories;

    @BindView(R.id.elementUserListViewRepositories)
    ListView listViewRepositories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_user);

        Intent intent = getIntent();

        this.userLogin = intent.getStringExtra(MyConstants.PARAM_ACTIVITY_USER_LOGIN);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Loader.show(UserActivity.this);

        setUserInformation();

        setUserRepositories();

        Loader.close();
    }

    private void setUserInformation() {
        if (userLogin != null
                && textViewUserName != null
                && textViewLogin != null
                && imageViewUser != null
                && textViewUserBiography != null
                && textViewUserCompany != null
                && textViewUserLocation != null
                && textViewCountRepositories != null)
        {
            UserController userController = new UserController(){
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        User user = response.body();

                        if (user != null) {
                            textViewUserName.setText( (!user.getName().isEmpty() ? user.getName() : getString(R.string.ui_label_no_value)) );
                            textViewLogin.setText( (!user.getLogin().isEmpty() ? user.getLogin() : getString(R.string.ui_label_no_value)) );
                            textViewUserBiography.setText( (!user.getBiography().isEmpty() ? user.getBiography() : getString(R.string.ui_label_no_value)) );
                            textViewUserCompany.setText( (!user.getCompany().isEmpty() ? user.getCompany() : getString(R.string.ui_label_no_value)) );
                            textViewUserLocation.setText( (!user.getLocation().isEmpty() ? user.getLocation() : getString(R.string.ui_label_no_value)) );
                            textViewCountRepositories.setText( getString(R.string.ui_label_repositories) + " : " + user.getCountRepositories() );

                            if (!user.getUrlAvatar().isEmpty()) {
                                Picasso.with(getApplicationContext()).load(user.getUrlAvatar()).fit()
                                        .placeholder(R.drawable.github)
                                        .error(R.drawable.github)
                                        .into(imageViewUser);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.error_request, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.error_request, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };
            userController.searchUser(userLogin);
        }
    }

    private void setUserRepositories() {
        if (userLogin != null) {
            if (listViewRepositories != null) {

                RepositoryController repositoryController = new RepositoryController() {
                    @Override
                    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                        if(response.isSuccessful()) {
                            List<Repository> repositories = response.body();

                            if (repositories != null) {
                                RepositoryAdapter repositoryAdapter = new RepositoryAdapter(getApplicationContext(), repositories);

                                listViewRepositories.setAdapter(repositoryAdapter);

                                repositoryAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getApplicationContext(), R.string.error_request, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.error_request, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repository>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                repositoryController.requestRepositories(userLogin);
            }
        }
    }

}
