package com.tech.ayugram.mlkit.stub;

public class SubjectSegmenterOptions {
    public static class Builder {
        private boolean enableMultipleSubjects = false;
        private SubjectResultOptions subjectResultOptions;

        public Builder enableMultipleSubjects(SubjectResultOptions options) {
            this.enableMultipleSubjects = true;
            this.subjectResultOptions = options;
            return this;
        }

        public SubjectSegmenterOptions build() {
            return new SubjectSegmenterOptions();
        }
    }

    public static class SubjectResultOptions {
        private boolean enableSubjectBitmap = false;

        public static class Builder {
            private boolean enableSubjectBitmap = false;

            public Builder enableSubjectBitmap() {
                this.enableSubjectBitmap = true;
                return this;
            }

            public SubjectResultOptions build() {
                SubjectResultOptions options = new SubjectResultOptions();
                options.enableSubjectBitmap = this.enableSubjectBitmap;
                return options;
            }
        }
    }
}