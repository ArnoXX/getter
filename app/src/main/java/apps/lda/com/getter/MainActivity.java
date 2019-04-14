package apps.lda.com.getter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import androidx.appcompat.app.AppCompatActivity;
import apps.lda.com.getter.customViews.ExplorerFragment;

public class MainActivity extends AppCompatActivity {
    public JSONObject fs;
    public JSONObject root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        InputStream is = getResources().openRawResource(R.raw.fs);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();


        try {
            fs = new JSONObject(jsonString);

            root = fs.getJSONObject("root");



            JSONArray dirs = root.getJSONArray("dirs");
            JSONArray files = root.getJSONArray("files");

            Log.i("JSON", dirs.getString(0));

        } catch (JSONException e) {
            e.printStackTrace();
        }




        // TODO complete this shit
        Typeface roboto_thin = Typeface.createFromAsset (getAssets(),
                "fonts/roboto_thin.ttf");


        Fragment fr = new ExplorerFragment();
        ((ExplorerFragment) fr).setRoot(root);
        ((ExplorerFragment) fr).setBreadcrumbs((TextView) findViewById(R.id.breadcrumbsLine));
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, fr).commit();

    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
