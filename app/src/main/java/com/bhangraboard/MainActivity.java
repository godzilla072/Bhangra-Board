package com.bhangraboard;

import android.content.Context;
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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));

        Log.d("DEBUG", "onCreate Called!");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("DEBUG", "OnItemClick Called!");
                // Display a toast whenever an item in the gridView is tapped.
                String displayText = Integer.toString(position);
                Toast.makeText(MainActivity.this, displayText, Toast.LENGTH_SHORT).show();
            }
        });
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
                R.drawable.one_color
        };
    }
}
