package tz.xmsh.fwz.com.shtz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class sz extends AppCompatActivity {
LinearLayout szdx;
    EditText tzmc,ycfs;
    Button tzmcbc,ycfsbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sz);
        setTitle("设置");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        onload();


        tzmcbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
print("这个功能还没做！惊不惊喜！");
            }
            });
        ycfsbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(!TextUtils.isEmpty( ycfs.getText().toString().trim())){
                    editor.putInt("ycfs",Integer.valueOf( ycfs.getText().toString()));
                    editor.commit();
                    print("已保存");
                }else{
                    print("写点什么吧");
                }


            }
        });

    }
    private void onload() {
        szdx=(LinearLayout)findViewById(R.id.szdx);
        tzmc=(EditText) findViewById(R.id.tzmc);
        ycfs=(EditText) findViewById(R.id.ycfs);
        tzmcbc=(Button) findViewById(R.id.tzmcbc);
        ycfsbc=(Button) findViewById(R.id.ycfsbc);
        SharedPreferences sharedPreferences = getSharedPreferences("shtz", sz.MODE_PRIVATE);
        ycfs.setText(String.valueOf( sharedPreferences.getInt("ycfs",0)));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void print(String sb)
    {
        Toast.makeText(getApplicationContext(), sb,
                Toast.LENGTH_SHORT).show();
        // TODO: Implement this method
    }
}
