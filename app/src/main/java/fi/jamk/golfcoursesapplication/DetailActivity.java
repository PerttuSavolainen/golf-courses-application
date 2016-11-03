package fi.jamk.golfcoursesapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView address = (TextView) findViewById(R.id.fieldDetailAddressTextView);
        TextView phone = (TextView) findViewById(R.id.fieldDetailPhoneTextView);
        TextView email = (TextView) findViewById(R.id.fieldDetailEmailTextView);
        TextView desc = (TextView) findViewById(R.id.fieldDetailDescriptionTextView);
        TextView url = (TextView) findViewById(R.id.fieldDetailUrlTextView);
        TextView latlng = (TextView) findViewById(R.id.fieldDetailLatLngTextView);
        ImageView img = (ImageView) findViewById(R.id.fieldDetailImageView);

        Bundle extras = getIntent().getExtras();

        new DownloadImageTask(img).execute("http://ptm.fi/jamk/android/golfcourses/" + extras.getString("fieldImageUrl"));
        setTitle(extras.getString("fieldName"));
        address.setText(extras.getString("fieldAddress"));
        phone.setText(extras.getString("fieldPhone"));
        email.setText(extras.getString("fieldEmail"));
        desc.setText(extras.getString("fieldDesc"));
        url.setText("Katso kentän kotisivut:\n" + extras.getString("fieldUrl"));
        latlng.setText("Katso sijainti kartalta");
        latlng.setPaintFlags(latlng.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        latlng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latlng = getIntent().getExtras().getString("fieldLatlng");
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("geo:" + latlng + "?q=" + latlng + "(" + Uri.encode(getIntent().getExtras().getString("fieldName")) + ")");
                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");
                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }


/*    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        showProgressDialog();
    }*/

        protected void onPostExecute(Bitmap result) {
            //pDlg.dismiss();
            bmImage.setImageBitmap(result);
        }
    }
}
