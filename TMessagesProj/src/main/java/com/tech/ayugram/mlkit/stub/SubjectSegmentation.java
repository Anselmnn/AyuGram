package com.tech.ayugram.mlkit.stub;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

/**
 * Phase 2: Stub for com.google.mlkit.vision.segmentation.subject.SubjectSegmentation
 * ML Kit dependency removed in Phase 2
 */
public class SubjectSegmentation {
    private SubjectSegmentation() {}

    public static SubjectSegmenter getClient(SubjectSegmenterOptions options) {
        return new SubjectSegmenter();
    }
}