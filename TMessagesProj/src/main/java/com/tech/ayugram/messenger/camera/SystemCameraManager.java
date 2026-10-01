package com.tech.ayugram.messenger.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.Keep;
import androidx.core.content.FileProvider;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.FileLoader;

import java.io.File;

@Keep
public class SystemCameraManager {
    private static SystemCameraManager instance;
    
    public static final int REQUEST_PHOTO = 1001;
    public static final int REQUEST_VIDEO = 1002;
    public static final int REQUEST_ROUND_VIDEO = 1003;
    public static final int REQUEST_AUDIO = 1004;
    
    private SystemCameraManager() {
    }

    public static synchronized SystemCameraManager getInstance() {
        if (instance == null) {
            instance = new SystemCameraManager();
        }
        return instance;
    }

    /**
     * R11/R26: System camera for photos
     * ACTION_IMAGE_CAPTURE
     */
    public void takePhoto(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = createImageFile();
        if (photoFile != null) {
            Uri photoUri = FileProvider.getUriForFile(
                activity,
                ApplicationLoader.APP_PACKAGE + ".fileprovider",
                photoFile
            );
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            activity.startActivityForResult(intent, REQUEST_PHOTO);
        }
    }

    /**
     * R11/R26: System camera for videos
     * ACTION_VIDEO_CAPTURE
     */
    public void takeVideo(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File videoFile = createVideoFile();
        if (videoFile != null) {
            Uri videoUri = FileProvider.getUriForFile(
                activity,
                ApplicationLoader.APP_PACKAGE + ".fileprovider",
                videoFile
            );
            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // High quality
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 300); // 5 minutes
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            activity.startActivityForResult(intent, REQUEST_VIDEO);
        }
    }

    /**
     * R11: Round video (video message) - system video + round_message flag
     * Trade-off: no live ring/timer overlay
     */
    public void takeRoundVideo(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File videoFile = createVideoFile();
        if (videoFile != null) {
            Uri videoUri = FileProvider.getUriForFile(
                activity,
                ApplicationLoader.APP_PACKAGE + ".fileprovider",
                videoFile
            );
            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60); // 1 minute for round videos
            // Mark as round video for later processing
            intent.putExtra("round_message", true);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            activity.startActivityForResult(intent, REQUEST_ROUND_VIDEO);
        }
    }

    /**
     * R11: Audio messages - AudioRecord (not camera)
     * This is handled separately via AudioRecord API
     */
    public void recordAudio(Activity activity) {
        // Audio recording is handled by AudioRecord directly
        // Not using system intent for audio
    }

    private File createImageFile() {
        try {
            String fileName = FileLoader.getInstance().getAttachFileName("IMG_");
            File file = FileLoader.getInstance().getPathToAttach(fileName, false);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private File createVideoFile() {
        try {
            String fileName = FileLoader.getInstance().getAttachFileName("VID_");
            File file = FileLoader.getInstance().getPathToAttach(fileName, false);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Handle activity result
     * Returns Uri of captured media or null
     */
    public Uri handleResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return null;
        
        switch (requestCode) {
            case REQUEST_PHOTO:
            case REQUEST_VIDEO:
            case REQUEST_ROUND_VIDEO:
                // The file was saved to the EXTRA_OUTPUT location
                // We need to track which file was used
                return getLastCaptureUri(requestCode);
            default:
                return null;
        }
    }

    private Uri getLastCaptureUri(int requestCode) {
        // In a real implementation, track the file Uri per request
        // For now, return null - the calling code should track the file
        return null;
    }

    /**
     * Check if system camera is available
     */
    public boolean isCameraAvailable(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) ||
               pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
}