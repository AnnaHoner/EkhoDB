package com.ekho.paintingRecognition;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.List;


import com.ekho.modifiedDemo.R;
import com.ekho.paintingRecognition.PaintingClassifierActivity;

public class DisplayResultActivity extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result2);

        this.listView = (ListView) findViewById(R.id.listView);
        ImageView painting = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        String message = intent.getStringExtra(PaintingClassifierActivity.EXTRA_MESSAGE);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String imageName = databaseAccess.imageName(message);
        List<String> data = databaseAccess.getData(message);
        databaseAccess.close();

        Resources res = getResources();
        int resID = res.getIdentifier(imageName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID );
        painting.setImageDrawable(drawable );



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        this.listView.setAdapter(adapter);






        //
    }
}
