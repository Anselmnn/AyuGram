package com.tech.ayugram.firebase.stub;

public class FirebaseOptions {
    private String applicationId;
    private String apiKey;
    private String databaseUrl;
    private String storageBucket;
    private String projectId;
    private String gcmSenderId;

    public static class Builder {
        private String applicationId;
        private String apiKey;
        private String databaseUrl;
        private String storageBucket;
        private String projectId;
        private String gcmSenderId;

        public Builder setApplicationId(String applicationId) { this.applicationId = applicationId; return this; }
        public Builder setApiKey(String apiKey) { this.apiKey = apiKey; return this; }
        public Builder setDatabaseUrl(String databaseUrl) { this.databaseUrl = databaseUrl; return this; }
        public Builder setStorageBucket(String storageBucket) { this.storageBucket = storageBucket; return this; }
        public Builder setProjectId(String projectId) { this.projectId = projectId; return this; }
        public Builder setGcmSenderId(String gcmSenderId) { this.gcmSenderId = gcmSenderId; return this; }

        public FirebaseOptions build() {
            FirebaseOptions options = new FirebaseOptions();
            options.applicationId = this.applicationId;
            options.apiKey = this.apiKey;
            options.databaseUrl = this.databaseUrl;
            options.storageBucket = this.storageBucket;
            options.projectId = this.projectId;
            options.gcmSenderId = this.gcmSenderId;
            return options;
        }
    }

    public String getApplicationId() { return applicationId; }
    public String getApiKey() { return apiKey; }
    public String getDatabaseUrl() { return databaseUrl; }
    public String getStorageBucket() { return storageBucket; }
    public String getProjectId() { return projectId; }
    public String getGcmSenderId() { return gcmSenderId; }
}