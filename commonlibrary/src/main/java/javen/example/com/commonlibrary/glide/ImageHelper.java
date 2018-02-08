package javen.example.com.commonlibrary.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Javen on 22/11/2017.
 */

public class ImageHelper {

    public synchronized static void setImageByGlide(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
