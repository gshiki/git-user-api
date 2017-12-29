package org.com.inep.gitusers.view;

import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;

/**
 * User
 */
public class UserActivity extends AppCompatActivity {

    @NonNull
    private String userLogin;

    @NonNull
    private Unbinder unbinder;

    @NonNull
    @BindView(R.id.elementUserTextViewName)
    TextView textViewUserName;

    @NonNull
    @BindView(R.id.elementUserTextViewLogin)
    TextView textViewLogin;

    @NonNull
    @BindView(R.id.elementUserImage)
    ImageView imageViewUser;

    @NonNull
    @BindView(R.id.elementUserTextViewUserBio)
    TextView textViewUserBiography;

    @NonNull
    @BindView(R.id.elementUserTextViewUserCompany)
    TextView textViewUserCompany;

    @NonNull
    @BindView(R.id.elementUserTextViewUserLocation)
    TextView textViewUserLocation;

    @NonNull
    @BindView(R.id.elementUserTextViewCountRepositories)
    TextView textViewCountRepositories;

    @NonNull
    @BindView(R.id.elementUserTextViewNoRepositories)
    TextView textViewNoRepositories;

    @NonNull
    @BindView(R.id.elementUserLinearLayoutRepositories)
    LinearLayout linearLayoutRepositories;


    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_user);

        Intent intent = getIntent();

        this.userLogin = intent.getStringExtra(MyConstants.PARAM_ACTIVITY_USER_LOGIN);

        ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();

        Loader.show(UserActivity.this);

        setUserInformation();

        setUserRepositories();

        Loader.close();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
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
            if (linearLayoutRepositories != null) {
                RepositoryController repositoryController = new RepositoryController() {

                    @Override
                    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                        if(response.isSuccessful()) {
                            List<Repository> repositories = response.body();
//
                            if (repositories != null) {
                                for (Repository repository : repositories) {
                                    LayoutInflater inflater = getLayoutInflater();

                                    View view = inflater.inflate(R.layout.listview_item_repository, null);

                                    setUserRepositoryInformations(view, repository);

                                    linearLayoutRepositories.addView(view);
                                }
                                textViewNoRepositories.setVisibility(View.GONE);
                                linearLayoutRepositories.setVisibility(View.VISIBLE);
                            } else {
                                textViewNoRepositories.setVisibility(View.VISIBLE);
                                linearLayoutRepositories.setVisibility(View.GONE);

                                Toast.makeText(getApplicationContext(), R.string.error_request, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.error_request, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repository>> call, Throwable t) {
                        super.onFailure(call, t);
                    }

                };
                repositoryController.requestRepositories(userLogin);
            }
        }
    }

    private void setUserRepositoryInformations(View view, Repository repository) {
        ImageView imageViewLock = (ImageView) view.findViewById(R.id.elementItemRepositoryImageLock);
        ImageView imageViewUnlock = (ImageView) view.findViewById(R.id.elementItemRepositoryImageUnlock);
        TextView textViewName = (TextView) view.findViewById(R.id.elementItemRepositoryTextViewName);
        TextView textViewLanguage = (TextView) view.findViewById(R.id.elementItemRepositoryTextViewLanguage);
        TextView textViewDescription = (TextView) view.findViewById(R.id.elementItemRepositorytextViewDescription);

        if (repository.isPrivate()) {
            imageViewLock.setVisibility(View.VISIBLE);
            imageViewUnlock.setVisibility(View.GONE);
        } else {
            imageViewLock.setVisibility(View.GONE);
            imageViewUnlock.setVisibility(View.VISIBLE);
        }
        textViewName.setText(repository.getFullName());
        textViewLanguage.setText( (!repository.getLanguage().isEmpty() ? repository.getLanguage() : getString(R.string.ui_label_no_value) ) );
        textViewDescription.setText( (!repository.getDescription().isEmpty() ? repository.getDescription() : getString(R.string.ui_label_no_value) ) );
    }

}
