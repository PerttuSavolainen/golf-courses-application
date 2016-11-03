package fi.jamk.golfcoursesapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView heading = (TextView) findViewById(R.id.fieldDetailNameTextView);

        Bundle extras = getIntent().getExtras();
        heading.setText(extras.getString("fieldName"));

    }
}
