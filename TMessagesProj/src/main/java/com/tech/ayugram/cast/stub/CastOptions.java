package com.tech.ayugram.cast.stub;

public class CastOptions {
    public static class Builder {
        private String receiverApplicationId;
        private LaunchOptions launchOptions;

        public Builder(String receiverApplicationId) {
            this.receiverApplicationId = receiverApplicationId;
        }

        public Builder setLaunchOptions(LaunchOptions launchOptions) {
            this.launchOptions = launchOptions;
            return this;
        }

        public CastOptions build() {
            return new CastOptions();
        }
    }

    public String getReceiverApplicationId() { return ""; }
    public LaunchOptions getLaunchOptions() { return null; }
}