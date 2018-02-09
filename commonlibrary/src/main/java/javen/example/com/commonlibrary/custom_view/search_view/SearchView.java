package javen.example.com.commonlibrary.custom_view.search_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.spinytech.macore.MaActionResult;
import com.spinytech.macore.router.RouterResponse;

import java.util.List;

import javen.example.com.commonlibrary.R;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;
import javen.example.com.commonlibrary.local_router.RouterManager;
import javen.example.com.commonlibrary.utils.CheckUtil;

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
    private RecyclerView historyRecyclerView;
    private HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

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
        initRecyclerView(context);
        initSearchBlockLayout();
        initListener(context);
    }

    private void initRecyclerView(Context context) {
        historyRecyclerView = findViewById(R.id.recycler_view);
        historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(context);
        historyRecyclerView.setAdapter(historyRecyclerViewAdapter);
    }

    private void initListener(final Context context) {
        editTextClear.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //1.按下软键盘的搜索按键，对历史搜索记录表进行查找，没有就插入历史搜索数据库
                    //2.跳转到新的Activity页面去查询并显示与key匹配的数据

                    insertHistoryKey(context);
                    RouterManager.executeJumpToNewsShowActivity(context, "%" + editTextClear.getText().toString().trim() + "%");


                }
                return false;
            }
        });

        editTextClear.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                RouterResponse response = RouterManager.executeNewsHistoryKeyEqQuery(context, editTextClear.getText().toString().trim());
//
//                try {
//                    List<NewsSearchHistoryBean> list = (List<NewsSearchHistoryBean>) response.getObject();
//
//                    Log.e("listSize=", list.size() + "");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private void insertHistoryKey(Context context) {
        RouterResponse response = RouterManager.executeNewsHistoryKeyEqQuery(context, editTextClear.getText().toString().trim());

        try {
            int resultCode = response.getCode();

            if (resultCode != MaActionResult.CODE_SUCCESS) {
                Toast.makeText(context, "没有查询到相关结果", Toast.LENGTH_SHORT).show();
                RouterManager.executeNewsHistoryKeyInsert(context, editTextClear.getText().toString().trim());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setHistoryKeyData(List historyList) {
        historyRecyclerViewAdapter.setData(historyList);
    }


}
