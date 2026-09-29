package com.tech.ayugram.maps.stub.model;

public class PatternItem {
    public final static class Gap extends PatternItem {
        public final int length;

        public Gap(int length) {
            this.length = length;
        }
    }

    public final static class Dash extends PatternItem {
        public final int length;

        public Dash(int length) {
            this.length = length;
        }
    }
}