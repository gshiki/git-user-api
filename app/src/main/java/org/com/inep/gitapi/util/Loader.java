package org.com.inep.gitapi.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Shiki on 27/12/2017.
 */
public class Loader {

    private static ProgressDialog progressDialog;

    public static void show(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);

            progressDialog.setMessage("Its loading....");
            progressDialog.setCancelable(true);
        }
        progressDialog.show();
    }

    public static void close() {
        progressDialog.dismiss();
    }

}
