package fi.jamk.golfcoursesapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Pedo on 1.11.2016.
 */
public class GolfFieldsAdapter extends RecyclerView.Adapter<GolfFieldsAdapter.ViewHolder> {
    // adapterdata
    private List<GolfField> gfList;

    public GolfFieldsAdapter(List<GolfField> gfList) {
        this.gfList = gfList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.golffield_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GolfFieldsAdapter.ViewHolder holder, int position) {
        GolfField gf = gfList.get(position);
        //holder.golffieldImageView.setImageURI(Uri.parse("http://ptm.fi/jamk/android/golfcourses/" + gf.getImageUrl()));
        new DownloadImageTask(holder.golffieldImageView).execute("http://ptm.fi/jamk/android/golfcourses/" + gf.getImageUrl());




        holder.golffieldNameTextView.setText(gf.getName());
        holder.golffieldAddressTextView.setText(gf.getAddress());
        holder.golffieldEmailTextView.setText(gf.getEmail());
        holder.golffieldPhoneTextView.setText(gf.getPhone());
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

    @Override
    public int getItemCount() {
        return gfList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView golffieldImageView;
        public TextView golffieldNameTextView;
        public TextView golffieldAddressTextView;
        public TextView golffieldPhoneTextView;
        public TextView golffieldEmailTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            // get layout ids
            golffieldImageView = (ImageView) itemView.findViewById(R.id.golffieldImageView);
            golffieldNameTextView = (TextView) itemView.findViewById(R.id.golffieldNameTextView);
            golffieldAddressTextView = (TextView) itemView.findViewById(R.id.golffieldAddressTextView);
            golffieldPhoneTextView = (TextView) itemView.findViewById(R.id.golffieldPhoneTextView);
            golffieldEmailTextView = (TextView) itemView.findViewById(R.id.golffieldEmailTextView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("fieldName", golffieldNameTextView.getText().toString());
                    intent.putExtra("fieldAddress", golffieldAddressTextView.getText().toString());
                    intent.putExtra("fieldPhone", golffieldPhoneTextView.getText().toString());
                    intent.putExtra("fieldEmail", golffieldEmailTextView.getText().toString());
                    intent.putExtra("fieldDesc", gfList.get(position).getDescription());
                    intent.putExtra("fieldUrl", gfList.get(position).getUrl());
                    intent.putExtra("fieldImageUrl", gfList.get(position).getImageUrl());
                    intent.putExtra("fieldLatlng", gfList.get(position).getLat() + "," + gfList.get(position).getLng());
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
