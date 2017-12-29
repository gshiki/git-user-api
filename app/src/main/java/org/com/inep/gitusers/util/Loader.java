package org.com.inep.gitusers.util;

import android.app.ProgressDialog;
import android.content.Context;

import org.com.inep.gitusers.R;

/**
 * Created by Shiki on 27/12/2017.
 */
public class Loader {

    private static ProgressDialog progressDialog;

    public static void show(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);

            progressDialog.setMessage(context.getString(R.string.msg_loading_users));
            progressDialog.setCancelable(true);
        }
        progressDialog.show();
    }

    public static void close() {
        progressDialog.dismiss();

        progressDialog = null;
    }

}
