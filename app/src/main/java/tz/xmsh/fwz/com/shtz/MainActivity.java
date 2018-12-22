package tz.xmsh.fwz.com.shtz;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
LinearLayout xmsh,qtsb,gd,yjh,cwz,kxyk,bjxq;
    NotificationManager manager;
    int nid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
onload();
onstart();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello World!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
int yjjs=0,kxykjs=0;
    private void onstart() {
        xmsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isfsz)
                    print_2("发送中，请稍等。");
                else
              xmsh();
            }
        });
        qtsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isfsz)
                    print_2("发送中，请稍等。");
                else
                qtsb();

            }
        });
        gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
gd();
            }
        });
        yjh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isfsz)
                    print_2("发送中，请稍等。");
                else
                {
                    if(yjjs==0){
                        yjh();
                        yjjs=1;
                    }else{
                        print_2("加载中，请稍等。");
                    }

                }
            }
        });
        cwz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isfsz)
                    print_2("发送中，请稍等。");
                else
                    cwz();

            }
        });
        kxyk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isfsz)
                    print_2("发送中，请稍等。");
                else
                {
                    if(kxykjs==0){
                        kxyk();
                        kxykjs=1;
                    }else{
                        print_2("加载中，请稍等。");
                    }

                }
            }
        });

    }



    private void kxyk() {
        if (isNetworkConnected(this)==false)
        {
            print_2("您还在没有网络的异次元中！");
        }else{
            print_2("这个功能还没做。。。");
            sendNotification("小说",0);
        }
    }

    private void yjh() {
        if (isNetworkConnected(this) == false) {
            print_2("您还在没有网络的异次元中！");
        } else {
            print_2("加载中...");
            new Thread(runnable_0).start();

        }
        }
    Handler handler_0 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
          //  Log.i("mdzz","请求结果:" + val);
            if (val.equals("服务站炸了？")){
                print_2("服务站炸了？");
                return;
            }
            else{
                SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
                int yanchi=sharedPreferences.getInt("ycfs",0);
                if (yanchi==0)
                    print_2("已发送");
                else
                    print_2("这些文字将会在"+yanchi+"秒后到达");
                String[] gd=val.split(":");
                String end= gd[1].substring(1,gd[1].length()-10);
                sendNotification(end,0);
            }

        }
    };
    Runnable runnable_0 = new Runnable(){
        @Override
        public void run() {
            // TODO: http request.
            String html;
            try {
            html=getHtml("http://yijuzhan.com/api/word.php?m=json");
            } catch (Exception e) {
                html="服务站炸了？";
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value",html);
            msg.setData(data);
            handler_0.sendMessage(msg);
        }
    };
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    private void gd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("更多想法");
        builder.setMessage("你有还有什么需要的功能呢？");

        builder.setPositiveButton("诉说", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击确认做的操作
                print("听说给开发者的app给五星好评的人提需求更容易被满足哦!");
                Intent intent = new Intent();
                //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://www.coolapk.com/u/718544");
                intent.setData(content_url);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("不了", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击取消做的操作
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void qtsb() {


        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.xmsh_dialog, null);
        final AlertDialog mDialog = new AlertDialog.Builder(MainActivity.this)
                .setView(view).create();

        mDialog.setCancelable(true);
        final TextView xmshxbt=  view.findViewById(R.id.xmshbt);
        xmshxbt.setText("任意设备");
        final EditText xmshxx =view.findViewById(R.id.xmshxx);

        view.findViewById(R.id.xmshxxfs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable xmshxxwz=xmshxx.getText();
                if (!TextUtils.isEmpty(xmshxxwz.toString().trim())) {
                    sendNotification(xmshxxwz + "",0);

                    SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
                    int yanchi=sharedPreferences.getInt("ycfs",0);
                    if (yanchi==0)
                        print_2("已发送");
                    else
                        print_2("这些文字将会在"+yanchi+"秒后到达");
                    mDialog.dismiss();
                }else{
                    print("写点什么吧");
                }


            }
        });

        view.findViewById(R.id.xmshxxqx).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.show();
            }
            String[] xmsh250;
            private void cwz() {

                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.cwz_layout, null);
                final AlertDialog mDialog = new AlertDialog.Builder(MainActivity.this)
                        .setView(view).create();

                mDialog.setCancelable(true);
                final EditText cwzxx =view.findViewById(R.id.cwzxx);
                final EditText cwzdg =view.findViewById(R.id.cwzdg);
                final EditText cwzzl =view.findViewById(R.id.cwzzl);
                SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
                final int yanchi= sharedPreferences.getInt("ycfs",0);

                view.findViewById(R.id.cwzxxfs).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Editable xmshxxwz=cwzxx.getText();
                        if (!TextUtils.isEmpty(xmshxxwz.toString().trim())&&!TextUtils.isEmpty(cwzzl.getText().toString().trim())&&!TextUtils.isEmpty(cwzdg.getText().toString().trim())) {
                            int cwzdgsl=Integer.valueOf( cwzdg.getText().toString());
                            int cwzzlsl=Integer.valueOf( cwzzl.getText().toString());
                            if (xmshxxwz.length() <= cwzzlsl) {

                                sendNotification(xmshxxwz + "",0);
                                if (yanchi==0)
                                    print_2("已发送");
                                else
                                    print_2("这些文字将会在"+yanchi+"秒后到达");
                            } else if (xmshxxwz.length() > cwzzlsl) {
                                SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
                                final int yanchi= sharedPreferences.getInt("ycfs",0);


                                final char[] cc = xmshxxwz.toString().toCharArray();

                                final double fscs =  Math.ceil(cc.length / cwzzlsl);
                                if (fscs > cwzdgsl) {
                                    print_2("抱歉文字太长");
                                } else {
                                    if (yanchi==0)
                                        print_2("已发送");
                                    else
                                        print_2("这些文字将会在"+yanchi+"秒后到达");

                                    xmsh250=new String[cwzdgsl];
                                    /**
                                     * 要执行的操作
                                     */
                                    String[] zuiznr =new String[cwzdgsl];
                                    for (int fscsnb = 0; fscsnb < cwzdgsl; fscsnb = fscsnb + 1) {
                                        if ( fscsnb >=fscs){
                                            zuiznr[fscsnb]="";
                                        }else{
                                            String endshuchu = "";
                                            for (int sjxy = 0 + (cwzzlsl* fscsnb); sjxy < cwzzlsl + (cwzzlsl * fscsnb); sjxy++) {
                                                endshuchu = endshuchu + cc[sjxy];
                                            }
                                            zuiznr[fscsnb] = endshuchu;
                                        }
                                    }
                                    for (int wu = cwzdgsl-1; wu >= 0; wu = wu - 1) {
                                        xmsh250[(cwzdgsl-1)-wu]=zuiznr[wu];
                                    }
                                    sendNotification("cwz",cwzdgsl);

                                    //     manager.cancelAll();


                                }



                            }
                            mDialog.dismiss();
                        }else{
                            print("写点什么吧");
                        }

                    }
                });

                view.findViewById(R.id.cwzxxqx).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        mDialog.dismiss();
                    }
                });
                mDialog.show();
    }

    private void xmsh() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.xmsh_dialog, null);
        final AlertDialog mDialog = new AlertDialog.Builder(MainActivity.this)
                .setView(view).create();

        mDialog.setCancelable(true);
        final TextView xmshxbt=  view.findViewById(R.id.xmshbt);
        xmshxbt.setText("小米手环3");
        final EditText xmshxx =view.findViewById(R.id.xmshxx);

        view.findViewById(R.id.xmshxxfs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Editable xmshxxwz=xmshxx.getText();
                if (!TextUtils.isEmpty(xmshxxwz.toString().trim())) {
                    if (xmshxxwz.length() <= 50) {

                        sendNotification(xmshxxwz + "",0);
                        SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
                        int yanchi= sharedPreferences.getInt("ycfs",0);
                        if (yanchi==0)
                            print_2("已发送");
                        else
                            print_2("这些文字将会在"+yanchi+"秒后到达");
                    } else if (xmshxxwz.length() > 50) {
                        SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
                        final int yanchi= sharedPreferences.getInt("ycfs",0);


                        final char[] cc = xmshxxwz.toString().toCharArray();

                        final double fscs =  Math.ceil(cc.length / 50);
                        if (fscs > 5) {
                            print_2("抱歉文字太长");
                        } else {
                            if (yanchi==0)
                                print_2("已发送");
                            else
                                print_2("这些文字将会在"+yanchi+"秒后到达");

xmsh250=new String[5];
                                    /**
                                     * 要执行的操作
                                     */
                                    String[] zuiznr =  {"","","","",""};
                                    for (int fscsnb = 0; fscsnb < fscs; fscsnb = fscsnb + 1) {
                                        String endshuchu = "";
                                        for (int sjxy = 0 + (50 * fscsnb); sjxy < 50 + (50 * fscsnb); sjxy++) {
                                            endshuchu = endshuchu + cc[sjxy];
                                        }
                                        zuiznr[fscsnb] = endshuchu;
                                    }
                                    for (int wu = 4; wu >= 0; wu = wu - 1) {
                                       xmsh250[4-wu]=zuiznr[wu];
                                    }
                            sendNotification("mdzz",2);

                               //     manager.cancelAll();


                        }



                    }
                    mDialog.dismiss();
                }else{
                    print("写点什么吧");
                }

            }
        });

        view.findViewById(R.id.xmshxxqx).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mDialog.dismiss();
            }
        });
        mDialog.show();
    }


    private void onload() {
        xmsh=(LinearLayout) findViewById(R.id.xmsh);
        qtsb=(LinearLayout)findViewById(R.id.qtsb);
        gd=(LinearLayout)findViewById(R.id.gd);
        yjh=(LinearLayout)findViewById(R.id.yjh);
        cwz=(LinearLayout)findViewById(R.id.cwz);
        kxyk=(LinearLayout)findViewById(R.id.kxyk);
        bjxq=(LinearLayout)findViewById(R.id.bjxq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent =new Intent(MainActivity.this,sz.class);
                           startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void print(String sb)
    {Toast.makeText(getApplicationContext(), sb,
            Toast.LENGTH_SHORT).show();
        // TODO: Implement this method
    }
    private void print_2(String sb)
    { Snackbar.make(bjxq, sb, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        // TODO: Implement this method
    }
Boolean isfsz=false;int sbgg=0;   String channelID = "1";
    String channelName = "channel_name";  Notification.Builder builder;


    private void sendNotification(String sbb, final int dasb){
        SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
        final int yanchi= sharedPreferences.getInt("ycfs",0);
        if (sbb.equals("cwz")){
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(yanchi*1000);//休眠3秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 要执行的操作
                     */
                    for(int xmshdsb=0;xmshdsb<dasb;xmshdsb++)
                        sendNotification(xmsh250[xmshdsb],1);

                }
            }.start();
            return;
        }
        if (dasb==2){
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(yanchi*1000);//休眠3秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 要执行的操作
                     */
                    for(int xmshdsb=0;xmshdsb<5;xmshdsb++)
                   sendNotification(xmsh250[xmshdsb],1);

                }
            }.start();

            return;
        }




        if(sbb.equals("")){return;}
        sbgg++;
        isfsz=true;
      //  Intent intent = new Intent(this,MainActivity.class);
     //  PendingIntent pindtent = PendingIntent.getActivity(this, 0, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

            manager.createNotificationChannel(channel);
        }
      builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.fs);//图标
        builder.setTicker("CREATED IN BAOJI WITH LOVE");//手机状态栏的提示;
        builder.setWhen(System.currentTimeMillis());//设置时间
        builder.setContentTitle("");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(channelID);
        }

        builder.setContentText(sbb);//通知内容
         //builder.setContentIntent(pindtent);//点击后意图
//builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示声音
//builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置指示灯
//builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
        //builder.setDefaults(Notification.DEFAULT_ALL);//设置提示声音,震动,指示灯
        if (dasb==0){
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(yanchi*1000);//休眠3秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 要执行的操作
                     */
                    yjjs=0;kxykjs=0;
                    Notification not = builder.build();
                    manager.notify(nid,not);
                    isfsz=false;
                }
            }.start();

        }else if(dasb==1){

            Notification not = builder.build();
            manager.notify(nid,not);
            isfsz=false;

        }




    }




    public static String getHtml(String path) throws Exception{

            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                byte[] data = read(inputStream);         //将流转换成字节数组，read(...)方法的代码在下面
                String html = new String(data);           //将字节数组转换成字符串
                return html;

            }

        return null;
    }


    public static byte[ ] read(InputStream inStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[ ] buffer = new byte[1024];
        int len = 0;
        while((len = inStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }
}
