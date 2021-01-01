package com.zc.httputilslib.task;

import android.app.Activity;


import com.zc.mylibrary.httpcore.ITaskCallbackListener;
import com.zc.mylibrary.httpcore.OkHttpUtil;
import com.zc.mylibrary.httpcore.RxTask;
import com.zc.mylibrary.tools.ProgressDialogManager;
import com.zc.mylibrary.tools.SPHelper;

import java.util.HashMap;


public class PostTask extends RxTask<String, Integer, String> {

    private Activity activity;
    private ITaskCallbackListener taskListener;

    public PostTask(Activity activity,
                    ITaskCallbackListener taskListener) {
        super(activity);
        this.activity = activity;
        this.taskListener = taskListener;
    }

    @Override
    protected String doInBackground(String... params) {
        // 执行请求
        String result = OkHttpUtil.post("https://www.baidu.com/", getParam());
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // 执行结果回调函数
        taskListener.doTaskComplete(result);
        ProgressDialogManager.dismissProgressDialog();
        super.onPostExecute(result);
    }

    @Override
    protected void onPreExecute() {
        ProgressDialogManager.showDialog(activity);
        super.onPreExecute();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    /**
     * 获取请求所需的参数
     *
     * @param
     * @return
     */
    private HashMap<String, Object> getParam() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", SPHelper.getInstance(activity).getString("userId", ""));
        return params;
    }
}