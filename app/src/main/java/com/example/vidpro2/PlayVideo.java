package com.example.vidpro2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.vidpro2.databinding.ActivityPlayVideoBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayVideo extends AppCompatActivity {
    ActivityPlayVideoBinding playVideoBinding;
    private int position;
    Handler handler = new Handler();
    boolean fullscreen = false;
    AdapterVideo adapterVideo;
    AudioManager audioManager;
    SqlHistory sqlHistory;

    private static final int MAX_VOLUME = 100;
    private static final int MIN_VOLUME = 0;
    private static final int MAX_LIGHTNESS = 255;
    private static final int MIN_LIGHTNESS = 0;
    private int mMaxVolume;

    // Ð? sáng màn hình hi?n t?i
    private int mShowLightness;

    int CurrentVol ;
    int getMaxAudio;
    int changeVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        sqlHistory = new SqlHistory(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        APIManage service = RetrofitClient.getClient(Url.urlItemTwo).create(APIManage.class);
        service.getCateTwo().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                adapterVideo = new AdapterVideo(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlayVideo.this,
                        LinearLayoutManager.VERTICAL, false);
                playVideoBinding.rvListVideo1.setLayoutManager(layoutManager);
                playVideoBinding.rvListVideo1.setAdapter(adapterVideo);
                adapterVideo.setIonClickVideo(new IonClickVideo() {
                    @Override
                    public void onClickVideo(String name, String ava, String url, String des) {
                        Intent intent = new Intent(getBaseContext(), PlayVideo.class);
                        intent.putExtra("url", url);
                        intent.putExtra("name", name);
                        intent.putExtra("ava", ava);
                        //  intent.putParcelableArrayListExtra("url", (ArrayList<? extends Parcelable>) response.body());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });

        playVideoBinding = DataBindingUtil.setContentView(this, R.layout.activity_play_video);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String name = intent.getStringExtra("name");
        String ava = intent.getStringExtra("ava");
        sqlHistory.onInsertDB(name, ava, url);
        playVideoBinding.tvNameVid.setText(name);
        Uri uri = Uri.parse(url);
        playVideoBinding.vidView.setVideoURI(uri);
        playVideoBinding.vidView.requestFocus();
        playVideoBinding.vidView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                totalTime();
                updateProgreeBar();
                if (position > 0) {
                    playVideoBinding.vidView.seekTo(position);
                } else {
                    playVideoBinding.vidView.seekTo(0);
                }
                playVideoBinding.vidView.start();


            }
        });
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
        }
        playVideoBinding.vidView.setOnTouchListener(new View.OnTouchListener() {
            float ex1, ex2, ey1, ey2;
            @SuppressLint("ClickableViewAccessibility")
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ex1 = event.getX();
                        ey1 = event.getY();
                    }
                    case MotionEvent.ACTION_MOVE: {
                        ex2 = event.getX();
                        ey2 = event.getY();

                        if (ex1 < (float) playVideoBinding.vidView.getWidth() / 2) {

                            //trai
                            if (ey1 - ey2 > 200) {
                                setChangeVolume(1);
                               // Toast.makeText(getBaseContext(), "up", Toast.LENGTH_SHORT).show();
                            }
                            if (ey2 - ey1 > 200) {
                                setChangeVolume(-1);
                                // Toast.makeText(context, "Down", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (ey1 - ey2 > 200) {
                                setChangeLightness(1);
                            }
                            if (ey2 - ey1 > 200) {
                                setChangeLightness(-1);
                            }
                        }
                        if (ex1 - ex2 > 200) {
                            if (playVideoBinding.vidView.getCurrentPosition() <= 500) {
                                position = 0;
                                playVideoBinding.vidView.seekTo(position);

                            } else {
                                position = playVideoBinding.vidView.getCurrentPosition() - 500;
                                playVideoBinding.vidView.seekTo(position);

                            }
                            //Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        if (ex2 - ex1 > 200) {
                            position = playVideoBinding.vidView.getCurrentPosition() + 500;
                            playVideoBinding.vidView.seekTo(position);

                            // Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    }
                }
                return true;
            }
        });
        playVideoBinding.imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playVideoBinding.vidView.getCurrentPosition() <= 10000) {
                    position = 0;
                    playVideoBinding.vidView.seekTo(position);
                    playVideoBinding.vidView.start();
                } else {
                    position = playVideoBinding.vidView.getCurrentPosition() - 10000;
                    playVideoBinding.vidView.seekTo(position);
                    playVideoBinding.vidView.start();
                }
            }
        });
        playVideoBinding.imForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = playVideoBinding.vidView.getCurrentPosition() + 10000;
                playVideoBinding.vidView.seekTo(position);
                playVideoBinding.vidView.start();
            }
        });
        playVideoBinding.imPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = playVideoBinding.vidView.getCurrentPosition();
                playVideoBinding.vidView.seekTo(position);
                playVideoBinding.vidView.start();
                playVideoBinding.imPlay.setVisibility(View.GONE);
                playVideoBinding.imPause.setVisibility(View.VISIBLE);
            }
        });
        playVideoBinding.imPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideoBinding.vidView.getCurrentPosition();
                playVideoBinding.vidView.pause();
                playVideoBinding.imPause.setVisibility(View.GONE);
                playVideoBinding.imPlay.setVisibility(View.VISIBLE);
            }
        });
        playVideoBinding.sbProcess.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    playVideoBinding.vidView.seekTo(progress);
                    playVideoBinding.sbProcess.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (playVideoBinding.vidView != null) {
                    if (playVideoBinding.vidView.isPlaying()) {
                        try {
                            Message message = new Message();
                            message.what = playVideoBinding.vidView.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(500);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        playVideoBinding.imFullScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullscreen) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) playVideoBinding.getRoot().getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.WRAP_CONTENT;
                    playVideoBinding.getRoot().setLayoutParams(params);
                    fullscreen = false;
                } else {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) playVideoBinding.getRoot().getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    playVideoBinding.getRoot().setLayoutParams(params);
                    fullscreen = true;
                }
            }
        });
    }

    private void updateProgreeBar() {
        handler.postDelayed(updateTimeTask, 0);
    }

    private Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {
            timeRun();
            playVideoBinding.sbProcess.setProgress(playVideoBinding.vidView.getCurrentPosition());
            playVideoBinding.sbProcess.setMax(playVideoBinding.vidView.getDuration());
            handler.postDelayed(this, 0);
        }
    };

    private void timeRun() {
        String runTime = "";
        String secondString = "";
        int hour = playVideoBinding.vidView.getCurrentPosition() / (1000 * 60 * 60);
        int min = (playVideoBinding.vidView.getCurrentPosition() % (1000 * 60 * 60)) / (1000 * 60);
        int sec = (playVideoBinding.vidView.getCurrentPosition() % (1000 * 60 * 60)) % (1000 * 60) / 1000;
        if (hour > 0) {
            runTime = hour + ":";
        }
        if (sec < 10) {
            secondString = "0" + sec;
        } else {
            secondString = "" + sec;
        }
        runTime = runTime + min + ":" + secondString;
        playVideoBinding.tvRunTime.setText(runTime);

    }

    private void totalTime() {
        int duration = playVideoBinding.vidView.getDuration() / 1000;
        int hour = duration / 3600;
        int minutes = (duration / 60) - (hour * 60);
        int sec = duration - (hour * 3600) - (minutes * 60);
        String allTime = "";
        if (hour > 0) {
            allTime = hour + ":";
        }
        String minTime;
        if (sec < 10) {
            minTime = "0" + sec;
        } else {
            minTime = "" + sec;
        }
        allTime = allTime + minutes + ":" + minTime;
        playVideoBinding.tvTotalTime.setText(allTime);

    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("position", playVideoBinding.vidView.getCurrentPosition());
    }
    private void setChangeVolume(int vol) {
        // get am thanh
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // get Maxvolume(maxvolume =100)
        getMaxAudio = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // get Volume hien tai = 15
        CurrentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        CurrentVol += (vol);
        if (CurrentVol > MAX_VOLUME) {
            CurrentVol = MAX_VOLUME;
        } else if (CurrentVol < MIN_VOLUME) {
            CurrentVol = MIN_VOLUME;
        }

        int changeVol = CurrentVol * getMaxAudio / 100;


        // set am thanh
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, CurrentVol, 0);

        // set hien thi len man hinh
        Toast.makeText(getApplicationContext(), "Sound : " + changeVol, Toast.LENGTH_SHORT).show();
    }
    private int setChangeLightness(int bright){
        // do sang duoc tang hoac giam
        mShowLightness += bright;
        if (mShowLightness > MAX_LIGHTNESS) {
            mShowLightness = MAX_LIGHTNESS;
        } else if (mShowLightness < MIN_LIGHTNESS) {
            mShowLightness = MIN_LIGHTNESS;
        }
        // set do sang cho he thong
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = (mShowLightness) / 255f;
        getWindow().setAttributes(layoutParams);
        // chuyen ve 100%
        int percentligt = ((mShowLightness * 100) / 255);
        Toast.makeText(getApplicationContext(), "Bright : " + percentligt + "%", Toast.LENGTH_SHORT).show();
        return percentligt;
    }


}
