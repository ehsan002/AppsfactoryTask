package com.ehsanrc.appsfactorytask.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ehsanrc.appsfactorytask.R;
import com.ehsanrc.appsfactorytask.models.TrackList;

import java.util.List;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.MyTracksViewHolder>{

    private Context context;
    private List<TrackList> tracks;

    public TrackListAdapter(Context context, List<TrackList> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public MyTracksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tracks,parent,false);
        MyTracksViewHolder holder = new MyTracksViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTracksViewHolder holder, int position) {
        holder.trackNo.setText(Integer.toString(tracks.get(position).getTrackNo())+". ");
        holder.trackName.setText(tracks.get(position).getTrackName());
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public class MyTracksViewHolder extends RecyclerView.ViewHolder {

        public TextView trackNo, trackName;
        public RelativeLayout parentLayoutDetails;

        public MyTracksViewHolder(View itemView) {
            super(itemView);
            trackNo = itemView.findViewById(R.id.tv_track_no);
            trackName = itemView.findViewById(R.id.tv_track_name);
            parentLayoutDetails = itemView.findViewById(R.id.rv_tracks);
        }
    }

}
