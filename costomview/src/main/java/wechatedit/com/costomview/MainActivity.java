package wechatedit.com.costomview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import wechatedit.com.costomview.roundview.AroundCircleView;
import wechatedit.com.costomview.roundview.RoundProgressBar;

public class MainActivity extends AppCompatActivity {


    //-----------------跳过------------
    @butterknife.Bind(R.id.roundbar)
    RoundProgressBar roundbar;

    //----------圆形图片进度条----------
    @butterknife.Bind(R.id.acv_icon)
    AroundCircleView acvIcon;
    private int progress;
    private boolean falg = true;
    Handler weakHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what == 1) {
                acvIcon.setProgress(progress);
            }
            if (progress == 100) {
                Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
        roundbar.start();
        startImgProgressBar();

        setOnClick();
    }

    private void setOnClick() {
        roundbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roundbar.stop();
                Toast.makeText(MainActivity.this, "This button is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
        acvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 100;
                Toast.makeText(MainActivity.this, "This button is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startImgProgressBar() {
        progress = 0;
        acvIcon.setProgress(progress);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (falg && progress <= 100) {
                    SystemClock.sleep(100);
                    progress += 1;
                    weakHandler.sendEmptyMessage(1);
                    if (progress > 100) {
                        weakHandler.sendEmptyMessage(2);
                    }
                }
            }
        }).start();

    }
}
