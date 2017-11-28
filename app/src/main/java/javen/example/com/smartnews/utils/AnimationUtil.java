package javen.example.com.smartnews.utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

/**
 * Created by Javen on 28/11/2017.
 */

public class AnimationUtil {
    private static AnimationUtil animationUtil;

    public static AnimationUtil getInstance() {
        if (animationUtil == null) {
            animationUtil = new AnimationUtil();
        }
        return animationUtil;
    }

    public void showYShakePropertyAnimationInShareDialog(View view, float translationValue, long duration) {

        PropertyValuesHolder translationValuesHolder = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, translationValue),
                Keyframe.ofFloat(0.5f, -translationValue),
                Keyframe.ofFloat(1.0f, 0f)

        );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, translationValuesHolder);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }
}
