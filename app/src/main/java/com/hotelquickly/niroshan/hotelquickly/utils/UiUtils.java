package com.hotelquickly.niroshan.hotelquickly.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * com.hotelquickly.niroshan.hotelquickly.utils
 * <p/>
 * Created by Niroshan Rathnayake.
 */

public class UiUtils {

    public final static double FONT_SMALLER_DENSITY_SIZE = 23f;
    public final static double FONT_SMALL_DENSITY_SIZE = 19f;
    public final static double FONT_MEDIUM_DENSITY_SIZE = 16f;
    public final static double FONT_LARGE_DENSITY_SIZE = 10f;

    public static int getDip(Context ctx, int pixel) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                pixel, ctx.getResources().getDisplayMetrics());
    }

    @SuppressWarnings({ "static-access", "deprecation" })
    public static void setHeightByPercent(Context ctx, View Root,
                                          float percentage) {
        WindowManager windowManager = (WindowManager) ctx
                .getSystemService(ctx.WINDOW_SERVICE);
        int device_height = windowManager.getDefaultDisplay().getHeight();

        float res_scale = (float) device_height * percentage;
        Root.setMinimumHeight(Math.round(res_scale));

        Root.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Math
                .round(res_scale)));

        Root.requestLayout();
    }

    @SuppressWarnings({ "static-access", "deprecation" })
    public static void setHeightByPercentWeight(Context ctx, View Root,
                                                float percentage) {
        WindowManager windowManager = (WindowManager) ctx
                .getSystemService(ctx.WINDOW_SERVICE);
        int device_height = windowManager.getDefaultDisplay().getHeight();

        float res_scale = (float) device_height * percentage;
        Root.setMinimumHeight(Math.round(res_scale));

        Root.setLayoutParams(new LinearLayout.LayoutParams(dps2pixels(0, ctx), Math
                .round(res_scale)));

        Root.requestLayout();
    }

    @SuppressWarnings({ "static-access", "deprecation" })
    public static void setWidthHeightByPercent(Context ctx, View Root,
                                               float percentageWidth, float percentageHeight) {
        WindowManager windowManager = (WindowManager) ctx
                .getSystemService(ctx.WINDOW_SERVICE);
        int device_width = windowManager.getDefaultDisplay().getWidth();
        int device_height = windowManager.getDefaultDisplay().getHeight();

        float res_scale_width = (float) device_width * percentageWidth;
        float res_scale_height = (float) device_height * percentageHeight;
        Root.setMinimumHeight(Math.round(res_scale_height));
        Root.setLayoutParams(new LinearLayout.LayoutParams(Math.round(res_scale_width), Math
                .round(res_scale_height)));
        Root.requestLayout();
    }

    @SuppressWarnings({ "static-access", "deprecation" })
    public static int setMarginByPercent(Context ctx, View Root,
                                         float percentage) {
        WindowManager windowManager = (WindowManager) ctx
                .getSystemService(ctx.WINDOW_SERVICE);
        int device_height = windowManager.getDefaultDisplay().getHeight();

        float res_scale = (float) device_height * percentage;

        LinearLayout.LayoutParams temp = (LinearLayout.LayoutParams) Root.getLayoutParams();


        temp.setMargins((int) res_scale, (int) res_scale, (int) res_scale,
                (int) res_scale);

        return (int) res_scale;
    }


    @SuppressWarnings({ "static-access", "deprecation" })
    public static int setMarginByPercent(Context ctx, View Root,
                                         float percentage, boolean isLeftMarginZero) {
        WindowManager windowManager = (WindowManager) ctx
                .getSystemService(ctx.WINDOW_SERVICE);
        int device_height = windowManager.getDefaultDisplay().getHeight();

        float res_scale = (float) device_height * percentage;

        LinearLayout.LayoutParams temp = (LinearLayout.LayoutParams) Root.getLayoutParams();

        if(!isLeftMarginZero) {
            temp.setMargins((int) res_scale, (int) res_scale, (int) res_scale,
                    (int) res_scale);
        } else {
            temp.setMargins((int) 0, (int) res_scale, (int) res_scale,
                    (int) res_scale);
        }

        return (int) res_scale;
    }

    private static float scale = 0;

    public static int dps2pixels(int dps, Context context) {
        if (0 == scale) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dps * scale + 0.5f);
    }

    public static void setTextViewFontSizeBasedOnScreenDensity(
            Activity activity, TextView tv, double size) {

        DisplayMetrics dMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
        final float WIDE = activity.getResources().getDisplayMetrics().widthPixels;
        int valueWide = (int) (WIDE / size / (dMetrics.scaledDensity));
        tv.setTextSize(valueWide);
    }

    public static void setTextViewFontSizeBasedOnScreenDensity(
            Activity activity, TextView tv, double size, int style) {

        DisplayMetrics dMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
        final float WIDE = activity.getResources().getDisplayMetrics().widthPixels;
        int valueWide = (int) (WIDE / size / (dMetrics.scaledDensity));
        tv.setTextSize(valueWide);
        tv.setTypeface(tv.getTypeface(), style);
    }

    public static void setTextViewFontSizeBasedOnScreenDensity(
            Activity activity, TextView tv, double size, boolean makeBold) {

        DisplayMetrics dMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
        final float WIDE = activity.getResources().getDisplayMetrics().widthPixels;
        int valueWide = (int) (WIDE / size / (dMetrics.scaledDensity));
        if(makeBold) {
            tv.setTypeface(Typeface.DEFAULT_BOLD);
        }
        tv.setTextSize(valueWide);
    }

    public static void setTextViewFontSizeBasedOnScreenDensity(
            Activity activity, TextView tv) {

        DisplayMetrics dMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
        final float WIDE = activity.getResources().getDisplayMetrics().widthPixels;
        int valueWide = (int) (WIDE / 19.0f / (dMetrics.scaledDensity));
        tv.setTextSize(valueWide);
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
        final float WIDE = activity.getResources().getDisplayMetrics().widthPixels;

        return Math.round(WIDE);
    }
}

