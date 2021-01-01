
package com.zc.mylibrary.httpcore;

import android.app.Activity;
import android.content.Context;


import com.zc.mylibrary.tools.Preconditions;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Tony.Fan on 2018/3/12 17:37
 * <p>
 * 封装 RxTask
 */
public abstract class RxTask<Params, Progress, Result> {
    private ObservableEmitter<? super Result> subscriber;
    private Context context;


    public RxTask(Context context) {
        this.context = context;
    }


    public final void execute(Params... params) {
        onPreExecute();
        Observable.create(subscriber -> doInbackgroud(subscriber, params)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseInfo -> {
                    try {
                        if (!Preconditions.isNullOrEmpty(context) && ((Activity) context).isFinishing()) {
                            onCancelled();
                            return;
                        }
                    } catch (Exception exp) {

                    }
                    onPostExecute((Result) responseInfo);
                }, e -> {
                    RxTask.this.onCancelled();
                    RxTask.this.onPostExecute(null);
                });
    }

    protected abstract Result doInBackground(Params... params);

    private void doInbackgroud(ObservableEmitter<? super Result> subscriber, Params... params) {
        this.subscriber = subscriber;
        Result result = doInBackground(params);
        subscriber.onNext(result);
        subscriber.onComplete();
    }

    protected void onPreExecute() {
    }


    protected void onPostExecute(Result result) {
    }

    protected void onCancelled() {
        cancel(true);
    }

    public void cancel(boolean forceCancel) {
        if (subscriber != null) {
            subscriber.onComplete();
        }
    }

}

