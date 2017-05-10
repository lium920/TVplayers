package com.example.administrator.tvplayers;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import media.AndroidMediaController;
import media.IjkVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity implements MyItemClickListener {
    private static final String TAG ="MainActivity" ;
    private    int flag=0 ;
    private boolean mBackPressed;
    private IjkMediaPlayer mIjkMediaPlayer;
    private IjkVideoView mPlayer;
    private AndroidMediaController mAndroidMediaController;
    private List<String> mData;
    private RecyclerView mProgrem;
    private Button mExit;
    private LinearLayoutManager mManager;
    private  MyAdapter mMyAdapter;
    private MyCountDownTimer mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        }
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        mPlayer = (IjkVideoView) findViewById(R.id.player);
        mIjkMediaPlayer = new IjkMediaPlayer();
        mIjkMediaPlayer.loadLibrariesOnce(null);
        mIjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mAndroidMediaController = new AndroidMediaController(this, false);

        mPlayer.setMediaController(mAndroidMediaController);
        //http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8
        String url ="http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";

        mPlayer.setVideoURI(Uri.parse(url));
        mPlayer.start();
        mProgrem = (RecyclerView) findViewById(R.id.progremlist);
        mExit = (Button) findViewById(R.id.exit);
        mData=new ArrayList<String>();
        mData.add("CCTV-1");
        mData.add("CCTV-3");
        mData.add("CCTV-5");
        mData.add("CCTV-6");
        mData.add("CCTV-8");
        mData.add("CHC电影");
        mData.add("北京卫视");
        mData.add("北京文艺");
        mData.add("北京体育");
        mData.add("北京纪实");
        mData.add("湖南卫视");
        mData.add("浙江卫视");
        mData.add("江苏卫视");
        mData.add("东方卫视");
        mData.add("安徽卫视");
        mData.add("黑龙江卫视");
        mData.add("辽宁卫视");
        mData.add("深圳卫视");
        mData.add("广东卫视");
        mData.add("天津卫视");
        mData.add("湖北卫视");
        mData.add("山东卫视");
        mData.add("重庆卫视");
        mExit.setBackgroundResource(R.drawable.video_exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleandata();
                finish();
            }
        });
        mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mProgrem.setLayoutManager(mManager);
        mMyAdapter = new MyAdapter(this, mData);
        mProgrem.setAdapter(mMyAdapter);
        this.mMyAdapter.setOnItemClickListener(this);
        mc = new MyCountDownTimer(10000, 1000);
        mc.start();
    }


    @Override
    public void onItemClick(View view, int postion) {
        mc.cancel();
        mc.start();
        if (flag==mData.size()){
            Log.e(TAG, "onItemClick:--------------11------------- "+mData.size()+"+++++"+postion+"----------------"+flag);
            flag=postion;
        }else  if (postion ==flag){
            Log.e(TAG, "onItemClick:--------------22------------- "+postion+"----------------"+flag);
            view.setBackgroundResource(R.drawable.chick);
        }
        else{
            if (mManager.findViewByPosition(flag)!=null){
                mManager.findViewByPosition(flag).setBackgroundResource(R.drawable.video_bg_list);
            }
            view.setBackgroundResource(R.drawable.chick);
            Log.e(TAG, "onItemClick:--------------33------------- "+postion+"----------------"+flag);
            flag =postion;
        }
        String s = mData.get(postion);
        cleandata();
        if(s != null){
            if (s.equals("CCTV-1")){
                String url1 ="http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
                Log.e(TAG, "onItemClick: "+url1);
                     seturl(url1);
            }

            if (s.equals("CCTV-3"))
            {
                String url3 = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8";
               seturl(url3);

            }
            if (s.equals("CCTV-5")){
                String url5 = "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8";
               seturl(url5);
            }
            if (s.equals("CCTV-6")){
                String url6 = "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";
                seturl(url6);
            }
            if (s.equals("CCTV-8")){
                String url8 = "http://ivi.bupt.edu.cn/hls/cctv8hd.m3u8";
                seturl(url8);
            }
            if (s.equals("CHC电影")){
                String url9 = "http://ivi.bupt.edu.cn/hls/chchd.m3u8";
                seturl(url9);
            }
            if (s.equals("北京卫视")){
                String url10 = "http://ivi.bupt.edu.cn/hls/btv1hd.m3u8";
                seturl(url10);
            }
            if (s.equals("北京文艺")){
                String url11 = "http://ivi.bupt.edu.cn/hls/btv2hd.m3u8";
                seturl(url11);
            }
            if (s.equals("北京体育")){
                String url12 = "http://ivi.bupt.edu.cn/hls/btv6hd.m3u8";
                seturl(url12);
            }
            if (s.equals("北京纪实")){
                String url13 = "http://ivi.bupt.edu.cn/hls/btv11hd.m3u8";
                seturl(url13);
            }
            if (s.equals("湖南卫视")){
                String url14 = "http://ivi.bupt.edu.cn/hls/hunanhd.m3u8";
                seturl(url14);
            }
            if (s.equals("浙江卫视")){
                String url15 = "http://ivi.bupt.edu.cn/hls/zjhd.m3u8";
                seturl(url15);
            }
            if (s.equals("江苏卫视")){
                String url16 = "http://ivi.bupt.edu.cn/hls/jshd.m3u8";
                seturl(url16);
            }
            if (s.equals("东方卫视")){
                String url17 = "http://ivi.bupt.edu.cn/hls/dfhd.m3u8";
                seturl(url17);
            }
            if (s.equals("安徽卫视")){
                String url18 = "http://ivi.bupt.edu.cn/hls/ahhd.m3u8";
                seturl(url18);
            }
            if (s.equals("黑龙江卫视")){
                String url9 = "http://ivi.bupt.edu.cn/hls/hljhd.m3u8";
                seturl(url9);
            }
            if (s.equals("辽宁卫视")){
                String url9 = "http://ivi.bupt.edu.cn/hls/lnhd.m3u8";
                seturl(url9);
            }
            if (s.equals("深圳卫视")){
                String url9 = "http://ivi.bupt.edu.cn/hls/szhd.m3u8";
                seturl(url9);
            }
            if (s.equals("广东卫视")){
                String url9 = "http://ivi.bupt.edu.cn/hls/gdhd.m3u8";
                seturl(url9);
            }
            if (s.equals("天津卫视")){
                String url9 = "http://ivi.bupt.edu.cn/hls/tjhd.m3u8";
                seturl(url9);
            }
            if (s.equals("湖北卫视")){
                String url9 = "http://ivi.bupt.edu.cn/hls/hbhd.m3u8";
                seturl(url9);
            }
            if (s.equals("山东卫视")){
                String url9 = "http://ivi.bupt.edu.cn/hls/sdhd.m3u8";
                seturl(url9);
            }
            if (s.equals("重庆卫视")){
                String url9 = "http://ivi.bupt.edu.cn/hls/cqhd.m3u8";
                seturl(url9);
            }

        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(Displayer event) {
        Log.e(TAG, "onDataSynEvent: -------------------1" );
        if (mProgrem.getVisibility()==View.VISIBLE){
            mProgrem.setVisibility(View.GONE);
        }else {
            mProgrem.setVisibility(View.VISIBLE);
            mc.start();
        }



    }
    public  void cleandata(){

        mIjkMediaPlayer.release();
    }
    @Override
    protected void onDestroy() {
        cleandata();
        super.onDestroy();
        Log.d(TAG, "onDestroy:  ondestroy");
    }
    @Override
    protected void onStop() {


        super.onStop();

        if (mBackPressed || ! mPlayer.isBackgroundPlayEnabled()) {
            mPlayer.stopPlayback();
            mPlayer.release(true);
            mPlayer.stopBackgroundPlay();
        } else {
            mPlayer.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
        EventBus.getDefault().unregister(this);
        mc.cancel();
    }

    @Override
    public void onBackPressed() {

        mBackPressed = true;
        Log.d(TAG, "onBackPressed:  ssssssssssssssss");
        super.onBackPressed();
    }

    public  static class  Displayer{
        public void Displayer(){
        }

    }

    public void seturl(String url){
        mPlayer.setVideoURI(Uri.parse(url));

        mPlayer.setOnPreparedListener(new IjkMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {

                mp.start();
            }
        });
    }
    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         *            表示以毫秒为单位 倒计时的总数
         *
         *            例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         *            表示 间隔 多少微秒 调用一次 onTick 方法
         *
         *            例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
          mProgrem.setVisibility(View.GONE);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }
    }

}
