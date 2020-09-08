package masters.watch.com.watchmaster;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ScrollingActivity extends AppCompatActivity {
String name="Fwz";   private int mCount = 1;ArrayList<watchmaster_list> homepage;RecyclerViewAdapter mRecyclerViewAdapter;  RecyclerView rv;
    NotificationManager manager;int yjjs=0,kxykjs=0;   int nid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("您好！"+name);
        setSupportActionBar(toolbar);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       watchmaster_satart();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "短消息推送功能(暂未开启)", Snackbar.LENGTH_LONG)
                        .setAction("了解一下", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Snackbar.make(v, "功能板块已添加", Snackbar.LENGTH_LONG).show();
                                        homepage.add(new watchmaster_list("短消息推送", null,R.drawable.xmsh,dip2px(getApplicationContext(),99)));
                                rv.setAdapter(mRecyclerViewAdapter);
                                    }
                                }).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer_xs.cancel();
    }

    private  void watchmaster_satart() {
         rv = (RecyclerView) findViewById(R.id.homepage);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
      //  rv.setLayoutManager(new LinearLayoutManager(this));
        homepage=new ArrayList<watchmaster_list>() {};
        homepage.add(new watchmaster_list("显示文字", null,R.drawable.wz,dip2px(getApplicationContext(),111)));
        homepage.add(new watchmaster_list("玩游戏", null,R.drawable.mxdb_1,dip2px(getApplicationContext(),169)));
        homepage.add(new watchmaster_list("一句诗", null,R.drawable.wh,dip2px(getApplicationContext(),99)));
        homepage.add(new watchmaster_list("智能穿戴", null,R.drawable.zncd,dip2px(getApplicationContext(),81)));
        homepage.add(new watchmaster_list("新闻速报", null,R.drawable.xw,dip2px(getApplicationContext(),91)));
        homepage.add(new watchmaster_list("手环&手表壁纸", null,R.drawable.bz,dip2px(getApplicationContext(),233)));
        homepage.add(new watchmaster_list("歌词显示", null,R.drawable.gcxs,dip2px(getApplicationContext(),199)));
        homepage.add(new watchmaster_list("看小说", null,R.drawable.xs,dip2px(getApplicationContext(),123)));
        homepage.add(new watchmaster_list("如何使用这个软件?", null,R.drawable.yh,dip2px(getApplicationContext(),132)));
         mRecyclerViewAdapter = new RecyclerViewAdapter(this,homepage);
        rv.setAdapter(mRecyclerViewAdapter);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(this,new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemClick(View view, int position) {
                item_run( view, position);
            }
                @Override
                public void onLongClick (View view,int position){
print("你在长按！你可能希望可以长按来调整顺序！可惜这个功能还没有完成");

                }
            }));

    }

    private void item_run(View view, int position) {
       TextView item_text=(TextView)view.findViewById(R.id.watchmaster_text);
        switch (item_text.getText().toString()){
            case "短消息推送":
                print_2("待开发");
//调转
                break;
           case "显示文字":
               if (isfsz)
                   print_2("发送中，请稍等。");
               else
                   cwz();
                break;
            case "玩游戏":
                paly_watch_game();
                break;
           case "一句诗":
               if (isfsz)
                   print_2("发送中，请稍等。");
               else
               {
                   if(yjjs==0){
                       yjh();
                      yjjs=1;
                   }else if (yjjs!=0){
                       print_2("加载中，请稍等。");
                   }

               }
               break;
            case "智能穿戴":
                gd();
                break;
            case "新闻速报":
                gd();
                break;
            case "手环&手表壁纸":
                gd();
                break;
            case "歌词显示":
                gd();
                break;
            case "看小说":
                if (isfsz)
                    print_2("发送中，请稍等。");
                else if (!isfsz)
                {
                   xs_layout();
                }
                break;
            case "如何使用这个软件?":
                gd();
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
           // Intent intent =new Intent(ScrollingActivity.this,sz.class);
          //  startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private Context context;
        private List<watchmaster_list> items;

        public RecyclerViewAdapter(Context context,List<watchmaster_list> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.watchmaster_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                            watchmaster_list watchamer_nb___Sdsds=items.get(position);
                            holder.watchmaster_image.setImageResource(watchamer_nb___Sdsds.getwatchmaster_image_int());
                            holder.watchmaster_image.getLayoutParams().height = watchamer_nb___Sdsds.getheight();
                            holder.watchmaster_text.setText(watchamer_nb___Sdsds.gettitle());

        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView watchmaster_text;ImageView watchmaster_image;
            public ViewHolder(View itemView) {
                super(itemView);
                watchmaster_text = (TextView) itemView.findViewById(R.id.watchmaster_text);
                watchmaster_image = (ImageView) itemView.findViewById(R.id.watchmaster_image);

            }
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        GestureDetector mGestureDetector;
        private View childView;
        private RecyclerView touchView;

        public RecyclerItemClickListener(Context context, final ScrollingActivity.RecyclerItemClickListener.OnItemClickListener mListener) {
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent ev) {
                    if (childView != null && mListener != null) {
                        mListener.onItemClick(childView, touchView.getChildPosition(childView));
                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent ev) {
                    if (childView != null && mListener != null) {
                        mListener.onLongClick(childView, touchView.getChildPosition(childView));
                    }
                }
            });
        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onLongClick(View view, int posotion);
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            childView = rv.findChildViewUnder(e.getX(), e.getY());
            touchView = rv;
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void print(String sb)
    {Toast.makeText(getApplicationContext(), sb,
            Toast.LENGTH_SHORT).show();
    }
    private void print_2(String sb)
    { Snackbar.make(rv, sb, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        // TODO: Implement this method
    }

    Boolean isfsz=false;int sbgg=0;   String channelID = "1";
    String channelName = "显示文字所用的通知";  Notification.Builder builder;
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


            Notification not = builder.build();
            not.flags=Notification.FLAG_ONLY_ALERT_ONCE;
                    manager.notify(nid,not);

        }else if(dasb==1){
            Notification not = builder.build();
            manager.notify(nid,not);
            isfsz=false;
            yjjs=0;
        }
    }
    String[] xmsh250;
    private void cwz() {

        View view = LayoutInflater.from(ScrollingActivity.this).inflate(R.layout.cwz_layout, null);
        final AlertDialog mDialog = new AlertDialog.Builder(ScrollingActivity.this)
                .setView(view).create();

        mDialog.setCancelable(true);
        final EditText cwzxx =view.findViewById(R.id.cwzxx);
        final EditText cwzdg =view.findViewById(R.id.cwzdg);
        final EditText cwzzl =view.findViewById(R.id.cwzzl);


        view.findViewById(R.id.cwzxxfs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable xmshxxwz=cwzxx.getText();
                try{cwz_wzcl(xmshxxwz.toString(),Integer.valueOf( cwzdg.getText().toString()),Integer.valueOf( cwzzl.getText().toString()));}catch(Exception e){
                    print("写点什么吧");
                }
                mDialog.dismiss();
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
   public void cwz_wzcl(String nr,int jttz,int dsz) {
       SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
       final int yanchi= sharedPreferences.getInt("ycfs",0);
           int cwzdgsl=jttz;
           int cwzzlsl=dsz;
           if (nr.length() <= cwzzlsl) {

               sendNotification(nr + "",0);
               if (yanchi==0)
                   print_2("已发送");
               else
                   print_2("这些文字将会在"+yanchi+"秒后到达");
           } else if (nr.length() > cwzzlsl) {
               final char[] cc = nr.toCharArray();

               final double fscs =  Math.ceil( (nr.length() /cwzzlsl));

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
isfsz=false;
   }

    private void xmsh_3() {
        View view = LayoutInflater.from(ScrollingActivity.this).inflate(R.layout.xmsh_dialog, null);
        final AlertDialog mDialog = new AlertDialog.Builder(ScrollingActivity.this)
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
    String xs_list_text,file_lj; String[] xs_layout_context_list_sort;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };
    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void xs_layout() {
        verifyStoragePermissions(this);
        final SharedPreferences sharedPreferences = getSharedPreferences("watchmaster", sz.MODE_PRIVATE);
        View view = LayoutInflater.from(ScrollingActivity.this).inflate(R.layout.xs_list_layout, null);
        final AlertDialog mDialog = new AlertDialog.Builder(ScrollingActivity.this)
                .setView(view).create();
        mDialog.setCancelable(true);
       ListView sjlb_xs_layout=(ListView) view.findViewById(R.id.tjxs);
        final List<String> data = new ArrayList<String>();
        String xs_layout_context_list=sharedPreferences.getString("xslb","");
       xs_layout_context_list_sort = xs_layout_context_list.split("开发者fwz");
                for(int sbbbbbbbbbbbbbfwz = 0;  sbbbbbbbbbbbbbfwz< xs_layout_context_list_sort.length ;sbbbbbbbbbbbbbfwz++) {
                    if (!xs_layout_context_list_sort[sbbbbbbbbbbbbbfwz].equals(null)&&!xs_layout_context_list_sort[sbbbbbbbbbbbbbfwz].equals("")){
                        String[] xs_layout_file_sort = xs_layout_context_list_sort[sbbbbbbbbbbbbbfwz].split("/");
                        data.add(xs_layout_file_sort[xs_layout_file_sort.length-1]);
                    }else{
                        data.add("暂无书籍,请点击右下方按钮添加。");
                    }
                }
        sjlb_xs_layout.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
        sjlb_xs_layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] xs_layout_file_sort = xs_layout_context_list_sort[position].split("/");
if (!data.get(position).equals("暂无书籍,请点击右下方按钮添加。")){
    try{
        ml_layout(position,xs_layout_file_sort[xs_layout_file_sort.length-1]);
    }catch(Exception e){print("打开书籍失败");}
}


            }
        });

        view.findViewById(R.id.xslbxxfs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType(“image/*”);//选择图片
                //intent.setType(“audio/*”); //选择音频
                //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                //intent.setType(“video/*;image/*”);//同时选择视频和图片
                intent.setType("*/*");//无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 0);
                mDialog.dismiss();
            }
        });

        view.findViewById(R.id.xslbxxqx).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mDialog.dismiss();
            }
        });
        mDialog.show();


    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            switch(requestCode){
                case 0:
                    Uri uri=data.getData();
                    String chooseFilePath = FileChooseUtil.getInstance(this).getChooseFileResultPath(uri);
                    Log.d("","选择文件返回："+chooseFilePath);
                    sendFileMessage(chooseFilePath);
                    break;
            }
        }
    }
    String fzwz="",ml_layout_fz_text; String[] ml_layout_fz,ml_layout_fz_text_zm; int zs=0;
    private void ml_layout(final int djg, String mz) {
        final SharedPreferences sharedPreferences = getSharedPreferences("watchmaster", sz.MODE_PRIVATE);
        View view = LayoutInflater.from(ScrollingActivity.this).inflate(R.layout.xs_ml_list_layout, null);
        final AlertDialog mDialog = new AlertDialog.Builder(ScrollingActivity.this)
                .setView(view).create();
        mDialog.setCancelable(true);
        final TextView mlbt=  view.findViewById(R.id.mlbt);
        mlbt.setText(mz);
        ListView ml_list_ListView= view.findViewById(R.id.xs_ml_list);
        List<String> data = new ArrayList<String>();
        ml_layout_fz_text=getFileOutputString( xs_layout_context_list_sort[djg].trim(),getCharset( xs_layout_context_list_sort[djg].trim()))+"玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————————————————————————————————————————————————智享穿戴------------------------------------------------------------------------开发者：fwz——————————————————————————————————————————玩转智能穿戴——————";
        ml_layout_fz=new String[(int)Math.ceil(ml_layout_fz_text.length()/1200)];

for (int sjhh=0;sjhh<Math.ceil(ml_layout_fz_text.length()/1200);sjhh++){
    data.add("第"+(sjhh+1)+"部分");
    ml_layout_fz[sjhh]=ml_layout_fz_text.substring(sjhh*1200,(sjhh+1)*1200);
}



        ml_list_ListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data));
        ml_list_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                zjnr(position,xs_layout_context_list_sort[djg].trim(),djg);
            }});

        view.findViewById(R.id.findtxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           findbook_txt(ml_layout_fz_text,djg);
            }});

        view.findViewById(R.id.mllbxxfs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    zjnr(-1,xs_layout_context_list_sort[djg].trim(),djg);
            }});

        view.findViewById(R.id.mllbxxqx).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mDialog.dismiss();
            }
        });
        mDialog.show();

    }

    private void findbook_txt(final String qw, final int djg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入");    //设置对话框标题
      //  builder.setIcon(android.R.drawable.btn_star);   //设置对话框标题前的图标
        final EditText edit = new EditText(this);
        builder.setView(edit);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (qw.indexOf(edit.getText().toString())==0){
                    print("什么也没有找到");
                }else{
                    print("前往第一次出现的段落"+qw.indexOf(edit.getText().toString()));
                    if(qw.indexOf(edit.getText().toString())%1200==0){
                        zjnr(qw.indexOf(edit.getText().toString())/1200,xs_layout_context_list_sort[djg].trim(),djg);
                    }else{
                        zjnr(qw.indexOf(edit.getText().toString())/1200,xs_layout_context_list_sort[djg].trim(),djg);
                    }
                }

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
        AlertDialog dialog = builder.create();  //创建对话框
        dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
        dialog.show();
    }

    private void zjnr(final int djg, final String sbbbbbbbbbbbbbb, final int sjh) {
        final SharedPreferences sharedPreferences = getSharedPreferences("watchmaster", sz.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        View view = LayoutInflater.from(ScrollingActivity.this).inflate(R.layout.xs_layout_part, null);
        final AlertDialog mDialog = new AlertDialog.Builder(ScrollingActivity.this)
                .setView(view).create();
        mDialog.setCancelable(true);
        final TextView zjnr_tiele=  view.findViewById(R.id.zjnr);
        final TextView zjnr=  view.findViewById(R.id.xs_layout_textview);

        if (djg!=-1){
            zjnr.setText(ml_layout_fz[djg]);
            zjnr_tiele.setText("第"+(djg+1)+"部分");
            editor.putInt("xsddn"+sbbbbbbbbbbbbbb,djg);
            editor.commit();
        }
        else{
            print("已回到上次阅读位置");
            zjnr.setText(ml_layout_fz[sharedPreferences.getInt("xsddn"+sbbbbbbbbbbbbbb,1)]);
            zjnr_tiele.setText("第"+(sharedPreferences.getInt("xsddn"+sbbbbbbbbbbbbbb,1)+1)+"部分");
        }

        view.findViewById(R.id.zjnrxxfs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    cwz_wzcl(zjnr.getText().toString(),10,120);
                mDialog.dismiss();
                if(djg!=-1)
                zjnr(djg+1,xs_layout_context_list_sort[sjh],sjh);
                else
                    zjnr(sharedPreferences.getInt("xsddn"+sbbbbbbbbbbbbbb,1)+1,xs_layout_context_list_sort[sjh],sjh);

            }});

        view.findViewById(R.id.zjnrxxqx).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mDialog.dismiss();
            }
        });
        mDialog.show();

    }


    private void sendFileMessage(String chooseFilePath) {
        file_lj=chooseFilePath;
        SharedPreferences sharedPreferences = getSharedPreferences("watchmaster", sz.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("xslb",sharedPreferences.getString("xslb","")+chooseFilePath+"开发者fwz");
        editor.commit();
        xs_layout();

    }

    int cwzdgsl_xs,xs_list=0;String[] xs_text; Timer mTimer_xs;TimerTask timerTask_xs;
    private void kxyk(String xmshxxwz,int cwzzlsl_xs) {//35
        if (isNetworkConnected(this)==false)
        {
            print_2("您还在没有网络的异次元中！");
        }else {
           //  xmshxxwz = "看见孔乙己。到了年关，掌柜取下粉板说，“孔乙己还欠十九个钱呢！”到第二年的端午，又说“孔乙己还欠十九个钱呢！”到中秋可是没有说，再到年关也没有看见他。我到现在终于没有见——大约孔乙己的确死了。";
             cwzdgsl_xs =(int) Math.ceil(  xmshxxwz.length()/cwzzlsl_xs);
            final char[] cc = xmshxxwz.toCharArray();

           // final double fscs = Math.ceil(cc.length / cwzzlsl_xs);

                print_2("已发送!!!!仅供测试");
               xs_text = new String[cwzdgsl_xs+1];
                for (int fscsnb = 0; fscsnb <= cwzdgsl_xs; fscsnb = fscsnb + 1) {
                        if (fscsnb!=cwzdgsl_xs)
                        xs_text[fscsnb] = xmshxxwz.substring(fscsnb*cwzzlsl_xs,(fscsnb+1)*cwzzlsl_xs);
                        else if (fscsnb==cwzdgsl_xs){
                            xs_text[fscsnb] = xmshxxwz.substring(fscsnb*cwzzlsl_xs,fscsnb*cwzzlsl_xs+(xmshxxwz.length()-fscsnb*cwzzlsl_xs));
                        }

                }



            mTimer_xs = new Timer();
            timerTask_xs=new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 1;
                    handler_xs.sendMessage(msg);
                }
            };
            mTimer_xs.schedule(timerTask_xs, 0, 3000);

        }
    }


    Handler handler_xs = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (xs_list<=cwzdgsl_xs){
                        Log.e("xmsh",xs_text[xs_list]);
                        sendNotification(xs_text[xs_list],0);
                        xs_list++;
                        if (xs_list-1==cwzdgsl_xs){xs_list=0;kxykjs=0; isfsz=false;destroyTimer();}
                    }
                    break;
                default:
                    break;
            }
        };
    };
    public void destroyTimer() {
        if (mTimer_xs != null) {
            mTimer_xs.cancel();
            mTimer_xs = null;

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
                yjjs=0;
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
                sendNotification(end,1);
            }

        }
    };
    Runnable runnable_0 = new Runnable(){
        @Override
        public void run() {
            // TODO: http request.
            String html;
            try {
                html= HtmlService.getHtml("http://yijuzhan.com/api/word.php?m=json")+"";
            } catch (Exception e) {
                html="服务站炸了？";
                e.printStackTrace();
                Log.e("mdzz","sbsbbbbbbbbbbb"+e);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(ScrollingActivity.this);

        builder.setTitle("提示");
        builder.setMessage("此功能暂未开放！！！！你有还有什么需要的功能呢？");

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
    public static String getFileOutputString(String filePath, String charset) {
        try {
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset), 8192);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append("\n").append(line);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getCharset(String filePath) {
        BufferedInputStream bis = null;
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8";
                checked = true;
            }
            bis.mark(0);
            if (!checked) {
                while ((read = bis.read()) != -1) {
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80 - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return charset;
    }
    public static int getSize(long number) {
        int count = 0;
        while (number > 0) {
            count += 1;
            number = (number / 10);
        }
        return count;
    }

    public void paly_watch_game() {
isRoot();
        handler_yx.postDelayed(task_yx,3000);

        //handler_game.post(task_game);//立即调用
    }
    int sbbbbbbbbbbbbb=0;
    private Handler handler_yx = new Handler();
    private Runnable task_yx = new Runnable() {
        public void run() {
            // TODO Auto-generated method stub
            handler_yx.postDelayed(this,5*100);//设置循环时间，此处是5秒

            if (sbbbbbbbbbbbbb!=9)
                setSystemTime(getApplication(),"0105090"+String.valueOf(sbbbbbbbbbbbbb));
            else if (sbbbbbbbbbbbbb==9){
                setSystemTime(getApplication(),"01050909");
                sbbbbbbbbbbbbb=0;
            }
            sbbbbbbbbbbbbb++;
        }
    };

    public static void setSystemTime(final Context cxt, String datetimes) {
        try {
            Process process = Runtime.getRuntime().exec("su");
//			String datetime = "20131023.112800"; // 测试的设置的时间【时间格式
            String datetime = ""; // 测试的设置的时间【时间格式
            datetime = datetimes.toString(); // yyyyMMdd.HHmmss】
            DataOutputStream os = new DataOutputStream(
                    process.getOutputStream());
            os.writeBytes("setprop persist.sys.timezone GMT\n");
            os.writeBytes("/system/bin/date " + datetime + "\n");
            os.writeBytes("clock -w\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            Toast.makeText(cxt, "权限异常", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isRoot()
    {
        Process process = null;
        DataOutputStream os = null;
        try
        {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            int exitValue = process.waitFor();
            if (exitValue == 0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (Exception e)
        {
            Log.d("", "" + e.getMessage());
            return false;
        } finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private Handler handler_game = new Handler();
    private Runnable task_game = new Runnable() {
        public void run() {
            // TODO Auto-generated method stub
            handler_game.postDelayed(this,1*1000);
            sendNotification("功能已锁定，暂不开放。",0);
        }
    };
}
