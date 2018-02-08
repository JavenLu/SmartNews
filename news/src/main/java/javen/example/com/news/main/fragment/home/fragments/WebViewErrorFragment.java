package javen.example.com.news.main.fragment.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javen.example.com.news.R;


/**
 * Created by Javen on 28/11/2017.
 */

public class WebViewErrorFragment extends Fragment {
    private TextView reLoadTextView;
    private OnClickReloadText onClickReloadText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_view_error_layout, container, false);
        reLoadTextView = view.findViewById(R.id.reload_text_view);
        reLoadTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReloadText.reloadWeb();
            }
        });

        return view;

    }

    public void setReLoadClickListener(OnClickReloadText onClickReloadText) {
        this.onClickReloadText = onClickReloadText;
    }


    public interface OnClickReloadText {
        void reloadWeb();
    }

}
