package javen.example.com.searchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Javen on 22/01/2018.
 */

public class SearchView extends LinearLayout {

    private Float textSize;
    private int textColor, hintColor;
    private String textHint;
    private int blockHeight;
    private int blockColor;
    private int hintTextSize;
    private EditTextClear editTextClear;
    private LinearLayout searchBlockLayout;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        inflate(context, R.layout.search_view_layout, this);
        initEditText();
        initSearchBlockLayout();
        editTextClear.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //按下软键盘的搜索按键，对历史搜索记录表进行查找，没有就插入数据库，并显示在RecyclerView中
                    //有的话不插入，直接查询出来显示在RecyclerView 中

                }
                return false;
            }
        });
    }

    private void initSearchBlockLayout() {
        searchBlockLayout = findViewById(R.id.search_block_layout);
        LayoutParams params = (LayoutParams) searchBlockLayout.getLayoutParams();
        params.height = blockHeight;
        searchBlockLayout.setBackgroundColor(blockColor);
        searchBlockLayout.setLayoutParams(params);
    }

    private void initEditText() {
        editTextClear = findViewById(R.id.et_search);
        editTextClear.setTextSize(textSize);
        editTextClear.setTextColor(textColor);
        editTextClear.setFocusable(true);
        editTextClear.setFocusableInTouchMode(true);
        editTextClear.setHintTextColor(hintColor);
        setHint();
    }

    private void setHint() {
        SpannableString string = new SpannableString(textHint);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(hintTextSize, false);
        string.setSpan(absoluteSizeSpan, 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editTextClear.setHint(new SpannedString(string));
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        setSearchViewTextColor(context, typedArray);
        textSize = typedArray.getDimension(R.styleable.SearchView_textSize, 20);
        textHint = typedArray.getString(R.styleable.SearchView_textHint);
        blockHeight = typedArray.getInteger(R.styleable.SearchView_searchBlockHeight, 150);
        hintTextSize = typedArray.getDimensionPixelSize(R.styleable.SearchView_textHintSize, 12);
        setSearchViewBlockColor(context, typedArray);
    }

    private void setSearchViewBlockColor(Context context, TypedArray typedArray) {
        int defaultBlockColor = context.getResources().getColor(R.color.colorDefault);
        blockColor = typedArray.getColor(R.styleable.SearchView_searchBlockColor, defaultBlockColor);
    }

    private void setSearchViewTextColor(Context context, TypedArray typedArray) {
        int defaultColor = context.getResources().getColor(R.color.colorText);
        textColor = typedArray.getColor(R.styleable.SearchView_textColor, defaultColor);
        hintColor = typedArray.getColor(R.styleable.SearchView_textHintColor, defaultColor);
    }


}
