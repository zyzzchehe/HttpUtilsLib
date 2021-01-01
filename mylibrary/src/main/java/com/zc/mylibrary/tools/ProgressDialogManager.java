package com.zc.mylibrary.tools;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zc.mylibrary.R;


/**
 * Created by Tony.Fan on 2017/8/4.
 */

public class ProgressDialogManager {
    private static MaterialDialog progressDialog;


    public static void showDialog(@NonNull Context context) {
        showDialog(context, null);
    }

    public static void showDialog(@NonNull Context context, boolean canCancel) {
        showDialog(context, canCancel, null);
    }

    public static void showDialog(@NonNull Context context, DialogInterface
            .OnCancelListener listener) {
        showDialog(context, true, listener);
    }

    public static void showDialog(@NonNull Context context, Boolean canCancel, DialogInterface
            .OnCancelListener listener) {
        showProgressDialog(context, canCancel, listener);
    }

    public static void showProgressDialog(@NonNull Context context, boolean canCancelable, DialogInterface
            .OnCancelListener listener) {
        if (isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new MaterialDialog.Builder(context)
                .title("提示")
                .content("正在加载...")
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .show();
    }

    public static void dismissProgressDialog() {
        if (progressDialog == null) {
            return;
        }
        if (isShowing()) {
            try {
                progressDialog.dismiss();
                progressDialog = null;
            } catch (Exception e) {
                //捕获异常
            }
        }
    }

    public static boolean isShowing() {
        return (progressDialog != null && progressDialog.isShowing());
    }

}
