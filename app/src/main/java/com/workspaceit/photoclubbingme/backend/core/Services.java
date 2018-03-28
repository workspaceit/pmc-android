package com.workspaceit.photoclubbingme.backend.core;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by vikram on 28-Mar-18.
 */

public class Services {

    public static void AlartBox(Context context, String str)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(str);
        alertDialog.show();
    }
}
