To get a Git project into your build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.zyzzchehe:HttpUtilsLib:Tag'
	}
  
如何调用：
1、GET请求：
public class GetTask extends RxTask<String, Integer, String> {

    private Activity activity;
    private ITaskCallbackListener taskListener;

    public GetTask(Activity activity,
                   ITaskCallbackListener taskListener) {
        super(activity);
        this.activity = activity;
        this.taskListener = taskListener;
    }

    @Override
    protected String doInBackground(String... params) {
        // 执行请求
        String result = OkHttpUtil.get("http://wthrcdn.etouch.cn/weather_mini?city=西安");
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

2、POST请求：
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
