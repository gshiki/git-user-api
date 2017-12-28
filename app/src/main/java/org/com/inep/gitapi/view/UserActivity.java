package org.com.inep.gitapi.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.com.inep.gitapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User
 */
public class UserActivity extends AppCompatActivity {

    @BindView(R.id.elementUserProfileName)
    TextView textViewUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userLogin = intent.getStringExtra("param_user_login");

        if (textViewUserName != null) {
            textViewUserName.setText(userLogin);
        }
    }
}
