package pack.bi.utils;

import pack.bi.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    private static final int REQUIRED_WIDTH = 200;
    private static final int REQUIRED_HEIGHT = 200;
    private String url;
    private final WeakReference<ImageView> imageViewReference;
    private Resources res;
    private ResultsListener mListener;

    public ImageDownloader(ImageView imageView, ResultsListener listener) {
        imageViewReference = new WeakReference<ImageView>(imageView);
        mListener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        url = params[0];
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream(), null, opt);

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (opt.outWidth / scale / 2 >= REQUIRED_WIDTH && opt.outHeight / scale / 2 >= REQUIRED_HEIGHT) {
                scale *= 2;
            }

            opt = new BitmapFactory.Options();
            opt.inSampleSize = scale;
            return BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream(), null, opt);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {

            return BitmapFactory.decodeResource(res, R.drawable.ic_launcher);

        }
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (isCancelled()) {
            result = null;
        }
        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                mListener.onResultsSucceeded();
                imageView.setImageBitmap(result);
            } else {
                mListener.onError("Image not found!");
            }
        }
    }
}
