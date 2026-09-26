package com.tech.ayugram.mlkit.stub;

import android.annotation.SuppressLint;

import java.util.List;

/**
 * Phase 2: Stub for com.google.mlkit.vision.segmentation.subject.SubjectSegmenter
 * ML Kit dependency removed in Phase 2
 */
public class SubjectSegmenter {
    public interface Task<T> {
        Task<T> addOnSuccessListener(OnSuccessListener<T> listener);
        Task<T> addOnFailureListener(OnFailureListener listener);
    }

    public interface OnSuccessListener<T> {
        void onSuccess(T result);
    }

    public interface OnFailureListener {
        void onFailure(Exception e);
    }

    public Task<SubjectSegmentationResult> process(Object inputImage) {
        return new Task<SubjectSegmentationResult>() {
            @Override
            public Task<SubjectSegmentationResult> addOnSuccessListener(OnSuccessListener<SubjectSegmentationResult> listener) {
                listener.onSuccess(new SubjectSegmentationResult());
                return this;
            }

            @Override
            public Task<SubjectSegmentationResult> addOnFailureListener(OnFailureListener listener) {
                return this;
            }
        };
    }
}