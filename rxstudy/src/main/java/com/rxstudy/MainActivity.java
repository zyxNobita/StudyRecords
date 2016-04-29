package com.rxstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 *
 * @time 2016-4-29 16:14:02
 *
 *       https://github.com/THEONE10211024/RxJavaSamples rx系列相关的blog以及相关用法与使用场景
 */
public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getName() + "RxTest";
    @Bind(R.id.main_btn)
    Button mainBtn;
    @Bind(R.id.mainTxt)
    TextView mainTxt;
    @Bind(R.id.main_btn_next)
    Button mainBtnNext;
    private Observer<Integer> mObserver;
    private Observable<Integer> mObservable;
    private StringBuilder mStringBuilder;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mStringBuilder = new StringBuilder();
        createObserver();
        createObservable();

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relateObservableAndObserver();
            }
        });

        mainBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
    }

    // 1.创建观察者Observer
    private void createObserver() {
        mObserver = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
                mainTxt.setText(mStringBuilder.append("onCompleted").toString());
                mToast.makeText(MainActivity.this,
                        "这是运行在UIMain线程的，继续点击NEXT", Toast.LENGTH_LONG).show();
                mainBtnNext.setVisibility(View.VISIBLE);
                finish();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void onNext(Integer o) {
                mStringBuilder.append(o.toString() + "\n");
            }
        };
    }

    // 2.创建被观察者Observable
    private void createObservable() {
        mObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i <= 10; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });
    }

    // 3.将观察者与被观察者关联起来,建立订阅关系
    private void relateObservableAndObserver() {
        mObservable.subscribe(mObserver);
    }
}
