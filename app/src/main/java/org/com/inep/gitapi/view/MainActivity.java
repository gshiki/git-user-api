package org.com.inep.gitapi.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.com.inep.gitapi.R;
import org.com.inep.gitapi.control.UserController;
import org.com.inep.gitapi.model.UserList;
import org.com.inep.gitapi.util.Loader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Main
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.elementInputSearch)
    TextView inputSearch;

    @BindView(R.id.elementListView)
    ListView listViewUsers;

    @BindView(R.id.elementTextViewInitial)
    TextView textViewNoResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    /** ***************************************************** */
    /** **************** EVENTOS DE ELEMENTOS *************** */
    /** ***************************************************** */
    @OnClick(R.id.elementButtonSearch)
    public void searchUsers(View view) {
        if (inputSearch.getText().length() > 0) {
            Loader.show(MainActivity.this);

            //Toast.makeText(getApplicationContext(), inputSearch.getText().toString(), Toast.LENGTH_LONG).show();

            UserController userController = new UserController() {
                @Override
                public void onResponse(Call<UserList> call, Response<UserList> response) {
                    if(response.isSuccessful()) {
                        UserList userList = response.body();

                        UsersAdapter usersAdapter = new UsersAdapter(getApplicationContext(), userList.getUsers());

                        listViewUsers.setAdapter(usersAdapter);

                        listViewUsers.setOnItemClickListener(usersAdapter);

                        listViewUsers.setVisibility(View.VISIBLE);

                        textViewNoResults.setVisibility(View.GONE);

                        Loader.close();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.msg_falha_requisicao, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserList> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };
            userController.searchUsers(inputSearch.getText().toString());
        } else {
            Toast.makeText(getApplicationContext(), "É necessário digitar algum nome para facilitar a busca", Toast.LENGTH_SHORT).show();
        }
    }

}
