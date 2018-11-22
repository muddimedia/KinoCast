package com.ov3rk1ll.kinocast.api.mirror;

import android.text.TextUtils;
import android.util.Log;

import com.ov3rk1ll.kinocast.ui.DetailActivity;
import com.ov3rk1ll.kinocast.utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class VShare extends Host {
    private static final String TAG = VShare.class.getSimpleName();
    public static final int HOST_ID = 78;

    public static String getMirrorLink(Document doc){
        try {

            String href = doc.select("a").attr("href");

            return href;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public int getId() {
        return HOST_ID;
    }

    @Override
    public String getName() {
        return "VShare.eu";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getVideoPath(DetailActivity.QueryPlayTask queryTask) {
        Log.d(TAG, "GET " + url);

        if(TextUtils.isEmpty(url)) return null;
        try {
            queryTask.updateProgress(url);
            Document doc = Jsoup.connect(url)
                    .userAgent(Utils.USER_AGENT)
                    .timeout(3000)
                    .get();

            return doc.select("source[type=video/mp4]").attr("src");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
