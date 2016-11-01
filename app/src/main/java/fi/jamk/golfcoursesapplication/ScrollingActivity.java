package fi.jamk.golfcoursesapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<GolfField> gfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gfs = createGolfFieldList(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.golffieldRecyclerView);
        // create layoutmanager
        mLayoutManager = new LinearLayoutManager(this);
        // set manager to recycler view
        mRecyclerView.setLayoutManager(mLayoutManager);
        // create adapter
        mAdapter = new GolfFieldsAdapter(gfs);
        // set adapter to recycler view
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String loadJsonFromAssets(Context context) {

        String json = null;

        try {
            InputStream inputStream = context.getAssets().open("golf_courses.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException exc) {
            exc.printStackTrace();
            return null;
        }

        return json;
    }

    /**
     * create GolfField objects from json
     *
     * @param context
     * @return list
     */
    public List<GolfField> createGolfFieldList(Context context) {

        List<GolfField> list = new ArrayList<>();

        try {
            JSONObject JSONObj = new JSONObject(loadJsonFromAssets(context));
            JSONArray fields = JSONObj.getJSONArray("kentat");
            for (int i = 0; i< fields.length(); i++) {
                JSONObject current = fields.getJSONObject(i);
                GolfField gf = new GolfField(
                        current.getString("Tyyppi"),
                        current.getString("lat"),
                        current.getString("lng"),
                        current.getString("Kentta"),
                        current.getString("Puhelin"),
                        current.getString("Sahkoposti"),
                        current.getString("Osoite"),
                        current.getString("Kuva"),
                        current.getString("Kuvaus"),
                        current.getString("Webbi")
                );
                list.add(gf);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;

    }

}
