package com.svobodapeter.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SongAlbum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_album);

        /**
         * Intent which will open Playing.class from SongAlbum.class
         */
        ImageButton playActivity = findViewById(R.id.play_activity);
        playActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                //Process after activation of event
                Intent mainIntentActivity = new Intent(SongAlbum.this,PlayingActivity.class);
                startActivity(mainIntentActivity);
            }
        });

        /**
         * Creating new seperated adapters from SongPreviewAdapter for each category of music
         */
        SongPreviewAdapter albumAdapter = new SongPreviewAdapter(trendMusicMethod());
        SongPreviewAdapter albumAdapter2 = new SongPreviewAdapter(electronicMusicMethod());
        SongPreviewAdapter albumAdapter3 = new SongPreviewAdapter(popMusicMethod());
        SongPreviewAdapter albumAdapter4 = new SongPreviewAdapter(rockMusicMethod());

        /**
         * Will atach new adapter to the RecyclerView and give it also LayoutManager to make RecyclerView scrolling horizontaly.
         */
        RecyclerView trendingView = findViewById(R.id.trending);
        trendingView.setAdapter(albumAdapter);
        trendingView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RecyclerView electronicView = findViewById(R.id.electronical);
        electronicView.setAdapter(albumAdapter2);
        electronicView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RecyclerView popView = findViewById(R.id.pop);
        popView.setAdapter(albumAdapter3);
        popView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RecyclerView rockView = findViewById(R.id.rock);
        rockView.setAdapter(albumAdapter4);
        rockView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    /**
     * Class which is creating new adapter, which is attached to the RecyclerView of music category
     * Was necessary to implement directly in SongAlbum because of OnClickListener
     * Inspired by blog of Willow Tree Apps company - https://willowtreeapps.com/ideas/android-fundamentals-working-with-the-recyclerview-adapter-and-viewholder-pattern/
     */
    class SongPreviewAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {

        private List<SongPreview> models = new ArrayList<>();

        /**
         * Taking all views which we have in SongPreview and attach them to
         * @param viewModels
         */

        public SongPreviewAdapter(final List<SongPreview> viewModels) {
            if (viewModels != null) {
                this.models.addAll(viewModels);

            }
        }

        /**
         * Recycling is handled by framework, therefore we are creating ViewHolder which will inflate parent view.
         *
         * @param parent
         *         The ViewGroup into which the new View will be added after it is bound to
         *         an adapter position.
         * @param viewType
         *         The view type of the new View.
         *
         * @return
         */
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return new SongPreviewViewHolder(view);
        }

        /**
         * This is where the data is bound to each ViewHolder. This method is called at least once and will be
         * called each time the adapter is notified that the data set has changed
         *
         * @param holder
         *         The ViewHolder
         * @param position
         *         The position in our collection of data
         */
        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            ((SongPreviewViewHolder) holder).bindData(models.get(position));

            /**
             * SetOnClickListener is called everytime, when is selected song in RecyclerView. Intent (playingActivityIntent)
             * is opening Playing.class to play selected song
             *
             */
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent playingActivityIntent = new Intent (SongAlbum.this,PlayingActivity.class);
                    playingActivityIntent.putExtra("Artist",((TextView) findViewById(R.id.artist_name)).getText().toString());
                    playingActivityIntent.putExtra("Song",((TextView) findViewById(R.id.song_name)).getText().toString());
                    startActivity(playingActivityIntent);
                }
            });

        }

        /**
         * Gets the size of the collection of items in our list
         *
         * @return An Integer representing the size of the collection that will be displayed
         */
        @Override
        public int getItemCount() {
            return models.size();
        }

        /**
         * Gets the item view type. We can return the static constant that the Android framework
         * creates for us.
         *
         * @param position
         *         The position in the collection
         *
         * @return The item layout id
         */
        @Override
        public int getItemViewType(final int position) {
            return R.layout.playlist;
        }

        class SongPreviewViewHolder extends RecyclerView.ViewHolder  {
            public TextView artistName;
            public TextView songName;
            public ImageView albumImage;
            /**
             * The ViewHolder that will be used to display the data in each item shown
             * in the RecyclerView
             *
             * @param itemView
             *         The layout view group used to display the data
             */
            public SongPreviewViewHolder( View itemView ) {
                super(itemView);

                albumImage = itemView.findViewById(R.id.album_image);
                //https://www.freepik.com/free-photos-vectors/music - Music vector created by Freepik

                artistName = itemView.findViewById(R.id.artist_name);

                songName = itemView.findViewById(R.id.song_name);

            }


            /**
             * Method that is used to bind the data to the ViewHolder
             *
             * @param itemModel
             *         The viewmodel that contains the data
             */
            public void bindData(final SongPreview itemModel) {
                albumImage.setImageResource(itemModel.getAlbumImage());
                artistName.setText(itemModel.getArtistName());
                songName.setText(itemModel.getSongName());

            }
        }


    }

    /**
     * ArrayList "trendMusic" is filled by data
     * @return
     */
    private List<SongPreview> trendMusicMethod(){
        ArrayList<SongPreview> trendMusic = new ArrayList<>();
        trendMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist1), getString(R.string.song1)));
        trendMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist2), getString(R.string.song2)));
        trendMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist3), getString(R.string.song3)));
        trendMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist4), getString(R.string.song4)));

        return trendMusic;
    }

    /**
     * ArrayList "elecMusic" is filled by data
     * @return
     */
    private List<SongPreview> electronicMusicMethod(){
        ArrayList<SongPreview> elecMusic = new ArrayList<>();
        elecMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist1), getString(R.string.song1)));
        elecMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist2), getString(R.string.song2)));
        elecMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist3), getString(R.string.song3)));
        elecMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist4), getString(R.string.song4)));

        return elecMusic;
    }

    /**
     * ArrayList "popMusic" is filled by data
     * @return
     */
    private List<SongPreview> popMusicMethod(){
        ArrayList<SongPreview> popMusic = new ArrayList<>();
        popMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist1), getString(R.string.song1)));
        popMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist2), getString(R.string.song2)));
        popMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist3), getString(R.string.song3)));
        popMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist4), getString(R.string.song4)));

        return popMusic;
    }

    /**
     * ArrayList "rockMusic" is filled by data
     * @return
     */
    private List<SongPreview> rockMusicMethod(){
        ArrayList<SongPreview> rockMusic = new ArrayList<>();
        rockMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist1), getString(R.string.song1)));
        rockMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist2), getString(R.string.song2)));
        rockMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist3), getString(R.string.song3)));
        rockMusic.add(new SongPreview(R.mipmap.blank_image,getString(R.string.artist4), getString(R.string.song4)));

        return rockMusic;
    }


}
