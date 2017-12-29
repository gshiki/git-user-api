package org.com.inep.gitusers.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.com.inep.gitusers.R;
import org.com.inep.gitusers.control.UsersController;
import org.com.inep.gitusers.model.UserList;
import org.com.inep.gitusers.util.Loader;
import org.com.inep.gitusers.view.adapter.UsersAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Main
 */
public class MainActivity extends Activity {

    @NonNull
    @BindView(R.id.elementMainSearchViewUsers)
    SearchView searchViewUsers;

    @NonNull
    @BindView(R.id.elementMainRecyclerView)
    RecyclerView recyclerViewUsers;

    @NonNull
    @BindView(R.id.elementMainTextViewEmpty)
    TextView textViewEmpty;

    @NonNull
    LinearLayoutManager linearLayoutManager;

    @NonNull
    private Unbinder unbinder;


    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();

        setSearchViewConfig();

        setRecyclerViewConfig();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

    private void setSearchViewConfig() {
        searchViewUsers.setQueryHint(getString(R.string.ui_placeholder_search_user));

        searchViewUsers.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                if (!text.isEmpty()) {
                    Loader.show(MainActivity.this);

                    UsersController usersController = new UsersController() {
                        @Override
                        public void onResponse(Call<UserList> call, Response<UserList> response) {
                            if(response.isSuccessful()) {
                                UserList userList = response.body();

                                if (userList.getCount() > 0) {
                                    UsersAdapter usersAdapter = new UsersAdapter(getApplicationContext(), recyclerViewUsers, userList.getUsers());

                                    recyclerViewUsers.addItemDecoration(new DividerItemDecoration(recyclerViewUsers.getContext(), linearLayoutManager.getOrientation()));
                                    recyclerViewUsers.setAdapter(usersAdapter);
                                    recyclerViewUsers.setVisibility(View.VISIBLE);

                                    textViewEmpty.setVisibility(View.GONE);
                                } else {
                                    recyclerViewUsers.setVisibility(View.GONE);

                                    textViewEmpty.setVisibility(View.VISIBLE);
                                    textViewEmpty.setText(getString(R.string.error_request_empty));
                                }
                                Loader.close();
                            } else {
                                Toast.makeText(getApplicationContext(), R.string.error_request, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserList> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    };
                    usersController.searchUsers(text);

                } else {
                    textViewEmpty.setVisibility(View.VISIBLE);
                    textViewEmpty.setText(getString(R.string.ui_text_no_search));

                    Toast.makeText(getApplicationContext(), R.string.msg_empty_search, Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                if (text.isEmpty()) {
                    recyclerViewUsers.setVisibility(View.GONE);

                    textViewEmpty.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }

    private void setRecyclerViewConfig() {
        recyclerViewUsers.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerViewUsers.setLayoutManager(linearLayoutManager);
    }

    /** **************** EVENTOS DE ELEMENTOS *************** */
    @OnClick(R.id.elementMainSearchViewUsers)
    public void focusSearchView(View view) {
        if (searchViewUsers != null)
            searchViewUsers.setIconified(false);
    }

}
