package com.tech.ayugram.messenger;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Keep
public class MediaController {
    private static MediaController[] instances = new MediaController[3];
    
    private final ConcurrentHashMap<String, MediaPlayer> playingMessages = new ConcurrentHashMap<>();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private AudioManager audioManager;
    private int previousAudioMode = AudioManager.MODE_NORMAL;
    private boolean wasMusicActive = false;

    private MediaController(int accountNum) {
        audioManager = (AudioManager) ApplicationLoader.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    public static MediaController getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new MediaController(num);
        }
        return instances[num];
    }

    public static MediaController getInstance() {
        return getInstance(0);
    }

    public void playMessage(String path) {
        playMessage(path, null);
    }

    public void playMessage(String path, MediaPlayer.OnCompletionListener listener) {
        if (path == null) return;
        
        try {
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(ApplicationLoader.getApplicationContext(), Uri.parse(path));
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnCompletionListener(mp -> {
                playingMessages.remove(path);
                mp.release();
                if (listener != null) listener.onCompletion(mp);
            });
            player.setOnErrorListener((mp, what, extra) -> {
                playingMessages.remove(path);
                mp.release();
                return true;
            });
            player.prepareAsync();
            player.setOnPreparedListener(MediaPlayer::start);
            playingMessages.put(path, player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseMessage(String path) {
        MediaPlayer player = playingMessages.get(path);
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    public void resumeMessage(String path) {
        MediaPlayer player = playingMessages.get(path);
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    public void stopMessage(String path) {
        MediaPlayer player = playingMessages.remove(path);
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    public boolean isPlayingMessage(String path) {
        MediaPlayer player = playingMessages.get(path);
        return player != null && player.isPlaying();
    }

    public void setAudioFocus(boolean request) {
        if (request) {
            previousAudioMode = audioManager.getMode();
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        } else {
            audioManager.abandonAudioFocus(null);
            audioManager.setMode(previousAudioMode);
        }
    }

    public void cleanup() {
        for (MediaPlayer player : playingMessages.values()) {
            try {
                player.stop();
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        playingMessages.clear();
    }
}