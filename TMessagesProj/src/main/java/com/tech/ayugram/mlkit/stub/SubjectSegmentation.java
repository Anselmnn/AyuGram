package com.tech.ayugram.mlkit.stub;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

public class SubjectSegmentation {
    private SubjectSegmentation() {}

    public static SubjectSegmenter getClient(SubjectSegmenterOptions options) {
        return new SubjectSegmenter();
    }
}