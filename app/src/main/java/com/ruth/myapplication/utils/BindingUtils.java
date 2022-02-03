package com.ruth.myapplication.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.squareup.picasso.Picasso;

public class BindingUtils {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {

        if (url != null && !url.isEmpty()) {
            Picasso.get().load(url).into(imageView);
        }
        else imageView.setImageBitmap(null);
    }
}
