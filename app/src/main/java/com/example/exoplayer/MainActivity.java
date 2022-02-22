package com.example.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class MainActivity extends AppCompatActivity {

    SimpleExoPlayerView exoPlayerView;

    // creating a variable for exoplayer
    SimpleExoPlayer exoPlayer;
    Button btnOne,btnTwo;
    // url of video which we are loading.
//    String videoURL = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";
    String videoURL = "https://ak.picdn.net/shutterstock/videos/1066153537/preview/stock-footage-close-up-time-lapse-footage-of-heavy-wet-snow-flakes-falling-onto-a-concrete-balcony-the-flakes.webm";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.videoOneBtn);
        btnTwo = findViewById(R.id.videoTwoBtn);

        exoPlayerView = findViewById(R.id.idExoPlayerVIew);
        try {

            // bandwisthmeter is used for
            // getting default bandwidth
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

            // track selector is used to navigate between
            // video using a default seekbar.
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

            // we are adding our track selector to exoplayer.
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            // we are parsing a video url
            // and parsing its video uri.
            Uri videouri = Uri.parse(videoURL);

            // we are creating a variable for datasource factory
            // and setting its user agent as 'exoplayer_view'
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");

            // we are creating a variable for extractor factory
            // and setting it to default extractor factory.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            // we are creating a media source with above variables
            // and passing our event handler as null,
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
            // inside our exoplayer view
            // we are setting our player
            exoPlayerView.setPlayer(exoPlayer);

            // we are preparing our exoplayer
            // with media source.
            exoPlayer.prepare(mediaSource);

            // we are setting our exoplayer
            // when it is ready.
            exoPlayer.setPlayWhenReady(true);

            btnOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri videouri = Uri.parse("https://ak.picdn.net/shutterstock/videos/1066153537/preview/stock-footage-close-up-time-lapse-footage-of-heavy-wet-snow-flakes-falling-onto-a-concrete-balcony-the-flakes.webm");
                    MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
                    exoPlayer.prepare(mediaSource);
                    exoPlayer.setPlayWhenReady(true);
                }
            });

            btnTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri videouri = Uri.parse("https://cdn.videvo.net/videvo_files/video/free/2013-08/large_watermarked/hd0983_preview.mp4");
                    MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
                    exoPlayer.prepare(mediaSource);
                    exoPlayer.setPlayWhenReady(true);
                }
            });

        } catch (Exception e) {
            // below line is used for
            // handling our errors.
            Log.e("TAG", "Error : " + e.toString());
        }
    }
}