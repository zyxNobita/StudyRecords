package wechatedit.com.costomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import wechatedit.com.costomview.roundview.RoundProgressBar;

public class MainActivity extends AppCompatActivity {
    @butterknife.Bind(R.id.roundbar)
    RoundProgressBar roundbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);


        roundbar.start();

        roundbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roundbar.stop();
                Toast.makeText(MainActivity.this, "This button is clicked.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
