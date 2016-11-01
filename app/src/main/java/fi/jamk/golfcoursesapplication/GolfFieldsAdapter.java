package fi.jamk.golfcoursesapplication;

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
                    String name = gfList.get(position).name;
                    Toast.makeText(view.getContext(), name, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
