package com.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.OverScroller;

/**
 * Created by ZQiang on 2016/7/25.
 */
public class ColumnHorizontalScrollView extends View implements ViewPager.OnPageChangeListener {

    private Context context;
    private int width ;
    private int padingLeft;
    private int padingRight ;
    private float lineStartX = 0  ;
    private float lineEndX = 0  ;
    private Paint textPaint;
    private int textWidth ;
    private int lastPosition = 0 ;
    private float lineScale = 0 ;

    private ViewPager mViewPager ;


    /**
     * 这些事默认设置的
     */
    private static final int DEFULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFULT_TEXT_SIZE = 40;
    private  static final int DEFULT_LINE_COLOR = Color.RED;
    private static final int DEFULT_LINE_STRO = 5;


    private String [] str ; // 存储 题要显示的字符
    private float [] strX ; // 存储 每个字符  开始的X位置
    private float [] strWidth ;  // 存储每个字符的长度
    private int startX; // 与手势相关    记录按下的X的坐标

    private int recordDownX = 0 ;

    private OnClickOneListener mOnClickOneListener ;
    private OverScroller mScroller;

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs,
                                      int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public ColumnHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }


    /**
     * 初始化的时候需要new出来的一些类，和必要的设置
     * @param context
     */
    private void init(Context context){
        this.context = context ;
        mScroller = new OverScroller(context,new LinearInterpolator());
        textPaint = new Paint();
        linePaint = new Paint();

        textPaint.setColor(DEFULT_TEXT_COLOR);
        textPaint.setTextSize(DEFULT_TEXT_SIZE);
        textPaint.setTypeface(Typeface.SANS_SERIF);

        linePaint.setColor(DEFULT_LINE_COLOR);
        linePaint.setStrokeWidth(DEFULT_LINE_STRO);
    }




    /**
     * 画图
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (str == null) {
            return ;
        }
        measureSize();
        drawText(canvas); //画字
        measureLineSize();//测量下划线的位置
        drawLine(canvas);//画下划线
    }



    /**
     * 这个是用来通过Scroller来控制缓冲变化的   后来不需要了
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
        super.computeScroll();
        postInvalidate();
    }


    /**
     * 画字的具体代码
     * @param canvas
     */
    private  void drawText(Canvas canvas){
        int totel = 0 ;
        for (int i = 0; i < str.length; i++) {
            if (i == 0 ) {
                totel += padingLeft;
            }
            strX[i] = totel ;
            canvas.drawText(str[i], strX[i], textHeight, textPaint);
            float oneTextWidth = textPaint.measureText(str[i]);
            totel =(int) (totel +  oneTextWidth +space);
            strWidth[i] = oneTextWidth ;
        }
        textWidth = totel - space  +padingLeft ;
    }


    private void drawLine(Canvas canvas){
        canvas.drawLine(lineStartX, lineHeight ,lineEndX , lineHeight, linePaint);
    }



    private void measureSize(){
        width = getWidth();
        height = getHeight();
        padingLeft = getPaddingLeft();
        padingRight = getPaddingRight();
        textHeight = (int) (height / 2 - (textPaint.descent() + textPaint.ascent())/2);
        lineHeight = (int) (textHeight - (textPaint.descent() + textPaint.ascent()));
    }



    private void measureLineSize(){
        lineStartX = strX[lastPosition] - space / 2 + (strWidth[lastPosition] + space )* lineScale;
        if ((lastPosition + 1) == mViewPager.getAdapter().getCount()) {
            lineEndX = strX[lastPosition] + strWidth[lastPosition] + space / 2 ;
        }else {
            lineEndX = strX[lastPosition] + strWidth[lastPosition] + space / 2 + ( strWidth[lastPosition + 1] + space ) * lineScale;
        }
    }



    public void setTitle(String ...str){
        this.str = str ;
        strX = new float[str.length];
        strWidth = new float[str.length];
    }


    private int space = 0 ;
    private Paint linePaint;
    private int lineHeight;
    private int height;
    private int textHeight;

    public void setspace(int space){
        this.space = space ;
    }

    public void setTextColorResourceId(int colorid){
        int color = context.getResources().getColor(colorid);
        textPaint.setColor(color);
    }

    public void setTextColor(int color){
        textPaint.setColor(color);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = recordDownX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int scrollX = getScrollX();
                if (Math.abs(moveX - recordDownX ) > 32 ) {
                    smootMove(scrollX , startX - moveX);
                }
                startX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) event.getX();
                if (Math.abs(upX - recordDownX ) < 32 ) {
                    int position = decidePosition(upX);
                    setPosition(position);
                    if (mOnClickOneListener != null) {
                        mOnClickOneListener.onClick(position) ;
                    }
                }
                break;
        }
        return true;
    }


    private int  decidePosition(int upX){
        for (int i = 0; i < strX.length; i++) {
            if ((upX + getScrollX()) < (strX[i] + strWidth[i]) + space / 2) {
                return i ;
            }
        }
        return 0 ;
    }


    public void setPosition(int position){
//        lastPosition = position ;
//        int scrollX = getScrollX();
//        int location = (int) (strX[lastPosition] - space - 20);
//        int dy = (int)(location  - getScrollX());
//        if ((location + width) > (textWidth + padingRight ) ) {
//            mScroller.startScroll(scrollX, 0,textWidth + padingRight - width - getScrollX()  , 0, 200);
//        }else {
//            mScroller.startScroll(scrollX, 0, dy , 0, 200);
//        }
//        if ((scrollX + dy ) > (textWidth+padingRight - width)) {
//            mScroller.startScroll(scrollX, 0, textWidth+padingRight - width - scrollX , 0, 200);
//        }else {
//            mScroller.startScroll(scrollX, 0, dy , 0, 200);
//        }
        mViewPager.setCurrentItem(position);
    }


    private void smootMove(int scrollX , int dy){
        if ((scrollX <= 0 && dy > 0 && textWidth > width)  || (scrollX > 0)) {
            if ((scrollX + dy) < 0 ) {
                scrollTo(0 , 0);
            }else {
                if ((scrollX + dy + getWidth()) > (textWidth+padingRight )) {
                    scrollTo(textWidth +padingRight - getWidth() , 0);
                }else {
                    scrollTo(scrollX + dy , 0);
                }
            }
        }
    }

    public void setViewPager(ViewPager mViewPager){
        this.mViewPager = mViewPager;
        if (this.mViewPager != null) {
            this.mViewPager.setOnPageChangeListener(this);
        }
    }

    /**
     * dip转为 px
     */
    public int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int lastPosition, float scale, int location) {
        this.lastPosition = lastPosition ;
        lineScale = scale ;
        invalidate();
        smootMove((int)(strX[lastPosition] - space - 20 ) ,(int)((strWidth[lastPosition] + space )*  lineScale));
    }

    @Override
    public void onPageSelected(int arg0) {
    }


    public void setOnClickOneListener (OnClickOneListener mOnClickOneListener){
        this.mOnClickOneListener = mOnClickOneListener ;
    }


    /**
     * 内部接口    是点击事件的接口
     * @author lenovo
     *
     */
    interface OnClickOneListener{
        public void onClick(int position);
    }

}