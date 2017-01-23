package com.bhangraboard;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;
import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));

        // Initialize media player objects for playing sound clips.
        final ArrayList<MediaPlayer> mediaPlayerList = loadSoundClips(new ArrayList<MediaPlayer>());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("DEBUG", "OnItemClick Called!");

                // TODO: Remove toast.
                // Display a toast whenever an item in the gridView is tapped.
                String displayText = Integer.toString(position);
                Toast.makeText(MainActivity.this, displayText, Toast.LENGTH_SHORT).show();

                // Play sound clip.
                MediaPlayer test_mp = mediaPlayerList.get(position);
                Log.d("DEBUG", test_mp.toString());


                MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.one);
                mp.start();

                // TODO: Swap to black & white image while sound is playing.
            }
        });
    }

    private ArrayList<MediaPlayer> loadSoundClips(ArrayList<MediaPlayer> mediaPlayerList) {

        // Dynamically load all of the sound clip resources in the raw dir.
        Field[] fields = R.raw.class.getFields();

        for (Field field : fields) {
            try {
                int rawResourceId = field.getInt(null);
                mediaPlayerList.add(MediaPlayer.create(MainActivity.this, rawResourceId));
                Log.d("DEBUG", String.format("%s is %d", field.getName(), rawResourceId));
            } catch(IllegalAccessException e) {
                Log.e("ERROR", String.format("%s threw IllegalAccessException", field.getName()));
            } catch(IllegalArgumentException e) {
                Log.e("ERROR", String.format("%s threw IllegalArgumentException", field.getName()));
            }
        }

        return mediaPlayerList;
    }

    // This class is responsible for filling the gridView with Bhangra artists.
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private int imageHeight = 200;
        private int imageWidth = 200;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // Create a new ImageView for each item used by the ImageAdapter.
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            // Try reusing the old view, if possible.
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(imageHeight, imageWidth));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // References to our images of Bhangra artists.
        private Integer[] mThumbIds = {
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color,
                R.drawable.one_color
        };
    }
}
